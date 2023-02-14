package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.ProjectSettingCommandService;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.shared.utils.DataTimeUtils;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import com.hqwx.codegeneration.shared.utils.FreeMarkerUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/ProjectSettingCommandServiceImpl.class */
public class ProjectSettingCommandServiceImpl implements ProjectSettingCommandService {
    @Override // com.hqwx.codegeneration.application.command.ProjectSettingCommandService
    public int syncSetting(CodeGeneratorConfigCommand configCommand) {
        String resourcesDir = configCommand.getProjectMainDirectory() + "/src/main/resources/";
        if (!FileUtils.checkFileDirectoryIsExist(resourcesDir)) {
            System.out.println("syncProjectSetting, resourcesDir not correct, " + resourcesDir);
            return 0;
        }
        int updateNum = 0;
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand);
        String bootstrapFilePath = resourcesDir + "bootstrap.yml";
        if (!FileUtils.checkFileIsExist(bootstrapFilePath)) {
            String fileContent = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectBootstrap.ftl", tempParamMap);
            if (FileUtils.outputToFile(1, bootstrapFilePath, fileContent)) {
                updateNum = 0 + 1;
            }
            String logConfigFilePath = resourcesDir + "log4j2-dev.xml";
            String fileContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectLog4j2Dev.ftl", tempParamMap);
            if (FileUtils.outputToFile(1, logConfigFilePath, fileContent2)) {
                updateNum++;
            }
            String logConfigFilePath2 = resourcesDir + "log4j2-prod.xml";
            String fileContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectLog4j2Prod.ftl", tempParamMap);
            if (FileUtils.outputToFile(1, logConfigFilePath2, fileContent3)) {
                updateNum++;
            }
        }
        String pomFilePath = configCommand.getProjectMainDirectory() + "/pom.xml";
        String pomTempName = "ProjectPomForSpringBoot.ftl";
        if (StringUtils.isNotBlank(configCommand.getProjectParentDirectory())) {
            pomFilePath = configCommand.getProjectParentDirectory() + "/pom.xml";
            pomTempName = "ProjectPomForMaven.ftl";
        }
        if (FileUtils.checkFileIsExist(pomFilePath)) {
            Map<String, Object> pomParamMap = new HashMap<>();
            if (checkAndParseDefaultPom(pomFilePath, pomParamMap)) {
                tempParamMap.put("_pomParamMap", pomParamMap);
                String fileContent4 = FreeMarkerUtils.generate(configCommand.getTempType(), pomTempName, tempParamMap);
                if (FileUtils.outputToFile(1, pomFilePath, fileContent4)) {
                    updateNum++;
                }
            }
        }
        String readmeFilePath = configCommand.getProjectMainDirectory() + "/README.md";
        if (!FileUtils.checkFileIsExist(readmeFilePath)) {
        }
        return updateNum;
    }

    private Map<String, Object> buildTempParamMap(CodeGeneratorConfigCommand configCommand) {
        File projectDir;
        Map<String, Object> tempParamMap = new HashMap<>();
        tempParamMap.put("_currentDate", DataTimeUtils.findCurrentDateStr());
        tempParamMap.put("_currentTime", DataTimeUtils.findCurrentDateTimeStr());
        if (StringUtils.isNotBlank(configCommand.getProjectParentDirectory())) {
            projectDir = new File(configCommand.getProjectParentDirectory());
        } else {
            projectDir = new File(configCommand.getProjectMainDirectory());
        }
        tempParamMap.put("_projectName", projectDir.getName());
        if (configCommand.getOnlyRepositoryFlag() == null) {
            configCommand.setOnlyRepositoryFlag(1);
        }
        if (configCommand.getBatchProcessSupportFlag() == null) {
            configCommand.setBatchProcessSupportFlag(0);
        }
        if (configCommand.getOverwriteExistFileFlag() == null) {
            configCommand.setOverwriteExistFileFlag(0);
        }
        tempParamMap.put("_configCommand", configCommand);
        return tempParamMap;
    }

    private boolean checkAndParseDefaultPom(String pomFilePath, Map<String, Object> pomParamMap) {
        boolean overwriteFlag = false;
        try {
            int lineCount = 0;
            boolean defaultBootParent = false;
            boolean projectGroupFlag = false;
            String projectGroupId = "";
            String projectArtifactId = "";
            String projectName = "";
            String projectDesc = "";
            String projectJdkVersion = "";
            List<String> moduleList = new ArrayList<>();
            File pomFile = new File(pomFilePath);
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(pomFile), "UTF-8");
            BufferedReader buffReader = new BufferedReader(streamReader);
            String preLine = "";
            for (String line = buffReader.readLine(); line != null; line = buffReader.readLine()) {
                lineCount++;
                if (lineCount < 20) {
                    if (line.contains("spring-boot-starter-parent")) {
                        defaultBootParent = true;
                    }
                    if (line.contains("<groupId>")) {
                        if (preLine.contains("<parent>") || line.contains("org.springframework.boot")) {
                            projectGroupFlag = false;
                        } else {
                            projectGroupFlag = true;
                            projectGroupId = extractXmlValueByline(line);
                        }
                    }
                    if (projectGroupFlag) {
                        if (line.contains("<artifactId>")) {
                            projectArtifactId = extractXmlValueByline(line);
                        }
                        if (line.contains("<name>")) {
                            projectName = extractXmlValueByline(line);
                        }
                        if (line.contains("<description>")) {
                            projectDesc = extractXmlValueByline(line);
                        }
                    }
                }
                if (line.contains("<maven.compiler.source>")) {
                    projectJdkVersion = extractXmlValueByline(line);
                }
                if (line.contains("<java.version>")) {
                    projectJdkVersion = extractXmlValueByline(line);
                }
                if (line.contains("<module>")) {
                    moduleList.add(extractXmlValueByline(line));
                }
                preLine = line;
            }
            buffReader.close();
            streamReader.close();
            if (lineCount <= 32) {
                overwriteFlag = true;
            } else if (defaultBootParent && lineCount > 0 && lineCount <= 48) {
                overwriteFlag = true;
            }
            if (overwriteFlag) {
                pomParamMap.put("projectGroupId", projectGroupId);
                pomParamMap.put("projectArtifactId", projectArtifactId);
                pomParamMap.put("projectName", projectName);
                pomParamMap.put("projectDesc", projectDesc);
                pomParamMap.put("projectJdkVersion", projectJdkVersion);
                if (moduleList.size() > 0) {
                    pomParamMap.put("moduleList", moduleList);
                }
            }
        } catch (Exception err) {
            System.out.println("checkAndParseDefaultPom_error, pom.xml class path: " + pomFilePath + ", error: " + err.toString());
        }
        return overwriteFlag;
    }

    private String extractXmlValueByline(String line) {
        if (StringUtils.isBlank(line)) {
            return "";
        }
        String[] subArray = line.trim().split("<");
        for (String subStr : subArray) {
            if (subStr.contains(">")) {
                return subStr.substring(subStr.indexOf(">") + 1);
            }
        }
        return "";
    }
}
