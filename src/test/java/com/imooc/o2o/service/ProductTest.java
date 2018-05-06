package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductTest extends BaseTest {
    @Autowired
    ProductService productService;

    @Test
    public void testaddProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(4L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);

        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试");
        product.setPriority(0);

        // 创建缩略图文件流
        File thumbnailFile = new File("D:\\123.jpg");
        InputStream thumbnail = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), thumbnail);

        // 创建详情图片列表
        File thumbnailFile2 = new File("D:\\456.png");
        InputStream thumbnail2 = new FileInputStream(thumbnailFile2);
        ImageHolder imageHolder2 = new ImageHolder(thumbnailFile2.getName(), thumbnail2);

        List<ImageHolder> list = new ArrayList<>();
        // 不能使用同一个流, 报错！！！找了好久
//        list.add(imageHolder);
        list.add(imageHolder2);

        // 添加商品并验证
        ProductExecution productExecution = productService.addProduct(product, imageHolder, list);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }
}
