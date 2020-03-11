package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class NotAcceptableException extends GlobalException {
    public NotAcceptableException(ICodeEnum codeEnum) {
        super(codeEnum);
    }

    public NotAcceptableException(ICodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public NotAcceptableException(Integer code, String message) {
        super(code, message);
    }
}
