package com.epsilon.lx.enums;

public enum  ErrorCode implements ICodeEnum {


    /**
     * 用户不存在
     */
    USER_NOT_FOUND(40401,"用户 %s 不存在"),

    /**
     * 用户已存在
     */
    USER_ALREADY_EXIST(40402,"用户 %s 已存在"),

    /**
     * 电影不存在
     */
    MOVIE_NOT_FOUND(40410,"电影 %s 不存在"),

    ERROR(10000, "未知错误，请联系管理员");

    private int code;
    private String msg;

    ErrorCode(int code)
    {
        this.code = code;
    }
    ErrorCode(int code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
