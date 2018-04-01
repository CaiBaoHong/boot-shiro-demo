package com.abc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/login")
    public String login(){
        return "<html><body><h1>登录页</h1></body></html>";
    }

    @RequestMapping("/index")
    public String index(){
        return "<html><body><h1>首页</h1></body></html>";
    }

    @RequestMapping("/401")
    public String page401(){
        return "<html><body><h1>401</h1></body></html>";
    }

}
