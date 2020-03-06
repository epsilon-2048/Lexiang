package com.epsilon.lx.controller;

import com.epsilon.lx.dto.CommentDTO;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.service.comment.IVideoCommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private IVideoCommendService videoCommendService;

    @ApiOperation(value = "根据视频id获取评论列表")
    @GetMapping("video/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> getCommentDTOByVideoID(@PathVariable("id") Long id){
        Video video = new Video();
        video.setId(id);
        return videoCommendService.getVideoCommendByVideoId(video);
    }

    @ApiOperation(value = "发表评论")
    @PostMapping("video/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable("id") Long id) {
        return;
    }
}
