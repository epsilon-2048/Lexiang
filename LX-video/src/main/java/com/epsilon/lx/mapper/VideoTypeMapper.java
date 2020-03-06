package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.VideoType;
import com.epsilon.lx.entities.VideoTypeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoTypeMapper {
    int countByExample(VideoTypeExample example);

    int deleteByExample(VideoTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VideoType record);

    int insertSelective(VideoType record);

    List<VideoType> selectByExample(VideoTypeExample example);

    VideoType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VideoType record, @Param("example") VideoTypeExample example);

    int updateByExample(@Param("record") VideoType record, @Param("example") VideoTypeExample example);

    int updateByPrimaryKeySelective(VideoType record);

    int updateByPrimaryKey(VideoType record);
}