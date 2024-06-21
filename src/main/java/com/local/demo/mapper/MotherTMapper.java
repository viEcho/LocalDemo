package com.local.demo.mapper;

import com.local.demo.entity.MotherT;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * TODO 请添加类注释
 *
 * @author vvEcho
 * @date 2024/06/20
 */
@Mapper
public interface MotherTMapper {
    int batchInsert(List<MotherT> record);
}