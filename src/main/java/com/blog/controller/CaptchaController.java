package com.blog.controller;

import com.blog.config.captcha.CaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author: Wenzhao
 * Date: 2021/8/1
 */
@RestController
public class CaptchaController {
    @Resource
    DefaultKaptcha captchaProducer;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void Captcha(HttpSession session, HttpServletResponse response) {
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");

        String captchaText = captchaProducer.createText();
        session.setAttribute("captchaCode",new CaptchaConfig(captchaText,2*60));
        try (ServletOutputStream out = response.getOutputStream();){
            BufferedImage captchaImage = captchaProducer.createImage(captchaText);
            ImageIO.write(captchaImage,"jpg",out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

