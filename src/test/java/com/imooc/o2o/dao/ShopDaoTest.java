package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;


    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();

        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> list =shopDao.queryShopList(shopCondition, 0, 5);
        System.out.println(list.size());

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(2L);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> list2 =shopDao.queryShopList(shopCondition, 0, 5);
        System.out.println(list2.size());

    }

    @Test
    public void testQueryShopCount() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        int count =shopDao.queryShopCount(shopCondition);
        System.out.println(count);
    }

    @Test
    @Ignore
    public void testQueryByShopId() {
        long shopId = 5L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName:" + shop.getArea().getAreaName());
    }

    @Test
    @Ignore
    public void testInsertShop() {
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
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(5L);

//        shop.setShopDesc("test");
//        shop.setPhone("test");
//        shop.setShopImg("test");

        shop.setShopDesc("测试");
        shop.setPhone("测试");
        shop.setShopImg("测试");

        shop.setLastEditTime(new Date());

        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }
}
