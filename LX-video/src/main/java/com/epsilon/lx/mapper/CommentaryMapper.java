package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.Commentary;
import com.epsilon.lx.entities.CommentaryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentaryMapper {
    int countByExample(CommentaryExample example);

    int deleteByExample(CommentaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Commentary record);

    int insertSelective(Commentary record);

    List<Commentary> selectByExample(CommentaryExample example);

    Commentary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Commentary record, @Param("example") CommentaryExample example);

    int updateByExample(@Param("record") Commentary record, @Param("example") CommentaryExample example);

    int updateByPrimaryKeySelective(Commentary record);

    int updateByPrimaryKey(Commentary record);
}