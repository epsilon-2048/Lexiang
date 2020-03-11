package com.epsilon.lx.service;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(value = "LX-VIDEO")
public interface AssortClientService {


    //获取所有类型
    @GetMapping("/assort/types")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoType> getTypes();

    //获取所有分类
    @GetMapping("/assort/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoCategory> getCategories();
    //获取所有国家
    @GetMapping("/assort/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> getCountries();

    //插入类型
    @PostMapping("/assort/types")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTypes(@RequestBody List<VideoType> types);
    //插入国家
    @PostMapping("/assort/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCountries(@RequestBody List<Country> countries);

    //插入分类
    @PostMapping("/assort/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategories(@RequestBody List<VideoCategory> categories);
}
