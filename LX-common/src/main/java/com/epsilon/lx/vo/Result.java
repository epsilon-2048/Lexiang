package com.epsilon.lx.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 错误返回结果集
 */
@Data
//@Accessors(chain = true)
public class Result {

    private int code;
    private String content;

    public Result(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
