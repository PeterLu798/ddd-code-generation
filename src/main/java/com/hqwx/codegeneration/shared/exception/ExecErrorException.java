package com.hqwx.codegeneration.shared.exception;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/ExecErrorException.class */
public class ExecErrorException extends BaseException {
    public ExecErrorException(String message) {
        super(message);
    }

    public ExecErrorException(String message, Class srcClass) {
        super(message, srcClass);
    }

    public ExecErrorException(String message, Class srcClass, String showMessage) {
        super(message, srcClass, showMessage);
    }

    public ExecErrorException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message, srcClass, showMessage, printErrorLogFlag);
    }
}
