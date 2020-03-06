package com.epsilon.lx.mapper;

import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoCategoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface VideoCategoryMapper {
    int countByExample(VideoCategoryExample example);

    int deleteByExample(VideoCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VideoCategory record);

    int insertSelective(VideoCategory record);

    List<VideoCategory> selectByExample(VideoCategoryExample example);

    VideoCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VideoCategory record, @Param("example") VideoCategoryExample example);

    int updateByExample(@Param("record") VideoCategory record, @Param("example") VideoCategoryExample example);

    int updateByPrimaryKeySelective(VideoCategory record);

    int updateByPrimaryKey(VideoCategory record);
}