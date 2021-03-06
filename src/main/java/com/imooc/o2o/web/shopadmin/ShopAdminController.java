package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value="/shopedit")
    public String shopOperation() {
        return "shop/shopedit";
    }

    @RequestMapping(value="/shoplist")
    public String shopList() {
       return "shop/shoplist";
    }

    @RequestMapping(value="/shopmanage")
    public String shopManagement() {
        return "shop/shopmanage";
    }

    @RequestMapping(value = "/productcategorymanage")
    public String ProductCategoryManage() {
        return "shop/productcategorymanage";
    }

    @RequestMapping(value = "/productmanage")
    public String ProductManage() {
        return "shop/productmanage";
    }

    @RequestMapping(value = "/productedit")
    public String ProductEdit() {
        return "shop/productedit";
    }


}
