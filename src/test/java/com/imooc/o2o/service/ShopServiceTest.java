package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.enmus.ShopStateEnum;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.exceptiopns.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory = new ShopCategory();

        shopCategory.setShopCategoryId(1L);
        shopCondition.setShopCategory(shopCategory);
        ShopExecution shopExecution = shopService.getShopList(shopCondition, 1, 5);
        System.out.println("店铺列表数" + shopExecution.getShopList().size());
        System.out.println("店铺总数" + shopExecution.getCount());
    }



    @Test
    @Ignore
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop  = new Shop();
        shop.setShopId(23L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("D:\\123.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop, inputStream, "123.jpg");
        System.out.println("新的图片地址：" + shopExecution.getShop().getShopImg());

    }

    @Test
    @Ignore
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setPhone("test1");
        shop.setShopImg("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice(ShopStateEnum.CHECK.getStateInfo());
        File shopImg = new File("D:\\123.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, inputStream, shopImg.getName());
    }
}
