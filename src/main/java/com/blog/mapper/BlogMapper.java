package com.blog.mapper;

import com.blog.model.Blog;
import com.blog.model.MyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Wenzhao
 * Date: 2021/8/4
 */
public interface BlogMapper {
    @Insert("insert into blogs(writer,title,content,imgUrl) values(#{writer},#{title},#{content},#{imgUrl})")
    void write(Blog blog);

    @Select("select id,title,content,imgUrl,time from blogs where writer = #{username}")
    List<Blog> findByUsername(@Param("username") String username);

    @Select("select id from blogs where writer = #{username}")
    List<Integer> findBlogIdByUsername(@Param("username") String username);

    @Select("select title,content,imgUrl,time from blogs where id = #{id}")
    Blog findByBlogId(@Param("id") int id);
}
