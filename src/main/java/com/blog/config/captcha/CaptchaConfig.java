package com.blog.config.captcha;

import java.time.LocalDateTime;

/**
 * @author: Wenzhao
 * Date: 2021/8/1
 */
public class CaptchaConfig {
    private String code;
    private LocalDateTime expireTime;

    public CaptchaConfig(String captchaText, int expireAfterSeconds) {
        this.code = captchaText;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

}
