package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptiopns.ProductOperationException;


import java.util.List;

public interface ProductService {
    /**
     * 查询商品列表并且分页
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);


    /**
     *  添加商品以及图片处理
     * @param product
     * @param thumbnail
     * @param thumbnailName
     * @param productImgList
     * @param productImgNameList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
                                List<ImageHolder> imageHolderList) throws ProductOperationException;



}
