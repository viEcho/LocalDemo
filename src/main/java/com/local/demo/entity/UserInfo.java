package com.local.demo.entity;

import com.local.demo.aop.Faker;
import com.local.demo.enums.FakerTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
    @Faker(fakerType = FakerTypeEnum.IGNORE)
    private Long  id;

    /**
     * 名称
     */
    @Faker(fakerType = FakerTypeEnum.NAME)
    private String name;

    /**
     * 密码
     */
    @Faker(fakerType = FakerTypeEnum.PWD)
    private String password;

    /**
     * 地址
     */
    @Faker(fakerType = FakerTypeEnum.ADDRESS)
    private String address;

    /**
     * 创建人
     */
    @Faker(customVal = "admin")
    private String createdBy;

    /**
     * 创建时间
     */
    @Faker(fakerType = FakerTypeEnum.NOW_TIME)
    private Date createdTime;

    /**
     * 更新人
     */
    @Faker(customVal = "admin")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Faker(fakerType = FakerTypeEnum.NOW_TIME)
    private Date updatedTime;

}
