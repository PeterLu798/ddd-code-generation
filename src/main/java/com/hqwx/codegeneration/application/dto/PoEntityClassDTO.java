package com.hqwx.codegeneration.application.dto;

import java.io.Serializable;
import java.util.List;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/dto/PoEntityClassDTO.class */
public class PoEntityClassDTO implements Serializable {
    private String packageMainPath;
    private String packageMainFileDirectory;
    private String poName;
    private String entityName;
    private String entityNameInitialLowercase;
    private String entityNameSplitStr;
    private String entityNameChinese;
    private String entityDesc;
    private String tableName;
    private String aggregateName;
    private String serviceType;
    private List<PoEntityPropertyDTO> propertyList;

    public String toString() {
        StringBuffer info = new StringBuffer("");
        info.append("packageMainPath: ").append(this.packageMainPath);
        info.append(", packageMainFileDirectory: ").append(this.packageMainFileDirectory);
        info.append(", poName: ").append(this.poName);
        info.append(", entityName: ").append(this.entityName);
        info.append(", entityNameChinese: ").append(this.entityNameChinese);
        info.append(", entityDesc: ").append(this.entityDesc);
        info.append(", tableName: ").append(this.tableName);
        info.append(", serviceType: ").append(this.serviceType);
        info.append(", aggregateName: ").append(this.aggregateName);
        return info.toString();
    }

    public String getPackageMainPath() {
        return this.packageMainPath;
    }

    public void setPackageMainPath(String packageMainPath) {
        this.packageMainPath = packageMainPath;
    }

    public String getPackageMainFileDirectory() {
        return this.packageMainFileDirectory;
    }

    public void setPackageMainFileDirectory(String packageMainFileDirectory) {
        this.packageMainFileDirectory = packageMainFileDirectory;
    }

    public String getPoName() {
        return this.poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityNameInitialLowercase() {
        return this.entityNameInitialLowercase;
    }

    public void setEntityNameInitialLowercase(String entityNameInitialLowercase) {
        this.entityNameInitialLowercase = entityNameInitialLowercase;
    }

    public String getEntityNameSplitStr() {
        return this.entityNameSplitStr;
    }

    public void setEntityNameSplitStr(String entityNameSplitStr) {
        this.entityNameSplitStr = entityNameSplitStr;
    }

    public String getEntityNameChinese() {
        return this.entityNameChinese;
    }

    public void setEntityNameChinese(String entityNameChinese) {
        this.entityNameChinese = entityNameChinese;
    }

    public String getEntityDesc() {
        return this.entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAggregateName() {
        return this.aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<PoEntityPropertyDTO> getPropertyList() {
        return this.propertyList;
    }

    public void setPropertyList(List<PoEntityPropertyDTO> propertyList) {
        this.propertyList = propertyList;
    }
}
