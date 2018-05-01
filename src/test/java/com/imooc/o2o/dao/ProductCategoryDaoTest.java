package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Ignore
    public void testQueryByShopId() throws Exception{
        Long shopId = 4L;
        List<ProductCategory> productCategorieList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为：" + productCategorieList.size());
    }

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商铺类别1");
        productCategory.setPriority(20);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(6L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商铺类别2");
        productCategory2.setPriority(10);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(6L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);

        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, effectedNum);
    }
}
