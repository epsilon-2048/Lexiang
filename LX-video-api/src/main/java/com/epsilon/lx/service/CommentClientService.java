package com.epsilon.lx.service;


import com.epsilon.lx.dto.CommentDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(value = "LX-VIDEO")
public interface CommentClientService {

    @GetMapping("video/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> getCommentDTOByVideoID(@PathVariable("id") Long id);


    @PostMapping("video/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable("id") Long id);
}
