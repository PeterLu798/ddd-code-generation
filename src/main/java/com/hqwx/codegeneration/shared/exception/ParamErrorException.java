package com.hqwx.codegeneration.shared.exception;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/ParamErrorException.class */
public class ParamErrorException extends BaseException {
    public ParamErrorException(String message) {
        super(message);
    }

    public ParamErrorException(String message, Class srcClass) {
        super(message, srcClass);
    }

    public ParamErrorException(String message, Class srcClass, String showMessage) {
        super(message, srcClass, showMessage);
    }

    public ParamErrorException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message, srcClass, showMessage, printErrorLogFlag);
    }
}
