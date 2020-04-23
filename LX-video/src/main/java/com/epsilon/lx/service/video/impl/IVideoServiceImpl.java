package com.epsilon.lx.service.video.impl;

import com.epsilon.lx.dto.VideoDTO;
import com.epsilon.lx.entities.ResourcePath;
import com.epsilon.lx.entities.ResourcePathExample;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.entities.VideoExample;
import com.epsilon.lx.enums.ErrorCode;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.mapper.ResourcePathMapper;
import com.epsilon.lx.mapper.VideoMapper;
import com.epsilon.lx.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IVideoServiceImpl implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ResourcePathMapper resourcePathMapper;

    @Autowired
    private PasswordEncoder encoder;



    /**
     * 上传影视
     *
     * @param videoDTO 影视数据传输对象
     * @return
     */
    @Override
    @Transactional
    public VideoDTO upload(VideoDTO videoDTO) {
        insert(videoDTO);
        return videoDTO;
    }
    /**
     * 根据video更新
     *
     * @param video 影视对象
     * @return
     */
    @Override
    public boolean update(Video video) {
        return videoMapper.updateByPrimaryKeySelective(video) > 0;
    }
    /**
     * 批量上传影视
     *
     * @param videoDTOList 影视数据传输对象列表
     * @return
     */
    @Override
    @Transactional
    public List<VideoDTO> upload(List<VideoDTO> videoDTOList) {
        for (VideoDTO videoDTO :
                videoDTOList) {
            insert(videoDTO);
        }
        return videoDTOList;
    }

    private void insert(VideoDTO videoDTO) {
        videoMapper.insertSelective(videoDTO.getVideo());
        for (ResourcePath rp :
                videoDTO.getResourcePathList()) {
            rp.setOwnerId(videoDTO.getVideo().getId());
            rp.setOwner(0);//0标识视频资源
            resourcePathMapper.insertSelective(rp);
        }
    }

    /**
     * 获取解码后的资源存储路径
     *
     * @param encodePath 编码路径
     * @return 解码路径
     */
    @Override
    public String getDecodePath(String encodePath) {
        return encodePath;
    }

    /**
     * 按条件搜索
     *
     * @param videoDTO 条件
     * @return 影视数据传输对象列表
     */
    @Override
    public List<VideoDTO> search(VideoDTO videoDTO) throws NotFoundException {
        VideoExample videoExample = getVideoExample(videoDTO.getVideo());
        List<Video> videos = videoMapper.selectByExample(videoExample);
        if (videos.isEmpty())
            throw new NotFoundException(ErrorCode.VIDEO_NOT_FOUND, String.valueOf(videoDTO.getVideo().getId()));
        List<VideoDTO> videoDTOList = new ArrayList<>(videos.size());
        for (Video v :
                videos) {
            VideoDTO videoDTO1 = new VideoDTO();
            videoDTO1.setVideo(v);
            ResourcePathExample example = new ResourcePathExample();
            example.createCriteria().andOwnerEqualTo(0).andOwnerIdEqualTo(v.getId());
            videoDTO1.setResourcePathList(resourcePathMapper.selectByExample(example));
            videoDTOList.add(videoDTO1);
        }
        return videoDTOList;
    }

    /**
     * 按条件搜索
     *
     * @param video 条件
     * @return 影视实体对象列表
     */
    @Override
    public List<Video> search(Video video) {
        return videoMapper.selectByExample(getVideoExample(video));
    }

    private VideoExample getVideoExample(Video video) {
        VideoExample videoExample = new VideoExample();
        if (video.getActors()!=null){
            videoExample.createCriteria().andActorsLike("%"+video.getActors()+"%");
        }
        if (video.getDirector()!=null){
            videoExample.createCriteria().andDirectorLike("%"+video.getDirector()+"%");
        }
        if (video.getName()!=null){
            videoExample.createCriteria().andNameLike("%"+video.getName()+"%");
        }
        if (video.getFree()!=null){
            videoExample.createCriteria().andFreeEqualTo(video.getFree());
        }
        if (video.getCategoryId()!=null){
            videoExample.createCriteria().andCategoryIdEqualTo(video.getCategoryId());
        }
        if (video.getCountryId()!=null){
            videoExample.createCriteria().andCountryIdEqualTo(video.getCountryId());
        }
        if (video.getTypeId()!=null){
            videoExample.createCriteria().andTypeIdEqualTo(video.getTypeId());
        }
        if (video.getId()!=null){
            videoExample.createCriteria().andIdEqualTo(video.getId());
        }
        videoExample.setOrderByClause("likes * 5 + follows * 5 + views * 2 + search_count desc");
        return videoExample;
    }


}
