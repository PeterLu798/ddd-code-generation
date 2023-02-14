package com.hqwx.codegeneration.shared.exception;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/DataNotFoundException.class */
public class DataNotFoundException extends BaseException {
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Class srcClass) {
        super(message, srcClass);
    }

    public DataNotFoundException(String message, Class srcClass, String showMessage) {
        super(message, srcClass, showMessage);
    }

    public DataNotFoundException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message, srcClass, showMessage, printErrorLogFlag);
    }
}
