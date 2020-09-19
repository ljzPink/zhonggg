package com.zhonggg.security.model;

import lombok.*;

import java.util.Set;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-09-19 15:35
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Role {
    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;
}
