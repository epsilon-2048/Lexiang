package com.epsilon.lx.service.comment;

import com.epsilon.lx.dto.CommentDTO;
import com.epsilon.lx.entities.Video;

import java.util.List;

public interface IVideoCommendService {
    /**
     * 通过videoId获取评论列表
     * @return
     */
    List<CommentDTO> getVideoCommendByVideoId(Video video);
}
