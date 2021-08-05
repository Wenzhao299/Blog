package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.MyUser;
import com.blog.model.MyUserDetails;
import com.blog.service.BlogService;
import com.blog.utils.OSSClientUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author: Wenzhao
 * Date: 2021/8/4
 */
@Controller
public class BlogController {
    @Resource
    BlogService blogService;

    @PostMapping("/write")
    public String write(Blog blog, @RequestParam(value="image",required=false)MultipartFile multipartFile, Model model, HttpSession session) {
        OSSClientUtil ossClientUtil = new OSSClientUtil();
        String username = (String) session.getAttribute("username");
        String imgUrl = "";
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imgUrl = ossClientUtil.fileUpload(file,username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Blog new_blog = new Blog();
        new_blog.setWriter(username).setTitle(blog.getTitle()).setContent(blog.getContent()).setImgUrl(imgUrl);
        blogService.write(new_blog);

        File f = new File(file.toURI());
        if (f.delete()){
            System.out.println("缓存文件删除成功！");
        }else {
            System.out.println("缓存文件删除失败!");
        }

        return "redirect:/home";
    }

    @GetMapping("/blog")
    public String findByBlogId(int id, HttpSession session, Model model){
        List<Integer> list = blogService.findBlogIdByUsername((String) session.getAttribute("username"));
        Blog blog = blogService.findByBlogId(id);
        if (list.contains(id)) {
            model.addAttribute("blog",blog);
            return "/blog";
        }else {
            model.addAttribute("msg","页面走丢了！");
            return "redirect:/home";
        }
    }

}
