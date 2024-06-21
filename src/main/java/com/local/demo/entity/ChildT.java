package com.local.demo.entity;

import java.util.Date;
import lombok.Data;

/**
 * TODO 请添加类注释
 *
 * @author vvEcho
 * @date 2024/06/20
 */
@Data
public class ChildT {
    /**
     * 主键
     */
    private Long id;

    private Long motherId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 批次日期
     */
    private Date batchDate;
}