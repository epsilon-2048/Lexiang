package com.epsilon.lx.service.assort;

import com.epsilon.lx.entities.VideoCategory;

import java.util.List;

/**
 * 影视类别服务接口
 */
public interface ICategoryService {

    /**
     * 获取所有类别
     * @return
     */
    List<VideoCategory> getListAll();

    /**
     * 通过id获取特定类别
     * @param id
     * @return
     */
    VideoCategory getCategoryById(Long id);

    /**
     * 增加新得类别
     * @param category
     * @return
     */
    VideoCategory addCategory(VideoCategory category);

    /**
     * 批量增加新得类别
     * @param videoCategories
     * @return
     */
    List<VideoCategory> addCategoryAll(List<VideoCategory> videoCategories);

}
