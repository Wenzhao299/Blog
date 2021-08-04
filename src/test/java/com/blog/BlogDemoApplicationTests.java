package com.blog;

import com.blog.utils.OSSClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
class BlogDemoApplicationTests {

    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
//        String p1 = passwordEncoder.encode("2018212194");
//        String p2 = passwordEncoder.encode("2018212194");
//        System.out.println(p1+" "+p2+" "+passwordEncoder.matches("2018212194",p1));
//        System.out.println(p1+" "+p2+" "+passwordEncoder.matches("2018212194",p2));
        OSSClientUtil ossClientUtil = new OSSClientUtil();
        File file = new File("E:\\OneDrive\\图片\\屏幕快照\\2021-05-23.png");
        String imgUrl = "";
        System.out.println(file.getName());
        try {
            imgUrl = ossClientUtil.fileUpload(file,"2018212194");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("imgUrl:"+imgUrl);
    }

}
