package com.local.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author echo
 * @date 2023/09/25
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    /**
     * id
     */
    private Long  id;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

}
