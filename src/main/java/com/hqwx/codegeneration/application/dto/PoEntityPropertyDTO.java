package com.hqwx.codegeneration.application.dto;

import java.io.Serializable;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/dto/PoEntityPropertyDTO.class */
public class PoEntityPropertyDTO implements Serializable {
    private String propertyType;
    private String propertyName;
    private String fieldName;
    private String propertyDesc;
    private Integer requiredFlag;
    private Integer position;

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPropertyDesc() {
        return this.propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public Integer getRequiredFlag() {
        return this.requiredFlag;
    }

    public void setRequiredFlag(Integer requiredFlag) {
        this.requiredFlag = requiredFlag;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
