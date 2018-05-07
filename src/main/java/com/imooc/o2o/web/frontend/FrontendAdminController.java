package com.imooc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendAdminController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    private String index() {
        return "frontend/index";
    }
}
