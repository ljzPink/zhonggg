package com.zhonggg.security.model;

import lombok.*;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-09-19 15:36
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Permissions {
    private String id;
    private String permissionsName;
}
