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
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/poem")
    public String poem(){
        return "poem/index";
    }
    @GetMapping("/news")
    public String news(){
        return "news/index";
    }
    @GetMapping("/music")
    public String music(){
        return "music/index";
    }







}
