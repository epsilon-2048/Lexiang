package com.epsilon.lx.controller;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.VideoCategory;
import com.epsilon.lx.entities.VideoType;
import com.epsilon.lx.service.assort.ICategoryService;
import com.epsilon.lx.service.assort.ICountryService;
import com.epsilon.lx.service.assort.IVideoTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@RequestMapping("/assort")
public class AssortController {


    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IVideoTypeService videoTypeService;

    //获取所有类型
    @GetMapping("/types")
    @ApiOperation(value = "获取所有类型列表")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoType> getTypes(){
        return videoTypeService.getListAll();
    }

    //获取所有分类
    @GetMapping("/categories")
    @ApiOperation(value = "获取所有分类列表")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoCategory> getCategories(){
        return categoryService.getListAll();
    }

    //获取所有国家
    @GetMapping("/countries")
    @ApiOperation(value = "获取所有生产地列表")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> getCountries(){
        return countryService.getListAll();
    }

    //插入类型
    @PostMapping("/types")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="新增类型")
    @ApiImplicitParam(name = "types", value = "类型实体", required = true, dataType = "VideoType")
    public void addTypes(@RequestBody List<VideoType> types){
        videoTypeService.addVideoTypeAll(types);
    }

    //插入国家
    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="新增生产地")
    @ApiImplicitParam(name = "countries", value = "生产地实体", required = true, dataType = "Country")
    public void addCountries(@RequestBody List<Country> countries){
        countryService.addCountryAll(countries);
    }

    //插入分类
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="新增类别")
    @ApiImplicitParam(name = "categories", value = "类别实体", required = true, dataType = "VideoCategory")
    public void addCategories(@RequestBody List<VideoCategory> categories){
        categoryService.addCategoryAll(categories);
    }
}
