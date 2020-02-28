package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class NotFoundException extends GlobalException {
    public NotFoundException(ICodeEnum codeEnum) {
        super(codeEnum);
    }

    public NotFoundException(ICodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public NotFoundException(Integer code, String message) {
        super(code, message);
    }
}
