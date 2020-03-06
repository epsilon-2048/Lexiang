package com.epsilon.lx.service.assort.impl;

import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoCategoryExample;
import com.epsilon.lx.mapper.VideoCategoryMapper;
import com.epsilon.lx.service.assort.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    private VideoCategoryMapper videoCategoryMapper;

    /**
     * 获取所有类别
     *
     * @return
     */
    @Override
    public List<VideoCategory> getListAll() {
        return videoCategoryMapper.selectByExample(new VideoCategoryExample());
    }

    /**
     * 通过id获取特定类别
     * @param id
     * @return
     */
    @Override
    public VideoCategory getCategoryById(Long id) {
        return videoCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加新得类别
     *
     * @param category
     * @return
     */
    @Override
    @Transactional
    public VideoCategory addCategory(VideoCategory category) {
        VideoCategoryExample example = new VideoCategoryExample();
        example.createCriteria().andNameEqualTo(category.getName());
        List<VideoCategory> categoryList = videoCategoryMapper.selectByExample(example);
        if (!categoryList.isEmpty())
            return categoryList.get(0);
        videoCategoryMapper.insertSelective(category);
        return category;
    }

    /**
     * 批量增加新得类别
     *
     * @param videoCategories
     * @return
     */

    @Override
    @Transactional
    public List<VideoCategory> addCategoryAll(List<VideoCategory> videoCategories) {
        List<VideoCategory> categoryList = new ArrayList<>(videoCategories.size());
        VideoCategoryExample example = new VideoCategoryExample();
        for (VideoCategory category :
                videoCategories) {
            example.createCriteria().andNameEqualTo(category.getName());
            List<VideoCategory> categories = videoCategoryMapper.selectByExample(example);
            if (!categories.isEmpty())
                categoryList.add(categories.get(0));
            else {
                videoCategoryMapper.insertSelective(category);
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
