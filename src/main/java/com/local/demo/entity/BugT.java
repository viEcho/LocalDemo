package com.local.demo.entity;

import lombok.Data;

/**
 * TODO 请添加类注释
 *
 * @author Jay
 * @date 2024/03/07
 */
@Data
public class BugT {
    /**
     * 主键
     */
    private Long bugId;

    /**
     * 人员
     */
    private String userName;

    /**
     * bug解决时长
     */
    private Integer bugTime;
}