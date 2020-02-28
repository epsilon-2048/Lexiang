package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class InternalServerErrorException extends GlobalException {
    public InternalServerErrorException(ICodeEnum codeEnum) {
        super(codeEnum);
    }

    public InternalServerErrorException(ICodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public InternalServerErrorException(Integer code, String message) {
        super(code, message);
    }
}
