package com.lan.base.exception;

/**
 * 针对快速创建的自定义异常
 */
public class ApiQuickException extends BaseException {

    private static final long serialVersionUID = 5824989579880015128L;

    public ApiQuickException(String message) {
        super(message);
    }

    public ApiQuickException(int code, String msg) {
        super(code, msg);
    }

}
