package com.epsilon.lx.controller;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoType;
import com.epsilon.lx.service.recommend.IVideoRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private IVideoRecommendService videoRecommendService;

    //获取各种推荐

    //无条件
    @GetMapping("/videos/{top}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取top条推荐")
    public List<Video> getRecommendedVideos(@PathVariable(value = "top") int top){
       return videoRecommendService.getListByParameters(top);
    }

    //类型
    @GetMapping("/videos/{top}/types/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取类型为id的top条推荐")
    public List<Video> getRecommendedVideosTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoType type = new VideoType();
        type.setId(id);
        return videoRecommendService.getListByParameters(top,type);
    }

    //国家
    @GetMapping("/videos/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取生产地为id的top条推荐")
    public List<Video> getRecommendedVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        Country country = new Country();
        country.setId(id);
        return videoRecommendService.getListByParameters(top,country);
    }

    //类别
    @GetMapping("/videos/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取类别为id的top条推荐")
    public List<Video> getRecommendedVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id){
        VideoCategory category = new VideoCategory();
        category.setId(id);
        return videoRecommendService.getListByParameters(top,category);
    }
}
