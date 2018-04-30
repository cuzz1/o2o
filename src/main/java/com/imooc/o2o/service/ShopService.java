package com.imooc.o2o.service;


import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptiopns.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;


public interface ShopService {
    /**
     * 根据shopCondition分页返回相应的店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
    /**
     *
     * @param shop
     * @param shopImgInputStrem
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStrem, String fileName) throws ShopOperationException;
    /**
     * 查询指定店铺信息
     * @param long shopId
     * @return Shop shop
     */
    Shop getByShopId(long shopId);

    /**
     * 添加店铺
     * @param shop
     * @param shopImgInputStrem
     * @param fileName
     * @return shopExecution
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStrem, String fileName) throws ShopOperationException;
}
