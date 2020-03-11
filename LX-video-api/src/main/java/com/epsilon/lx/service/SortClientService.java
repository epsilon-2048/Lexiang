package com.epsilon.lx.service;

import com.epsilon.lx.entities.Video;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(value = "LX-VIDEO")
public interface SortClientService {


    //点赞
    @GetMapping("/sort/likes/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getLikesVideos(@PathVariable(value = "top") int top);

    //点赞
    @GetMapping("/sort/likes/video/{top}/types/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getLikesVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
    //点赞
    @GetMapping("/sort/likes/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getLikesVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
    //点赞
    @GetMapping("/sort/likes/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getLikesVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //关注
    @GetMapping("/sort/follow/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getFollowsVideos(@PathVariable(value = "top") int top);

    //关注
    @GetMapping("/sort/follow/video/{top}/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getFollowsVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //关注
    @GetMapping("/sort/follow/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getFollowsVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //关注
    @GetMapping("/sort/follow/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getFollowsVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //播放
    @GetMapping("/sort/view/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getViewsVideos(@PathVariable(value = "top") int top);
    //播放
    @GetMapping("/sort/view/video/{top}/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getViewsVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
    //播放
    @GetMapping("/sort/view/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getViewsVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //播放
    @GetMapping("/sort/view/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getViewsVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
    //热搜
    @GetMapping("/sort/hotsearch/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getHotSearchVideos(@PathVariable(value = "top") int top);

    //热搜
    @GetMapping("/sort/hotsearch/video/{top}/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getHotSearchVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
    //热搜
    @GetMapping("/sort/hotsearch/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getHotSearchVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //热搜
    @GetMapping("/sort/hotsearch/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getHotSearchVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
}
