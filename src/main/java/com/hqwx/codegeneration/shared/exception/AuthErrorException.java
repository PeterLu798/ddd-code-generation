package com.hqwx.codegeneration.shared.exception;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/AuthErrorException.class */
public class AuthErrorException extends BaseException {
    public AuthErrorException(String message) {
        super(message);
    }

    public AuthErrorException(String message, Class srcClass) {
        super(message, srcClass);
    }

    public AuthErrorException(String message, Class srcClass, String showMessage) {
        super(message, srcClass, showMessage);
    }

    public AuthErrorException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message, srcClass, showMessage, printErrorLogFlag);
    }
}
