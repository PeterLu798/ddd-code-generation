package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.PoParseCommandService;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;
import com.hqwx.codegeneration.application.dto.PoEntityPropertyDTO;
import com.hqwx.codegeneration.shared.exception.ExecErrorException;
import com.hqwx.codegeneration.shared.exception.ParamErrorException;
import com.hqwx.codegeneration.shared.exception.ParamRequiredIsEmptyException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/PoParseCommandServiceImpl.class */
public class PoParseCommandServiceImpl implements PoParseCommandService {
    @Override // com.hqwx.codegeneration.application.command.PoParseCommandService
    public PoEntityClassDTO parseAndAssembleEntity(String aggregateName, String poName, String packageMainPath, String packageMainFileDirectory) {
        PoEntityPropertyDTO property;
        if (StringUtils.isBlank(aggregateName) || StringUtils.isBlank(packageMainFileDirectory)) {
            System.out.println("parseAndAssembleEntity_failed, PO parameter is not correct, aggregateName: " + aggregateName + ", poName: " + poName + ", packageMainFileDirectory: " + packageMainFileDirectory);
            throw new ParamRequiredIsEmptyException("聚合名称不能为空", getClass());
        } else if (poName == null || !poName.contains("PO")) {
            System.out.println("parseAndAssembleEntity_failed, PO name is not correct, packageMainFileDirectory: " + packageMainFileDirectory + ", poName: " + poName);
            throw new ParamErrorException("PO类名称不正确", getClass());
        } else {
            String classPath = packageMainFileDirectory + "infrastructure/repository/model/" + poName;
            if (!classPath.endsWith(".java")) {
                classPath = classPath + ".java";
            }
            try {
                File classFile = new File(classPath);
                if (!classFile.exists() || !classFile.isFile()) {
                    System.out.println("parseAndAssembleEntity_failed, PO file not exist: " + classPath + ", poName: " + poName);
                    throw new ParamErrorException("PO类文件不存在, 请检查");
                }
                PoEntityClassDTO poEntity = new PoEntityClassDTO();
                poEntity.setPackageMainPath(packageMainPath);
                poEntity.setPackageMainFileDirectory(packageMainFileDirectory);
                poEntity.setAggregateName(aggregateName.toLowerCase().trim());
                if (poName.contains("/")) {
                    String[] subArray = poName.split("/");
                    poEntity.setPoName(subArray[subArray.length - 1]);
                } else {
                    poEntity.setPoName(poName);
                }
                poEntity.setEntityName(poEntity.getPoName().substring(0, poEntity.getPoName().length() - 2));
                poEntity.setEntityNameInitialLowercase(poEntity.getEntityName().substring(0, 1).toLowerCase() + poEntity.getEntityName().substring(1));
                poEntity.setEntityNameSplitStr(convertToLowercaseAndSplitChar(poEntity.getEntityName(), "-"));
                poEntity.setEntityNameChinese(poEntity.getEntityName());
                poEntity.setEntityDesc("");
                List<PoEntityPropertyDTO> propertyList = new ArrayList<>();
                poEntity.setPropertyList(propertyList);
                InputStreamReader streamReader = new InputStreamReader(new FileInputStream(classFile), "UTF-8");
                BufferedReader buffReader = new BufferedReader(streamReader);
                String prePreLine = "";
                String preLine = "";
                for (String line = buffReader.readLine(); line != null; line = buffReader.readLine()) {
                    if (line.contains("@ApiModel(")) {
                        Map<String, String> annotationItemMap = extractAnnotationItemMapByLine(line);
                        if (annotationItemMap.containsKey("value")) {
                            poEntity.setEntityNameChinese(annotationItemMap.get("value"));
                        }
                        if (annotationItemMap.containsKey("description")) {
                            poEntity.setEntityDesc(annotationItemMap.get("description"));
                        }
                    } else if (line.contains("@TableName(")) {
                        Map<String, String> annotationItemMap2 = extractAnnotationItemMapByLine(line);
                        if (annotationItemMap2.containsKey("value")) {
                            poEntity.setTableName(annotationItemMap2.get("value"));
                        }
                    } else if (line.contains("private") && line.contains(";") && (property = extractEntityPropertyByline(line, preLine, prePreLine)) != null) {
                        propertyList.add(property);
                    }
                    prePreLine = preLine;
                    preLine = line;
                }
                buffReader.close();
                streamReader.close();
                if (StringUtils.isNotBlank(poEntity.getTableName()) && propertyList.size() > 0) {
                    System.out.println("parseAndAssembleEntity_end, PO class path: " + classPath + ", poEntity: " + poEntity.toString());
                    return poEntity;
                }
                System.out.println("parseAndAssembleEntity_failed, PO class path: " + classPath + ", poEntity: " + poEntity.toString());
                throw new ExecErrorException("PO类关联的表名配置(@TableName)或属性项不完整", getClass());
            } catch (Exception err) {
                System.out.println("parseAndAssembleEntity_error, PO class path: " + classPath + ", error: " + err.toString());
                throw new ExecErrorException("PO类解析出错, " + err.getMessage(), getClass());
            }
        }
    }

    private PoEntityPropertyDTO extractEntityPropertyByline(String line, String preLine, String prePreLine) {
        if (line == null || !line.contains("private")) {
            return null;
        }
        String propertyInfo = extractMiddleSubStrByLine(line, "private", ";");
        String[] splitArray = propertyInfo.split(" ");
        if (splitArray.length != 2 || StringUtils.isBlank(splitArray[0]) || StringUtils.isBlank(splitArray[1])) {
            return null;
        }
        String propertyType = "";
        if (splitArray[0].equals("Long") || splitArray[0].equals("long")) {
            propertyType = "Long";
        } else if (splitArray[0].equals("Integer") || splitArray[0].equals("int")) {
            propertyType = "Integer";
        } else if (splitArray[0].equals("Double") || splitArray[0].equals("double")) {
            propertyType = "Double";
        } else if (splitArray[0].equals("Float") || splitArray[0].equals("float")) {
            propertyType = "Float";
        } else if (splitArray[0].equals("BigDecimal")) {
            propertyType = "BigDecimal";
        } else if (splitArray[0].equals("Date")) {
            propertyType = "Date";
        } else if (splitArray[0].equals("String")) {
            propertyType = "String";
        }
        if (StringUtils.isBlank(propertyType)) {
            return null;
        }
        PoEntityPropertyDTO property = new PoEntityPropertyDTO();
        property.setPropertyType(propertyType);
        property.setPropertyName(splitArray[1]);
        property.setFieldName(extractFieldNameByLine(property.getPropertyName(), preLine, prePreLine));
        property.setPropertyDesc("");
        property.setRequiredFlag(0);
        property.setPosition(300);
        String matchDescLine = "";
        if (preLine != null && preLine.contains("@ApiModelProperty(")) {
            matchDescLine = preLine;
        } else if (prePreLine != null && prePreLine.contains("@ApiModelProperty(")) {
            matchDescLine = prePreLine;
        }
        Map<String, String> annotationItemMap = extractAnnotationItemMapByLine(matchDescLine);
        if (annotationItemMap.containsKey("value")) {
            property.setPropertyDesc(annotationItemMap.get("value"));
        }
        if (annotationItemMap.containsKey("required") && Boolean.valueOf(annotationItemMap.get("required")).booleanValue()) {
            property.setRequiredFlag(1);
        }
        if (annotationItemMap.containsKey("position")) {
            property.setPosition(Integer.valueOf(annotationItemMap.get("position")));
        }
        return property;
    }

    private String extractFieldNameByLine(String propertyName, String preLine, String prePreLine) {
        String matchFieldLine = "";
        if (preLine != null && preLine.contains("@TableField(")) {
            matchFieldLine = preLine;
        } else if (prePreLine != null && prePreLine.contains("@TableField(")) {
            matchFieldLine = prePreLine;
        }
        if (!StringUtils.isNotBlank(matchFieldLine)) {
            if (StringUtils.isNotBlank(propertyName)) {
                return convertToLowercaseAndSplitChar(propertyName.trim(), "_");
            }
            return "";
        }
        Map<String, String> annotationItemMap = extractAnnotationItemMapByLine(matchFieldLine);
        if (annotationItemMap.containsKey("value")) {
            return annotationItemMap.get("value");
        }
        return "";
    }

    private String convertToLowercaseAndSplitChar(String name, String splitChar) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        StringBuffer connectionStr = new StringBuffer("");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < name.length()) {
                char char1 = name.charAt(i2);
                if (Character.isUpperCase(char1)) {
                    if (i2 > 0 && splitChar != null) {
                        connectionStr.append(splitChar.trim());
                    }
                    connectionStr.append(String.valueOf(char1).toLowerCase());
                } else {
                    connectionStr.append(char1);
                }
                i = i2 + 1;
            } else {
                return connectionStr.toString();
            }
        }
    }

    private Map<String, String> extractAnnotationItemMapByLine(String line) {
        Map<String, String> annotationItemMap = new HashMap<>();
        if (line == null || !line.contains("(") || !line.contains(")")) {
            return annotationItemMap;
        }
        String descInfo = extractMiddleSubStrByLine(line, "(", ")");
        if (StringUtils.isBlank(descInfo)) {
            return annotationItemMap;
        }
        if (descInfo.contains("=")) {
            String[] array1 = descInfo.split(",");
            for (String item : array1) {
                String[] array2 = item.split("=");
                if (array2.length == 2 && StringUtils.isNotBlank(array2[0]) && StringUtils.isNotBlank(array2[1])) {
                    annotationItemMap.put(array2[0].trim(), array2[1].trim().replaceAll("\"", ""));
                }
            }
        } else {
            annotationItemMap.put("value", descInfo.replaceAll("\"", ""));
        }
        return annotationItemMap;
    }

    private String extractMiddleSubStrByLine(String line, String start, String end) {
        if (StringUtils.isBlank(line) || StringUtils.isBlank(start) || StringUtils.isBlank(end)) {
            return "";
        }
        String middleSubStr = line.substring(line.indexOf(start) + start.length(), line.indexOf(end)).trim();
        if (StringUtils.isNotBlank(middleSubStr)) {
            middleSubStr = middleSubStr.replaceAll("  ", " ").replaceAll("  ", " ");
        }
        return middleSubStr;
    }
}
