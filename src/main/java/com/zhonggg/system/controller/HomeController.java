package com.zhonggg.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home"; //当浏览器输入/时，会返回 /templates/home.html页面
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/poem")
    public String login(){
        return "poem/index";
    }
}
