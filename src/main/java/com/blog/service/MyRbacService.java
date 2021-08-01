package com.blog.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Wenzhao
 * Date: 2021/8/1
 */
@Component("RBAC")
public class MyRbacService {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails) principal);
            //simpleGrantedAuthority：本次申请访问的资源
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(request.getRequestURI());
            //userDetails.getAuthorities()：当前用户可以访问的资源
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);
        }
        return false;
    }
}
