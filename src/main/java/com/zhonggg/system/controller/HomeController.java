package com.zhonggg.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String defaultpage(){
        return "index"; //当浏览器输入/时，会返回 /templates/home.html页面
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/poem")
    public String login(){
        return "poem/index";
    }
    @GetMapping("/news")
    public String news(){
        return "news/index";
    }
}
