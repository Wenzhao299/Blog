package com.blog.service;

import com.blog.mapper.MyUserDetailsMapper;
import com.blog.model.MyUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author: Wenzhao
 * Date: 2021/8/1
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    MyUserDetailsMapper myUserDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用户基本信息
        MyUserDetails myUserDetails = myUserDetailsMapper.findByUsername(username);
        //用户角色信息
        String role_code = myUserDetailsMapper.findRoleByUsername(username);
        //根据角色加载权限
        List<String> authorities = myUserDetailsMapper.findAuthorityByRoleCodes(role_code);

        authorities.addAll(Collections.singleton("ROLE_"+role_code));
        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",authorities)));

        return myUserDetails;
    }
}
