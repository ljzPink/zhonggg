package com.zhonggg.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String index(){
        return "home"; //当浏览器输入/index时，会返回 /templates/home.html页面
    }
}
