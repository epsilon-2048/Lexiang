package com.epsilon.lx.controller;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoType;
import com.epsilon.lx.service.sort.IVideoSortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@RequestMapping("/sort")
public class SortController {

    @Autowired
    private IVideoSortService videoSortService;
    //获取各种排行

    //点赞
    @GetMapping("/likes/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频点赞量前top条")
    public List<Video> getLikesVideos(@PathVariable(value = "top") int top){
        return videoSortService.getLikesListByParameters(top);
    }

    //点赞
    @GetMapping("/likes/video/{top}/types/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类型为id的点赞量前top条")
    public List<Video> getLikesVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoType type = new VideoType();
        type.setId(id);
        return videoSortService.getLikesListByParameters(top,type);
    }

    //点赞
    @GetMapping("/likes/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类别为id的点赞量前top条")
    public List<Video> getLikesVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setId(id);
        return videoSortService.getLikesListByParameters(top,videoCategory);
    }

    //点赞
    @GetMapping("/likes/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频生产地为id的点赞量前top条")
    public List<Video> getLikesVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        Country country = new Country();
        country.setId(id);
        return videoSortService.getLikesListByParameters(top,country);
    }

    //关注
    @GetMapping("/follow/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频关注量前top条")
    public List<Video> getFollowsVideos(@PathVariable(value = "top") int top){
        return videoSortService.getFollowsListByParameters(top);
    }

    //关注
    @GetMapping("/follow/video/{top}/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类型为id的关注量前top条")
    public List<Video> getFollowsVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoType type = new VideoType();
        type.setId(id);
        return videoSortService.getFollowsListByParameters(top,type);
    }

    //关注
    @GetMapping("/follow/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类别为id的关注量前top条")
    public List<Video> getFollowsVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setId(id);
        return videoSortService.getFollowsListByParameters(top,videoCategory);
    }

    //关注
    @GetMapping("/follow/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频生产地为id的关注量前top条")
    public List<Video> getFollowsVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        Country country = new Country();
        country.setId(id);
        return videoSortService.getFollowsListByParameters(top,country);
    }

    //播放
    @GetMapping("/view/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频播放量前top条")
    public List<Video> getViewsVideos(@PathVariable(value = "top") int top){
        return videoSortService.getViewsListByParameters(top);
    }

    //播放
    @GetMapping("/view/video/{top}/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类型为id的播放量前top条")
    public List<Video> getViewsVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoType type = new VideoType();
        type.setId(id);
        return videoSortService.getViewsListByParameters(top,type);
    }

    //播放
    @GetMapping("/view/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类别为id的播放量前top条")
    public List<Video> getViewsVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setId(id);
        return videoSortService.getViewsListByParameters(top,videoCategory);
    }

    //播放
    @GetMapping("/view/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频产地为id的播放量前top条")
    public List<Video> getViewsVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        Country country = new Country();
        country.setId(id);
        return videoSortService.getViewsListByParameters(top,country);
    }

    //热搜
    @GetMapping("/hotsearch/video/{top}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频热搜量前top条")
    public List<Video> getHotSearchVideos(@PathVariable(value = "top") int top){
        return videoSortService.getHotSearchListByParameters(top);
    }

    //热搜
    @GetMapping("/hotsearch/video/{top}/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类型为id的热搜量前top条")
    public List<Video> getHotSearchVideosByTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoType type = new VideoType();
        type.setId(id);
        return videoSortService.getHotSearchListByParameters(top,type);
    }

    //热搜
    @GetMapping("/hotsearch/video/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频类别为id的热搜量前top条")
    public List<Video> getHotSearchVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setId(id);
        return videoSortService.getHotSearchListByParameters(top,videoCategory);
    }

    //热搜
    @GetMapping("/hotsearch/video/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取视频产地为id的热搜量前top条")
    public List<Video> getHotSearchVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        Country country = new Country();
        country.setId(id);
        return videoSortService.getHotSearchListByParameters(top,country);
    }
}
