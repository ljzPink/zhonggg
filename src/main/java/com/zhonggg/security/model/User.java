package com.zhonggg.security.model;

import lombok.*;

import java.util.Set;

/**
 * @description: 用户表
 * @author: lijinzhong
 * @create: 2020-09-19 14:48
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class User {
    private String id;
    private String userName;
    private String password;
    private Set<Role> roles;
}
