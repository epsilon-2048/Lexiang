package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ErrorCode;
import com.epsilon.lx.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleResourceNotFoundException(NotFoundException e)
    {
        log.error(e.getMessage(), e);
        return new Result(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(value = NotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Result handleResourceNotAcceptableException(NotAcceptableException e)
    {
        log.error(e.getMessage(), e);
        return new Result(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(value = InternalServerErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleResourceInternalServerErrorException(InternalServerErrorException e)
    {
        log.error(e.getMessage(), e);
        return new Result(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleResourceGlobalException(GlobalException e)
    {
        log.error(e.getMessage(), e);
        return new Result(e.getCode(), e.getMessage());
    }
/*
    @ExceptionHandler(value = FastDFS400Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleResourceFastDFSException(FastDFS400Exception e)
    {
        log.error(e.getMessage(), e);
        return new Result(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = FastDFS500Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleResourceFastDFSException(FastDFS500Exception e)
    {
        log.error(e.getMessage(), e);
        return new Result(e.getCode(), e.getMessage());
    }*/

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleResourceException(Exception e)
    {
        log.error(e.getMessage(), e);
        return new Result(ErrorCode.ERROR.getCode(), ErrorCode.ERROR.getMsg());
    }
}
