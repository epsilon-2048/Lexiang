package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.UserRecharge;
import com.epsilon.lx.entities.UserRechargeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRechargeMapper {
    int countByExample(UserRechargeExample example);

    int deleteByExample(UserRechargeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRecharge record);

    int insertSelective(UserRecharge record);

    List<UserRecharge> selectByExample(UserRechargeExample example);

    UserRecharge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRecharge record, @Param("example") UserRechargeExample example);

    int updateByExample(@Param("record") UserRecharge record, @Param("example") UserRechargeExample example);

    int updateByPrimaryKeySelective(UserRecharge record);

    int updateByPrimaryKey(UserRecharge record);
}