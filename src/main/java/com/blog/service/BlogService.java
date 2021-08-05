package com.blog.service;

import com.blog.mapper.BlogMapper;
import com.blog.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Wenzhao
 * Date: 2021/8/4
 */
@Service
public class BlogService {
    @Resource
    BlogMapper blogMapper;

    public void write(Blog blog) {
        blogMapper.write(blog);
    }

    public List<Blog> findByUsername(String username) {
        return blogMapper.findByUsername(username);
    }

    public List<Integer> findBlogIdByUsername(String username) {
        return blogMapper.findBlogIdByUsername(username);
    }

    public Blog findByBlogId(int id) {
        return blogMapper.findByBlogId(id);
    }
}
