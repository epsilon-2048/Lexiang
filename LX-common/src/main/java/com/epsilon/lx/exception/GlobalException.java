package com.epsilon.lx.exception;

import com.epsilon.lx.enums.ICodeEnum;

public class GlobalException extends Exception {

    private Integer code;

    public GlobalException(ICodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public GlobalException(ICodeEnum codeEnum, String message) {
        super(String.format(codeEnum.getMsg(), message));
        this.code = codeEnum.getCode();
    }
    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
