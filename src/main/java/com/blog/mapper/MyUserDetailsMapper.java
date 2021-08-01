package com.blog.mapper;

import com.blog.model.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Wenzhao
 * Date: 2021/8/1
 */
public interface MyUserDetailsMapper {
    //根据username查询用户信息
    @Select("select username,password,enabled from sys_user where username=#{username}")
    MyUserDetails findByUsername(@Param("username") String username);

    //根据username查询用户角色
    @Select("select role_code from view_user_role where username=#{username}")
    String findRoleByUsername(@Param("username") String username);

    //根据用户角色查询权限
//    @Select("select url form view_role_menu where role_code=#{role_code}")
    @Select("select url from view_role_menu where role_code=#{role_code}")
    List<String> findAuthorityByRoleCodes(@Param("role_code") String role_code);
}
