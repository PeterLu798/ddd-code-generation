package com.hqwx.codegeneration.shared.exception;

import java.io.Serializable;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/exception/BaseException.class */
public abstract class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 0;
    protected Class srcClass;
    private String srcCode;
    protected String showMessage;
    protected boolean printErrorLogFlag;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Class srcClass) {
        super(message);
        this.srcClass = srcClass;
    }

    public BaseException(String message, Class srcClass, String showMessage) {
        super(message);
        this.srcClass = srcClass;
        this.showMessage = showMessage;
    }

    public BaseException(String message, Class srcClass, String showMessage, boolean printErrorLogFlag) {
        super(message);
        this.srcClass = srcClass;
        this.showMessage = showMessage;
        this.printErrorLogFlag = printErrorLogFlag;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuffer info = new StringBuffer(200);
        info.append(super.toString());
        if (this.srcClass != null) {
            info.append(", srcClass: ").append(this.srcClass.getName());
        }
        if (this.showMessage != null && this.showMessage.length() > 0) {
            info.append(", showMessage: ").append(this.showMessage);
        }
        return info.toString();
    }

    public Class getSrcClass() {
        return this.srcClass;
    }

    public String getSrcCode() {
        if (this.srcClass != null) {
            return this.srcClass.getSimpleName();
        }
        return "";
    }

    public String getShowMessage() {
        return this.showMessage;
    }

    public boolean isPrintErrorLogFlag() {
        return this.printErrorLogFlag;
    }
}
