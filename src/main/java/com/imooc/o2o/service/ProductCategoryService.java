package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptiopns.ProductCategoryOperationException;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public interface ProductCategoryService {
    /**
     * 查询指定某个店铺下的所有的商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategroyList(Long shopId);

    /**
     * 批量添加CategoryList
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}
