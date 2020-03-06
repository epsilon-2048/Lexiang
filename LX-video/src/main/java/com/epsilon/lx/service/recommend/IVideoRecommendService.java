package com.epsilon.lx.service.recommend;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoType;

import java.util.List;

/**
 * 影视推荐服务接口
 */
public interface IVideoRecommendService {

    /**
     *  忽视影视分类，获取推荐前top条
     * @param top 条数
     * @return
     */
    List<Video> getListByParameters(int top);

    /**
     *  根据影视类型，获取推荐前top条
     * @param top 条数
     * @param videoType 影视类型
     * @return
     */
    List<Video> getListByParameters(int top, VideoType videoType);

    /**
     * 根据影视类别，获取推荐前top条
     * @param top 条数
     * @param videoCategory  影视类别
     * @return
     */
    List<Video> getListByParameters(int top, VideoCategory videoCategory);

    /**
     * 根据影视生产地， 获取推荐前top条
     * @param top 条数
     * @param country 生产地 国家级别
     * @return
     */
    List<Video> getListByParameters(int top, Country country);

}
