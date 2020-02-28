package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class FastDFS400Exception extends GlobalException {
    public FastDFS400Exception(ICodeEnum codeEnum) {
        super(codeEnum);
    }

    public FastDFS400Exception(ICodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public FastDFS400Exception(Integer code, String message) {
        super(code, message);
    }
}
