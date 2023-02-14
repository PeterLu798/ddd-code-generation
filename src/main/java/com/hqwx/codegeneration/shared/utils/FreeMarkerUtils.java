package com.hqwx.codegeneration.shared.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/utils/FreeMarkerUtils.class */
public class FreeMarkerUtils {
    private static Configuration defaultConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    private static Configuration dddConfig;
    private static Configuration dddHqwxConfig;

    static {
        defaultConfig.setDefaultEncoding("utf-8");
        defaultConfig.setClassForTemplateLoading(FreeMarkerUtils.class, "/freemarker");
        dddConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        dddConfig.setDefaultEncoding("utf-8");
        dddConfig.setClassForTemplateLoading(FreeMarkerUtils.class, "/freemarker/ddd_hqwx");
        dddHqwxConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        dddHqwxConfig.setDefaultEncoding("utf-8");
        dddHqwxConfig.setClassForTemplateLoading(FreeMarkerUtils.class, "/freemarker/ddd_hqwx");
    }

    public static String generate(String tempName, Map<String, Object> paramMap) {
        return generate(null, tempName, paramMap);
    }

    public static String generate(String tempType, String tempName, Map<String, Object> paramMap) {
        try {
            String tname = tempName.trim();
            if (tname.indexOf(".") < 0) {
                String str = tname + ".ftl";
            }
            String matchTempType = tempType != null ? tempType.toLowerCase().trim() : "";
            if (matchTempType.contains("hqwx")) {
                return generateByTemplate(dddHqwxConfig.getTemplate(tempName), paramMap);
            }
            if (matchTempType.equals("ddd")) {
                return generateByTemplate(dddConfig.getTemplate(tempName), paramMap);
            }
            return generateByTemplate(defaultConfig.getTemplate(tempName), paramMap);
        } catch (Exception err) {
            System.out.println("generate_by_freemarker_error, tempType: " + tempType + ", tempName: " + tempName + ", error: " + err.toString());
            return "";
        }
    }

    private static String generateByTemplate(Template template, Map<String, Object> paramMap) {
        if (template == null) {
            return "";
        }
        try {
            StringWriter writer = new StringWriter();
            template.process(paramMap, writer);
            return writer.toString();
        } catch (Exception err) {
            System.out.println("generateByTemplate_error, template.getName(): " + template.getName() + ", error: " + err.toString());
            return "";
        }
    }
}
