package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.enmus.ShopStateEnum;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;


public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
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
        File shopImg = new File("C:\\Users\\my\\Desktop\\QQ图片20180203002936.jpg");
//        shopService.addShop(shop,shopImg);
    }
}