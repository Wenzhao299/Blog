package com.blog.config.auth;

import com.blog.config.exception.AjaxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author: Wenzhao
 * Date: 2021/7/31
 */
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    //页面跳转的处理逻辑
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(objectMapper.writeValueAsString(AjaxResponse.userError("您已在另外一台电脑或浏览器登录，当前登录已下线！")));

    }
}
