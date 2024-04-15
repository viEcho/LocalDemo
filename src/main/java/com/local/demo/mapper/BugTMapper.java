package com.local.demo.mapper;

import com.local.demo.entity.BugT;

import org.apache.ibatis.annotations.Mapper;


/**
 * TODO 请添加类注释
 *
 * @author Jay
 * @date 2024/03/07
 */
@Mapper
public interface BugTMapper {
    int insert(BugT record);

    BugT selectByPrimaryKey(Long bugId);

    int updateByPrimaryKeySelective(BugT record);

    int updateByPrimaryKey(BugT record);
}