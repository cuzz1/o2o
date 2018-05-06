package com.imooc.o2o.service.impl;


import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptiopns.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;


    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        System.out.println("===================");
        System.out.println(pageIndex);
        System.out.println(pageSize);
        System.out.println("===================");
        // 页面转化成数据库的行码 并调用dao层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);

        System.out.println("===================");
        System.out.println(rowIndex);
        System.out.println(pageSize);
        System.out.println("===================");
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);

        for (Product product : productList) {
            System.out.println(product.getProductId());
            System.out.println(product.getProductName());
            System.out.println("===================");
        }
        // 基于同样的查询条件返回该条件下的商品总数
        int count = productDao.queryProductCount(productCondition);
        ProductExecution productExecution = new ProductExecution();
        if (productList != null) {
            productExecution.setProductList(productList);
            productExecution.setCount(count);
        } else {
            productExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return productExecution;
    }

    @Override
    // 1. 处理缩略图，获取缩略图相对路径并赋值给product
    // 2. 往tb_product写入商品信息，获取productId
    // 3. 结合productId批量处理商品详情图
    // 4. 将商品的详情图列表批量插入tb_product_img中
    // 事务管理
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList)
            throws ProductOperationException {
        // 空值判断
        if (product !=null && product.getShop().getShopId() != null) {
            // 给商品设置默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 默认为上架状态
            product.setEnableStatus(1);
            // 若商品缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }

            try {
                // 创建商品信息
                int effectNum = productDao.insertProduct(product);
                if (effectNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败" + e.getMessage());
            }
            // 若商品详情图不为空则添加
            if (imageHolderList != null && imageHolderList.size() > 0) {
                addProductImgList(product, imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            // 传入的参数为空
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 批量添加图
     * @param product
     * @param imageHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
        // 获取图片储存的路径，这里直接放在相应的店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        // 遍历图片一次去处理，并添加进productImg实体类
        for (ImageHolder imageHolder : imageHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder.getImage(), imageHolder.getImageName(), dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }

        // 如果确实是有图片需要添加的 就执行批量添加
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败" + e.getMessage());

            }
        }
    }

    /**
     * 添加缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        // 获取根路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail.getImage(), thumbnail.getImageName(), dest);
        product.setImgAddr(thumbnailAddr);
    }


}
