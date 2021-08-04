package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.MyUserDetails;
import com.blog.service.BlogService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class PageController {

    @Resource
    BlogService blogService;

    // 首页
    @GetMapping("/home")
    public String index(HttpSession session, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((MyUserDetails) principal).getUsername();
        List<Blog> blogs = blogService.findByUsername(username);
        session.setAttribute("username",username);
        model.addAttribute("blogs",blogs);
        return "home";
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