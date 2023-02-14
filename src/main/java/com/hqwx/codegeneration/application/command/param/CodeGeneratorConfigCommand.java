package com.hqwx.codegeneration.application.command.param;

import java.io.Serializable;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/param/CodeGeneratorConfigCommand.class */
public class CodeGeneratorConfigCommand implements Serializable {
    private String tempType;
    private String projectParentDirectory;
    private String projectMainDirectory;
    private String packageMainPath;
    private String packageMainDirectory;
    private String serviceType;
    private Integer overwriteExistFileFlag;
    private Integer batchProcessSupportFlag;
    private Integer onlyRepositoryFlag;
    private String aggregateName;
    private String poName;

    public String toString() {
        StringBuffer info = new StringBuffer("");
        info.append("tempType: ").append(this.tempType);
        info.append(", projectMainDirectory: ").append(this.projectMainDirectory);
        info.append(", packageMainPath: ").append(this.packageMainPath);
        info.append(", serviceType: ").append(this.serviceType);
        info.append(", overwriteExistFileFlag: ").append(this.overwriteExistFileFlag);
        info.append(", aggregateName: ").append(this.aggregateName);
        info.append(", poName: ").append(this.poName);
        return info.toString();
    }

    public String getTempType() {
        return this.tempType;
    }

    public void setTempType(String tempType) {
        this.tempType = tempType;
    }

    public String getProjectParentDirectory() {
        return this.projectParentDirectory;
    }

    public void setProjectParentDirectory(String projectParentDirectory) {
        this.projectParentDirectory = projectParentDirectory;
    }

    public String getProjectMainDirectory() {
        return this.projectMainDirectory;
    }

    public void setProjectMainDirectory(String projectMainDirectory) {
        this.projectMainDirectory = projectMainDirectory;
    }

    public String getPackageMainPath() {
        return this.packageMainPath;
    }

    public void setPackageMainPath(String packageMainPath) {
        this.packageMainPath = packageMainPath;
    }

    public String getPackageMainDirectory() {
        return this.packageMainDirectory;
    }

    public void setPackageMainDirectory(String packageMainDirectory) {
        this.packageMainDirectory = packageMainDirectory;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getOverwriteExistFileFlag() {
        return this.overwriteExistFileFlag;
    }

    public void setOverwriteExistFileFlag(Integer overwriteExistFileFlag) {
        this.overwriteExistFileFlag = overwriteExistFileFlag;
    }

    public Integer getBatchProcessSupportFlag() {
        return this.batchProcessSupportFlag;
    }

    public void setBatchProcessSupportFlag(Integer batchProcessSupportFlag) {
        this.batchProcessSupportFlag = batchProcessSupportFlag;
    }

    public Integer getOnlyRepositoryFlag() {
        return this.onlyRepositoryFlag;
    }

    public void setOnlyRepositoryFlag(Integer onlyRepositoryFlag) {
        this.onlyRepositoryFlag = onlyRepositoryFlag;
    }

    public String getAggregateName() {
        return this.aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public String getPoName() {
        return this.poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }
}
