package com.hqwx.codegeneration.shared.enums;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/enums/DevServiceTypeEnum.class */
public enum DevServiceTypeEnum {
    API_APP("api_app", "C端应用API"),
    API_ADMIN("api_admin", "管理后台API"),
    BACKEND_SERVICE("backend_service", "纯后台服务");
    
    private String code;
    private String name;

    DevServiceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
