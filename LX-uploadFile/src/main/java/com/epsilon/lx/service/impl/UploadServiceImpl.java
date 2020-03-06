package com.epsilon.lx.service.impl;

import com.epsilon.lx.exception.FastDFSException;
import com.epsilon.lx.exception.InternalServerErrorException;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.fastdfs.FastDFSCilent;
import com.epsilon.lx.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Service
@Slf4j
public class UploadServiceImpl implements IUploadService {

    private FastDFSCilent client = new FastDFSCilent();

    public String upload(MultipartFile file, Map<String,String> description) throws InternalServerErrorException, NotFoundException {
        String path;
        try {
            path = client.upload(file,description);
        } catch (FastDFSException e) {
            if (e.getCode() < 50000 )
                throw new NotFoundException(e.getCode(),e.getMessage());
            else
                throw new InternalServerErrorException(e.getCode(),e.getMessage());
        }
        log.info(path);
        return path;
    }

}
