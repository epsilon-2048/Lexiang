package com.epsilon.lx.service;

import com.epsilon.lx.exception.FastDFSException;
import com.epsilon.lx.exception.InternalServerErrorException;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.fastdfs.FastDFSCilent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IUploadService {


    public String upload(MultipartFile file, Map<String,String> description) throws InternalServerErrorException, NotFoundException;
}
