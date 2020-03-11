package com.epsilon.lx.service;


import com.epsilon.lx.entities.Video;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(value = "LX-VIDEO")
public interface RecommendClientService {


    //无条件
    @GetMapping("/recommend/videos/{top}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getRecommendedVideos(@PathVariable(value = "top") int top);

    //类型
    @GetMapping("/recommend/videos/{top}/types/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getRecommendedVideosTypeID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //国家
    @GetMapping("/recommend/videos/{top}/counties/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getRecommendedVideosByCountryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);

    //类别
    @GetMapping("/recommend/videos/{top}/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getRecommendedVideosByCategoryID(
            @PathVariable(value = "top") int top,
            @PathVariable("id") Long id);
}
