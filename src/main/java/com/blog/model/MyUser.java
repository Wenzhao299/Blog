package com.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: Wenzhao
 * Date: 2021/8/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MyUser {
    private Integer id;
    private String username;
    private String password;
    private Integer enabled;
}
