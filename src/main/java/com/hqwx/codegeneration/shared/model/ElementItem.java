package com.hqwx.codegeneration.shared.model;

import java.io.Serializable;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/model/ElementItem.class */
public class ElementItem implements Serializable {
    private String code;
    private String name;

    public ElementItem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
