package com.epsilon.lx.service.comment.impl;

import com.epsilon.lx.dto.CommentDTO;
import com.epsilon.lx.entities.Commentary;
import com.epsilon.lx.entities.CommentaryExample;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.mapper.CommentaryMapper;
import com.epsilon.lx.service.comment.IVideoCommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class IVideoCommendServiceImpl implements IVideoCommendService {

    @Autowired
    private CommentaryMapper commentMapper;

    /**
     * 通过videoId获取评论列表
     *
     * @return
     */
    @Override
    public List<CommentDTO> getVideoCommendByVideoId(Video video) {

        CommentaryExample example = new CommentaryExample();
        example.createCriteria().andVideoIdEqualTo(video.getId());
        List<Commentary> commentList = commentMapper.selectByExample(example);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        //获取父评论
        for (Commentary c :
                commentList) {
            CommentDTO commentDTO = new CommentDTO();
            if (c.getGrade().equals("0")) {
                commentDTO.setFather(c);
                commentDTOS.add(commentDTO);
            }
        }
        //获取子评论
        for (Commentary comment :
                commentList) {
            if (comment.getGrade().equals("1")) {
                for (CommentDTO commentDTO :
                        commentDTOS) {
                    if (comment.getVideoId().equals(commentDTO.getFather().getVideoId())) {
                        if (commentDTO.getChildren() != null) {
                            commentDTO.getChildren().add(comment);
                        } else {
                            List<Commentary> children = new ArrayList<>();
                            children.add(comment);
                            commentDTO.setChildren(children);
                        }
                    }
                }
            }
        }
        return commentDTOS;
    }
}