package com.imooc.o2o.exceptiopns;

public class ProductOperationException extends RuntimeException{
    public ProductOperationException(String msg) {
       super(msg);
    }
}
