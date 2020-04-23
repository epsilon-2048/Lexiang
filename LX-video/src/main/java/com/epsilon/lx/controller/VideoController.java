package com.epsilon.lx.controller;

import com.epsilon.lx.dto.VideoDTO;
import com.epsilon.lx.entities.Video;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.service.video.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private IVideoService videoService;

    //上传videoDTO
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "上传videoDTO:可批量")
   // @ApiImplicitParam(name = "videoDTOs", value = "视频DTO", required = true, dataType = "VideoDTO")
    public void addVideos(@RequestBody List<VideoDTO> videoDTOList){
        videoService.upload(videoDTOList);
    }

    //通过条件【video】获取不含视频资源的video列表
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "通过条件【video】获取不含视频资源的video列表")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "actors", value = "演员:可选", required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "类别id:可选", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "countryId", value = "生产地id:可选", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "typeId", value = "类型id:可选", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "director", value = "导演:可选", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "free", value = "是否免费0/1:可选", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "影视名字:可选", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "影视:可选", required = false,paramType = "query",  dataType = "Long")
    })
    public List<Video> getVideoNoUrlPathByParameters(
            @RequestParam(name = "actors",required = false) String actors,
            @RequestParam(name = "categoryId",required = false) Long categoryId,
            @RequestParam(name = "countryId",required = false) Long countryId,
            @RequestParam(name = "typeId",required = false) Long typeId,
            @RequestParam(name = "director",required = false) String director,
            @RequestParam(name = "free",required = false) String free,
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "id",required = false) Long id
    ) {
        //Video video = stringParamMap2Video(param);
        Video video = new Video();
        if (actors!=null && !actors.equals(""))
            video.setActors(actors);
        if (categoryId!=null)
            video.setCategoryId(categoryId);
        if (countryId!=null )
            video.setCountryId(countryId);
        if (director!=null && !director.equals(""))
            video.setDirector(director);
        if (free!=null && !free.equals(""))
            video.setFree(free);
        if (id!=null)
            video.setId(id);
        if (name!=null && !name.equals(""))
            video.setName(name);
        if (typeId!=null)
            video.setTypeId(typeId);
        return videoService.search(video);
    }

    //通过videoID获取含视频资源的videoDTO列表
    @GetMapping("/{id}")
    @ApiOperation(value = "通过videoID获取含视频资源的videoDTO列表")
    @ApiImplicitParam(name = "id", value = "视频id", required = true, paramType = "path", dataType = "Long")
    @ResponseStatus(HttpStatus.OK)
    public VideoDTO getVideoNoUrlPathByVideoID(@PathVariable("id") Long id, HttpServletRequest request) throws NotFoundException {
        System.out.println(request.getRequestURL().toString());
        VideoDTO videoDTO = new VideoDTO();
        Video video = new Video();
        video.setId(id);
        videoDTO.setVideo(video);
        return videoService.search(videoDTO).get(0);
    }

    private Video stringParamMap2Video( Map<String, String> param) {
        Video video = new Video();
        if (param.get("actors")!=null && !param.get("actors").equals(""))
            video.setActors(param.get("actors"));
        if (param.get("categoryId")!=null && !param.get("categoryId").equals(""))
            video.setCategoryId(Long.valueOf(param.get("categoryId")));
        if (param.get("countryId")!=null && !param.get("countryId").equals(""))
            video.setCountryId(Long.valueOf(param.get("countryId")));
        if (param.get("director")!=null && !param.get("director").equals(""))
            video.setDirector(param.get("director"));
        if (param.get("free")!=null && !param.get("free").equals(""))
            video.setFree(param.get("free"));
        if (param.get("id")!=null && !param.get("id").equals(""))
            video.setId(Long.valueOf(param.get("id")));
        if (param.get("name")!=null && !param.get("name").equals(""))
            video.setName(param.get("name"));
        if (param.get("typeId")!=null && !param.get("typeId").equals(""))
            video.setTypeId(Long.valueOf(param.get("typeId")));
        return video;
    }

}
