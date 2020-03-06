package com.epsilon.lx.service.assort;

import com.epsilon.lx.entities.VideoType;

import java.util.List;

/**
 * 影视类型服务接口
 */
public interface IVideoTypeService {

    /**
     * 获取所有视频分类
     * @return
     */
    List<VideoType> getListAll();

    /**
     * 通过id获取特定视频分类
     * @return
     */
    VideoType getVideoTypeById(Long id);

    /**
     * 增加新得视频分类
     * @param videoType
     * @return
     */
    VideoType addVideoType(VideoType videoType);

    /**
     * 批量增加新得视频分类
     * @param videoType
     * @return
     */
    List<VideoType> addVideoTypeAll(List<VideoType> videoTypes);
}
