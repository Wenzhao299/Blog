package com.blog.controller;

import com.blog.model.MyUser;
import com.blog.model.MyUserDetails;
import com.blog.service.MyUserDetailsService;
import com.blog.service.RegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @author: Wenzhao
 * Date: 2021/8/2
 */
@Controller
public class RegisterController {
    @Resource
    RegisterService registerService;

    @PostMapping("/register")
    public String register(MyUser myUser, Model model){
        MyUserDetails userDB = registerService.findByUsername(myUser.getUsername());

        if(userDB==null){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(myUser.getPassword());
            myUser.setPassword(password);
            myUser.setEnabled(1);
            try{
                registerService.register(myUser);
            }catch (Exception e){
                System.out.println(e);
            }
            return "/login";
        }else{
            model.addAttribute("msg","该用户名已经被使用！");
            return "/register";
        }
    }
}
