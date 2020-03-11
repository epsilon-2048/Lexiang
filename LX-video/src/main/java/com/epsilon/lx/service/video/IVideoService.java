package com.epsilon.lx.service.video;

import com.epsilon.lx.dto.VideoDTO;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.exception.NotFoundException;

import java.util.List;

/**
 * 影视资源的服务接口
 */
public interface IVideoService {

    /**
     * 上传影视
     * @param videoDTO 影视数据传输对象
     * @return
     */
    VideoDTO upload(VideoDTO videoDTO);

    /**
     * 根据video更新
     *
     * @param video 影视对象
     * @return
     */
    boolean update(Video video);
    /**
     * 批量上传影视
     * @param videoDTOList 影视数据传输对象列表
     * @return
     */
    List<VideoDTO> upload(List<VideoDTO> videoDTOList);

    /**
     *  获取解码后的资源存储路径
     * @param encodePath  编码路径
     * @return 解码路径
     */
    String getDecodePath(String encodePath);

    /**
     * 按条件搜索
     * @param videoDTO 条件
     * @return 影视数据传输对象列表
     */
    List<VideoDTO> search(VideoDTO videoDTO) throws NotFoundException;

    /**
     * 按条件搜索
     * @param video 条件
     * @return 影视实体对象列表
     */
    List<Video> search(Video video);

}
