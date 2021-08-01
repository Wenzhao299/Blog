package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class BlogDemoApplicationTests {

    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String p1 = passwordEncoder.encode("2018212194");
        String p2 = passwordEncoder.encode("2018212194");
        System.out.println(p1+" "+p2+" "+passwordEncoder.matches("2018212194",p1));
        System.out.println(p1+" "+p2+" "+passwordEncoder.matches("2018212194",p2));
    }

}
