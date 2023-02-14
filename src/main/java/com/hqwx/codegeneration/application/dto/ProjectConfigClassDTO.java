package com.hqwx.codegeneration.application.dto;

import java.io.Serializable;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/dto/ProjectConfigClassDTO.class */
public class ProjectConfigClassDTO implements Serializable {
    private String configClassMainDirectory;
    private String configClassPackagePath;
    private int swaggerExistFlag;
    private int mybatisPlusExistFlag;
    private int webControllerExistFlag;
    private int logTraceExistFlag;
    private int cacheExistFlag;

    public String getConfigClassMainDirectory() {
        return this.configClassMainDirectory;
    }

    public void setConfigClassMainDirectory(String configClassMainDirectory) {
        this.configClassMainDirectory = configClassMainDirectory;
    }

    public String getConfigClassPackagePath() {
        return this.configClassPackagePath;
    }

    public void setConfigClassPackagePath(String configClassPackagePath) {
        this.configClassPackagePath = configClassPackagePath;
    }

    public int getSwaggerExistFlag() {
        return this.swaggerExistFlag;
    }

    public void setSwaggerExistFlag(int swaggerExistFlag) {
        this.swaggerExistFlag = swaggerExistFlag;
    }

    public int getMybatisPlusExistFlag() {
        return this.mybatisPlusExistFlag;
    }

    public void setMybatisPlusExistFlag(int mybatisPlusExistFlag) {
        this.mybatisPlusExistFlag = mybatisPlusExistFlag;
    }

    public int getWebControllerExistFlag() {
        return this.webControllerExistFlag;
    }

    public void setWebControllerExistFlag(int webControllerExistFlag) {
        this.webControllerExistFlag = webControllerExistFlag;
    }

    public int getLogTraceExistFlag() {
        return this.logTraceExistFlag;
    }

    public void setLogTraceExistFlag(int logTraceExistFlag) {
        this.logTraceExistFlag = logTraceExistFlag;
    }

    public int getCacheExistFlag() {
        return this.cacheExistFlag;
    }

    public void setCacheExistFlag(int cacheExistFlag) {
        this.cacheExistFlag = cacheExistFlag;
    }
}
