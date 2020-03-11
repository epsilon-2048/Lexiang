package com.epsilon.lx.service.sort.impl;

import com.epsilon.lx.entities.*;
import com.epsilon.lx.mapper.VideoMapper;
import com.epsilon.lx.service.sort.IVideoSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IVideoSortServiceImpl implements IVideoSortService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 忽视影视分类，获取点赞排行前top条
     *
     * @param top 条数
     * @return
     */
    @Override
    public List<Video> getLikesListByParameters(int top) {
        return getListByParameters(top, OperationType.LIKES,null,null,null);
    }

    /**
     * 根据影视类型，获取点赞排行前top条
     *
     * @param top       条数
     * @param videoType 影视类型
     * @return
     */
    @Override
    public List<Video> getLikesListByParameters(int top, VideoType videoType) {
        return getListByParameters(top, OperationType.LIKES,videoType,null,null);
    }

    /**
     * 根据影视类别，获取点赞排行前top条
     *
     * @param top           条数
     * @param videoCategory 影视类别
     * @return
     */
    @Override
    public List<Video> getLikesListByParameters(int top, VideoCategory videoCategory) {
        return getListByParameters(top, OperationType.LIKES,null,videoCategory,null);
    }

    /**
     * 根据影视生产地， 获取点赞排行前top条
     *
     * @param top     条数
     * @param country 生产地 国家级别
     * @return
     */
    @Override
    public List<Video> getLikesListByParameters(int top, Country country) {
        return getListByParameters(top, OperationType.LIKES,null,null,country);
    }

    /**
     * 忽视影视分类，获取关注排行前top条
     *
     * @param top 条数
     * @return
     */
    @Override
    public List<Video> getFollowsListByParameters(int top) {
        return getListByParameters(top, OperationType.FOLLOWS,null,null,null);
    }

    /**
     * 根据影视类型，获取关注排行前top条
     *
     * @param top       条数
     * @param videoType 影视类型
     * @return
     */
    @Override
    public List<Video> getFollowsListByParameters(int top, VideoType videoType) {
        return getListByParameters(top, OperationType.FOLLOWS,videoType,null,null);
    }

    /**
     * 根据影视类别，获取关注排行前top条
     *
     * @param top           条数
     * @param videoCategory 影视类别
     * @return
     */
    @Override
    public List<Video> getFollowsListByParameters(int top, VideoCategory videoCategory) {
        return getListByParameters(top, OperationType.FOLLOWS,null,videoCategory,null);
    }

    /**
     * 根据影视生产地， 获取关注排行前top条
     *
     * @param top     条数
     * @param country 生产地 国家级别
     * @return
     */
    @Override
    public List<Video> getFollowsListByParameters(int top, Country country) {
        return getListByParameters(top, OperationType.FOLLOWS,null,null,country);
    }

    /**
     * 忽视影视分类，获取播放排行前top条
     *
     * @param top 条数
     * @return
     */
    @Override
    public List<Video> getViewsListByParameters(int top) {
        return getListByParameters(top, OperationType.VIEWS,null,null,null);
    }

    /**
     * 根据影视类型，获取播放排行前top条
     *
     * @param top       条数
     * @param videoType 影视类型
     * @return
     */
    @Override
    public List<Video> getViewsListByParameters(int top, VideoType videoType) {
        return getListByParameters(top, OperationType.VIEWS,videoType,null,null);

    }

    /**
     * 根据影视类别，获取播放排行前top条
     *
     * @param top           条数
     * @param videoCategory 影视类别
     * @return
     */
    @Override
    public List<Video> getViewsListByParameters(int top, VideoCategory videoCategory) {
        return getListByParameters(top, OperationType.VIEWS,null,videoCategory,null);

    }

    /**
     * 根据影视生产地， 获取播放排行前top条
     *
     * @param top     条数
     * @param country 生产地 国家级别
     * @return
     */
    @Override
    public List<Video> getViewsListByParameters(int top, Country country) {
        return getListByParameters(top, OperationType.VIEWS,null,null,country);

    }

    /**
     * 忽视影视分类，获取热搜前top条
     *
     * @param top 条数
     * @return
     */
    @Override
    public List<Video> getHotSearchListByParameters(int top) {
        return getListByParameters(top, OperationType.HOT_SEARCH,null,null,null);

    }

    /**
     * 根据影视类型，获取热搜前top条
     *
     * @param top       条数
     * @param videoType 影视类型
     * @return
     */
    @Override
    public List<Video> getHotSearchListByParameters(int top, VideoType videoType) {
        return getListByParameters(top, OperationType.HOT_SEARCH,videoType,null,null);

    }

    /**
     * 根据影视类别，获取热搜前top条
     *
     * @param top           条数
     * @param videoCategory 影视类别
     * @return
     */
    @Override
    public List<Video> getHotSearchListByParameters(int top, VideoCategory videoCategory) {
        return getListByParameters(top, OperationType.HOT_SEARCH,null,videoCategory,null);

    }

    /**
     * 根据影视生产地， 获取热搜前top条
     *
     * @param top     条数
     * @param country 生产地 国家级别
     * @return
     */
    @Override
    public List<Video> getHotSearchListByParameters(int top, Country country) {
        return getListByParameters(top, OperationType.HOT_SEARCH,null,null,country);

    }

    /**
     * 根据参数获取video
     * @param top 条数
     * @param operationType 操作类型
     * @param videoType 影视类型
     * @param videoCategory 影视类别
     * @param country 生产地
     * @return
     */
    private List<Video> getListByParameters(int top, OperationType operationType, VideoType videoType, VideoCategory videoCategory, Country country){
        VideoExample videoExample = getVideoExample(top, operationType, videoType, videoCategory, country);
        return videoMapper.selectByExample(videoExample);
    }

    private VideoExample getVideoExample(int top, OperationType operationType, VideoType videoType, VideoCategory videoCategory, Country country) {
        VideoExample videoExample = new VideoExample();
        VideoExample.Criteria videoExampleCriteria =  videoExample.createCriteria();

        if (null != videoCategory) {
            videoExampleCriteria.andCategoryIdEqualTo(videoCategory.getId());
        }
        if (null != country) {
            videoExampleCriteria.andCountryIdEqualTo(country.getId());
        }
        if (null != videoType) {
            videoExampleCriteria.andTypeIdEqualTo(videoType.getId());
        }

        switch (operationType) {
            case VIEWS: videoExample.setOrderByClause("views desc");break;
            case LIKES: videoExample.setOrderByClause("likes desc");break;
            case FOLLOWS: videoExample.setOrderByClause("follows desc");break;
            case HOT_SEARCH: videoExample.setOrderByClause("search_count desc");break;
            case ALL: break;
            default: break;
        }
        videoExample.setOffset(0);
        videoExample.setLimit(top);
        return videoExample;
    }

    /**
     * 操作类型
     */
    private enum OperationType {
        /**
         * 热搜
         */
        HOT_SEARCH,

        /**
         * 点赞
         */
        LIKES,

        /**
         * 关注
         */
        FOLLOWS,

        /**
         * 播放
         */
        VIEWS,

        /**
         * 所有
         */
        ALL;
    }
}
