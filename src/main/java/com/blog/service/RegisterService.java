package com.blog.service;

import com.blog.mapper.MyUserDetailsMapper;
import com.blog.model.MyUser;
import com.blog.model.MyUserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Wenzhao
 * Date: 2021/8/2
 */
@Service
public class RegisterService {
    @Resource
    MyUserDetailsMapper myUserDetailsMapper;

    public MyUserDetails findByUsername(String username) {
        return myUserDetailsMapper.findByUsername(username);
    }

    public void register(MyUser myUser){
        myUserDetailsMapper.register(myUser);
    }
}
