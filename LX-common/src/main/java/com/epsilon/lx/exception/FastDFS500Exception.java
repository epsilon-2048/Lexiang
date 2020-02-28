package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class FastDFS500Exception extends GlobalException {
    public FastDFS500Exception(ICodeEnum codeEnum) {
        super(codeEnum);
    }

    public FastDFS500Exception(ICodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public FastDFS500Exception(Integer code, String message) {
        super(code, message);
    }
}
