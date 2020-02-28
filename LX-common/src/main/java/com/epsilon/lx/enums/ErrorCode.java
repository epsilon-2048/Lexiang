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

    FILE_NOT_EXIST(40420, "文件不存在"),

    ERROR(10000, "未知错误，请联系管理员"),



    FILE_PATH_ISNULL(50010, "文件路径为空"),

    FILE_ISNULL(50011, "文件输入流为空"),

    FILE_UPLOAD_FAILED(50012, "文件上传失败"),


    FILE_DOWNLOAD_FAILED(50014, "文件下载失败"),

    FILE_DELETE_FAILED(50015, "删除文件失败"),

    FILE_SERVER_CONNECTION_FAILED(50016, "文件服务器连接失败"),

    FILE_OUT_SIZE(50017, "文件超过大小"),

    FILE_TYPE_ERROR(50018, "类型错误: %s"),
    WAIT_IDLECONNECTION_TIMEOUT(50019,"获取连接超时");
/*
    FILE_TYPE_ERROR_DOC(40019, "类型错误"),

    FILE_TYPE_ERROR_VIDEO(40020, "音频类型错误"),

    FILE_TYPE_ERROR_COMPRESS(400, "压缩文件类型错误");*/



    private int code;
    private String msg;

    ErrorCode(int code)
    {
        this.code = code;
    }
    ErrorCode(int code,String msg) {
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
