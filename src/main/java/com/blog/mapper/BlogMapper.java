package com.blog.mapper;

import com.blog.model.Blog;
import com.blog.model.MyUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: Wenzhao
 * Date: 2021/8/4
 */
public interface BlogMapper {
    @Insert("insert into blogs(writer,title,content,imgUrl) values(#{writer},#{title},#{content},#{imgUrl})")
    void write(Blog blog);

    @Select("select id,title,content,imgUrl,time from blogs where writer=#{username}")
    List<Blog> findByUsername(@Param("username") String username);

    @Select("select id from blogs where writer=#{username}")
    List<Integer> findBlogIdByUsername(@Param("username") String username);

    @Select("select id,title,content,imgUrl,time from blogs where id=#{id}")
    Blog findByBlogId(@Param("id") int id);

    @Delete("delete from blogs where id=#{id}")
    void delete(@Param("id") int id);

    @Update("update blogs set title=#{title}, content=#{content}, imgUrl=#{imgUrl} where id=#{id}")
    void modifyIncludeUrl(Blog blog);

    @Update("update blogs set title=#{title}, content=#{content} where id=#{id}")
    void modifyExcludeUrl(Blog blog);
}
