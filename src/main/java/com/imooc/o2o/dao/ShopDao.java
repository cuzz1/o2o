package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
    // 新增店铺 返回1成功 返回-1失败
    int insertShop(Shop shop);
}
