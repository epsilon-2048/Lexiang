package com.epsilon.lx.service;

import com.epsilon.lx.dto.VideoDTO;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.exception.NotFoundException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "LX-VIDEO")
public interface VideoClientServer {


    //上传videoDTO
    @PostMapping("/videos")
    @ResponseStatus(HttpStatus.CREATED)
    public void addVideos(@RequestBody List<VideoDTO> videoDTOList);

    @GetMapping("/videos")
    @ResponseStatus(HttpStatus.OK)
    List<Video> getVideoNoUrlPathByParameters(@RequestParam Map<String,String> param);

    //通过videoID获取含视频资源的videoDTO列表
    @GetMapping("/videos{id}")
    @ResponseStatus(HttpStatus.OK)
    VideoDTO getVideoNoUrlPathByVideoID(@PathVariable("id") Long id) throws NotFoundException;

}
