package com.hqwx.codegeneration.shared.exception;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/ValidationException.class */
public class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Class srcClass) {
        super(message, srcClass);
    }

    public ValidationException(String message, Class srcClass, String showMessage) {
        super(message, srcClass, showMessage);
    }

    public ValidationException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message, srcClass, showMessage, printErrorLogFlag);
    }
}
