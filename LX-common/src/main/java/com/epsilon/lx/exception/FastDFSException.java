package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class FastDFSException extends GlobalException{

    public FastDFSException(ICodeEnum codeEnum) {
        super(codeEnum);
    }

    public FastDFSException(ICodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public FastDFSException(Integer code, String message) {
        super(code, message);
    }
}
