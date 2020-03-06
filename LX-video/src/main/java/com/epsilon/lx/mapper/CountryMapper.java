package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.CountryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CountryMapper {
    int countByExample(CountryExample example);

    int deleteByExample(CountryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Country record);

    int insertSelective(Country record);

    List<Country> selectByExample(CountryExample example);

    Country selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Country record, @Param("example") CountryExample example);

    int updateByExample(@Param("record") Country record, @Param("example") CountryExample example);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}