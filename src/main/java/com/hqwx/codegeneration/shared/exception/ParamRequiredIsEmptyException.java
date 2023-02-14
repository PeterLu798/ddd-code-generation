package com.hqwx.codegeneration.shared.exception;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/ParamRequiredIsEmptyException.class */
public class ParamRequiredIsEmptyException extends BaseException {
    public ParamRequiredIsEmptyException(String message) {
        super(message);
    }

    public ParamRequiredIsEmptyException(String message, Class srcClass) {
        super(message, srcClass);
    }

    public ParamRequiredIsEmptyException(String message, Class srcClass, String showMessage) {
        super(message, srcClass, showMessage);
    }

    public ParamRequiredIsEmptyException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message, srcClass, showMessage, printErrorLogFlag);
    }
}
