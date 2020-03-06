package com.epsilon.lx.service.sort;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoType;

import java.util.List;

/**
 * 影视排行服务接口
 */
public interface IVideoSortService {


    /**
     *  忽视影视分类，获取点赞排行前top条
     * @param top 条数
     * @return
     */
    List<Video> getLikesListByParameters(int top);

    /**
     *  根据影视类型，获取点赞排行前top条
     * @param top 条数
     * @param videoType 影视类型
     * @return
     */
    List<Video> getLikesListByParameters(int top, VideoType videoType);

    /**
     * 根据影视类别，获取点赞排行前top条
     * @param top 条数
     * @param videoCategory  影视类别
     * @return
     */
    List<Video> getLikesListByParameters(int top, VideoCategory videoCategory);

    /**
     * 根据影视生产地， 获取点赞排行前top条
     * @param top 条数
     * @param country 生产地 国家级别
     * @return
     */
    List<Video> getLikesListByParameters(int top, Country country);

    /**
     *  忽视影视分类，获取关注排行前top条
     * @param top 条数
     * @return
     */
    List<Video> getFollowsListByParameters(int top);

    /**
     *  根据影视类型，获取关注排行前top条
     * @param top 条数
     * @param videoType 影视类型
     * @return
     */
    List<Video> getFollowsListByParameters(int top, VideoType videoType);

    /**
     * 根据影视类别，获取关注排行前top条
     * @param top 条数
     * @param videoCategory  影视类别
     * @return
     */
    List<Video> getFollowsListByParameters(int top, VideoCategory videoCategory);

    /**
     * 根据影视生产地， 获取关注排行前top条
     * @param top 条数
     * @param country 生产地 国家级别
     * @return
     */
    List<Video> getFollowsListByParameters(int top, Country country);

    /**
     *  忽视影视分类，获取播放排行热搜前top条
     * @param top 条数
     * @return
     */
    List<Video> getViewsListByParameters(int top);

    /**
     *  根据影视类型，获取播放排行前top条
     * @param top 条数
     * @param videoType 影视类型
     * @return
     */
    List<Video> getViewsListByParameters(int top, VideoType videoType);

    /**
     * 根据影视类别，获取播放排行前top条
     * @param top 条数
     * @param videoCategory  影视类别
     * @return
     */
    List<Video> getViewsListByParameters(int top, VideoCategory videoCategory);

    /**
     * 根据影视生产地， 获取播放排行前top条
     * @param top 条数
     * @param country 生产地 国家级别
     * @return
     */
    List<Video> getViewsListByParameters(int top, Country country);


    /**
     *  忽视影视分类，获取热搜前top条
     * @param top 条数
     * @return
     */
    List<Video> getHotSearchListByParameters(int top);

    /**
     *  根据影视类型，获取热搜前top条
     * @param top 条数
     * @param videoType 影视类型
     * @return
     */
    List<Video> getHotSearchListByParameters(int top, VideoType videoType);

    /**
     * 根据影视类别，获取热搜前top条
     * @param top 条数
     * @param videoCategory  影视类别
     * @return
     */
    List<Video> getHotSearchListByParameters(int top, VideoCategory videoCategory);

    /**
     * 根据影视生产地， 获取热搜前top条
     * @param top 条数
     * @param country 生产地 国家级别
     * @return
     */
    List<Video> getHotSearchListByParameters(int top, Country country);


}
