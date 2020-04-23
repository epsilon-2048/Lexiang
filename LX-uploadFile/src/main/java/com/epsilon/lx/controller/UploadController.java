package com.epsilon.lx.controller;

import com.epsilon.lx.exception.InternalServerErrorException;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.service.IUploadService;
import com.epsilon.lx.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import epsilon_2048.security.browser.support.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@Api
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @ApiOperation(value="上传", notes="已测试")
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestParam("file")MultipartFile multipartFile) throws InternalServerErrorException, NotFoundException, JsonProcessingException {
        return JsonUtil.obj2String(new SimpleResponse(uploadService.upload(multipartFile,null)));
    }
}
