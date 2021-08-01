package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

//    // 登录
//    @PostMapping("/login")
//    public String index(String username,String password) {
//        return "index";
//    }

    // 首页
    @GetMapping("/main")
    public String index() {
        return "main";
    }

    // 新建博客
    @GetMapping("/write")
    public String showOrder() {
        return "write";
    }

    // 博客管理
    @GetMapping("/admin")
    public String addOrder() {
        return "admin";
    }

    // 关于博客
    @GetMapping("/about")
    public String updateOrder() {
        return "about";
    }


}