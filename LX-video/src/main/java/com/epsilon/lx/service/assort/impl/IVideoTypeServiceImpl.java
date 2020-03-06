package com.epsilon.lx.service.assort.impl;

import com.epsilon.lx.entities.VideoType;
import com.epsilon.lx.entities.VideoTypeExample;
import com.epsilon.lx.mapper.VideoTypeMapper;
import com.epsilon.lx.service.assort.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class IVideoTypeServiceImpl implements IVideoTypeService {

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    /**
     * 获取所有视频分类
     *
     * @return
     */
    @Override
    public List<VideoType> getListAll() {
        return videoTypeMapper.selectByExample(new VideoTypeExample());
    }

    /**
     * 通过id获取特定视频分类
     *
     * @param id
     * @return
     */
    @Override
    public VideoType getVideoTypeById(Long id) {
        return videoTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加新得视频分类
     *
     * @param videoType
     * @return
     */
    @Override
    @Transactional
    public VideoType addVideoType(VideoType videoType) {
        VideoTypeExample example = new VideoTypeExample();
        example.createCriteria().andNameEqualTo(videoType.getName());
        List<VideoType> countryList = videoTypeMapper.selectByExample(example);
        if (!countryList.isEmpty())
            return countryList.get(0);
        else {
            videoTypeMapper.insertSelective(videoType);
            return videoType;
        }

    }

    /**
     * 批量增加新得视频分类
     *
     * @param videoTypes
     * @return
     */
    @Override
    @Transactional
    public List<VideoType> addVideoTypeAll(List<VideoType> videoTypes) {
        List<VideoType> videoTypeList = new ArrayList<>(videoTypes.size());
        VideoTypeExample example = new VideoTypeExample();
        for (VideoType videoType :
                videoTypes) {
            example.createCriteria().andNameEqualTo(videoType.getName());
            List<VideoType> typeList = videoTypeMapper.selectByExample(example);
            if (!typeList.isEmpty())
                videoTypeList.add(typeList.get(0));
            else {
                videoTypeMapper.insertSelective(videoType);
                videoTypeList.add(videoType);
            }
        }
        return videoTypeList;

    }
}
