package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 分页查询 可输入的条件有：店铺名(模糊). 店铺状态 区域id owner
     * @param shopCondition 查询的条件
     * @param rowIndex 从第几行开始取数据
     * @param pageSize 返回的条数
     * @param
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                         @Param("rowIndex") int rowIndex,
                         @Param("pageSize") int pageSize);

    /**
     * 查询总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);


    /**
     * 新增店铺 返回1成功 返回-1失败
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    /**
     * 通过shop id 查询店铺
     * @param shopid
     * @return
     */
    Shop queryByShopId(long shopid);
}
