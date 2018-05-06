package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptiopns.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    // 支持上传商品详情图片的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/getproductlistbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductListByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取前台传过来的页面
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        // 获取前台穿过来的每页要求返回带商品上线
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 从当前session中获取店铺信息 主要获取shopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        // 空值判断
        if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)) {
            // 获取传入的需要的检索条件
            long productCategoryId = HttpServletRequestUtil.getLong(request, "categoryId");
            String productName = HttpServletRequestUtil.getString(request, "productName");
            Product productCadition = compactProductCondition(currentShop.getShopId(), productCategoryId, productName);
            // 传入查询条件已经分页查询 返回相应的商品列表已经总数
            ProductExecution productExecution = productService.getProductList(productCadition, pageIndex, pageSize);
            System.out.println("************************");
            System.out.println(productExecution.getProductList().size());
            System.out.println("************************");


            modelMap.put("productList", productExecution.getProductList());
            modelMap.put("count", productExecution.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or currentShop!!!");
        }
        return modelMap;
    }

    private Product compactProductCondition(Long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        // 如果有指定类别的要求添加进去
        if (productCategoryId != -1L) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        // 如果有商品模糊查询添加进去
        if (productName != null) {
            productCondition.setProductName(productName);
        }
        return productCondition;
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 验证码验证

        // 接受前端参数的变量的的初始化，包括商品，缩略图， 详情图片实体
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");

        System.out.println(productStr);

        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();

        // 获取文件流
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        try {
            // 如果请求中存在文件流，则取出相关文件(包括缩略图，和详情图)
            if (multipartResolver.isMultipart(request)) {
                multipartRequest = (MultipartHttpServletRequest) request;
                // 取出缩略图并构建ImageHolder对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());
                // 取出详情图片列表并构建List<ImageHolder>列表，最多支持六张图片
                for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                    CommonsMultipartFile productImgFile =
                            (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);

                    if (productImgFile != null) {
                        // 如果取出第i个详情图片的文件流不为空 则将其加入列表
                        ImageHolder productImg = new ImageHolder(
                                productImgFile.getOriginalFilename(), productImgFile.getInputStream());

                        productImgList.add(productImg);
                    } else {
                        // 若取出的第i个详情图片文件流为空，则终止循环
                        break;
                    }
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能空");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }


        try {
            // 尝试获取前端传过来的表单string流并将其转化成Product实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }


        // 如果Product信息，缩略图已经详情图片列表都不为空，则开始进行商品添加操作
        if (product != null && thumbnail != null && productImgList.size() > 0) {
            try {
                // 从session中获取当前的店铺id并赋值给product 减少对前端的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);

                // 执行并添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }

        return modelMap;
    }

}
