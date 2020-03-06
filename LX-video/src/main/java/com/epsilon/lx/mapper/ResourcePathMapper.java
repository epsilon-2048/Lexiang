package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.ResourcePath;
import com.epsilon.lx.entities.ResourcePathExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourcePathMapper {
    int countByExample(ResourcePathExample example);

    int deleteByExample(ResourcePathExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResourcePath record);

    int insertSelective(ResourcePath record);

    List<ResourcePath> selectByExample(ResourcePathExample example);

    ResourcePath selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResourcePath record, @Param("example") ResourcePathExample example);

    int updateByExample(@Param("record") ResourcePath record, @Param("example") ResourcePathExample example);

    int updateByPrimaryKeySelective(ResourcePath record);

    int updateByPrimaryKey(ResourcePath record);
}