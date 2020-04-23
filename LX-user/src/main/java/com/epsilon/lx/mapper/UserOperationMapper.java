package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.UserOperation;
import com.epsilon.lx.entities.UserOperationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserOperationMapper {
    int countByExample(UserOperationExample example);

    int deleteByExample(UserOperationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserOperation record);

    int insertSelective(UserOperation record);

    List<UserOperation> selectByExample(UserOperationExample example);

    UserOperation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserOperation record, @Param("example") UserOperationExample example);

    int updateByExample(@Param("record") UserOperation record, @Param("example") UserOperationExample example);

    int updateByPrimaryKeySelective(UserOperation record);

    int updateByPrimaryKey(UserOperation record);
}