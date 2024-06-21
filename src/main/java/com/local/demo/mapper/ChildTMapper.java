package com.local.demo.mapper;

import com.local.demo.entity.ChildT;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * TODO 请添加类注释
 *
 * @author vvEcho
 * @date 2024/06/20
 */
@Mapper
public interface ChildTMapper {

    int batchInsert(List<ChildT> list);


}