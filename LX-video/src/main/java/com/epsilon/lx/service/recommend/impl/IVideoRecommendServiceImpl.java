package com.epsilon.lx.service.recommend.impl;

import com.epsilon.lx.entities.*;
import com.epsilon.lx.mapper.VideoMapper;
import com.epsilon.lx.service.recommend.IVideoRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IVideoRecommendServiceImpl implements IVideoRecommendService {

    @Autowired
    private VideoMapper videoMapper;


    /**
     * 忽视影视分类，获取推荐前top条
     *
     * @param top 条数
     * @return
     */
    @Override
    public List<Video> getListByParameters(int top) {
        return getListByParameters(top, null, null, null);
    }

    /**
     * 根据影视类型，获取推荐前top条
     *
     * @param top       条数
     * @param videoType 影视类型
     * @return
     */
    @Override
    public List<Video> getListByParameters(int top, VideoType videoType) {
        return getListByParameters(top, videoType, null, null);
    }

    /**
     * 根据影视类别，获取推荐前top条
     *
     * @param top           条数
     * @param videoCategory 影视类别
     * @return
     */
    @Override
    public List<Video> getListByParameters(int top, VideoCategory videoCategory) {
        return getListByParameters(top, null, videoCategory, null);
    }

    /**
     * 根据影视生产地， 获取推荐前top条
     *
     * @param top     条数
     * @param country 生产地 国家级别
     * @return
     */
    @Override
    public List<Video> getListByParameters(int top, Country country) {
        return getListByParameters(top, null, null, country);
    }

    /**
     * 根据参数获取video
     * @param top 条数
     * @param videoType 影视类型
     * @param videoCategory 影视类别
     * @param country 生产地
     * @return
     */
    private List<Video> getListByParameters(int top, VideoType videoType, VideoCategory videoCategory, Country country){
        VideoExample videoExample = getVideoExample(top, videoType, videoCategory, country);
        return videoMapper.selectByExample(videoExample);
    }

    private VideoExample getVideoExample(int top, VideoType videoType, VideoCategory videoCategory, Country country) {
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

        videoExample.setOrderByClause(getRecommendedOrderString());

        videoExample.setOffset(0);
        videoExample.setLimit(top);
        return videoExample;
    }

    /**
     * 获取推荐顺序字符串
     * @return
     */
    private String getRecommendedOrderString() {
        return "likes * 5 + follows * 5 + views * 2 + search_count desc";
    }

}
