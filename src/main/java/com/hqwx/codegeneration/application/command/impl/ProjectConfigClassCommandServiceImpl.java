package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.ProjectConfigClassDTO;
import com.hqwx.codegeneration.shared.utils.DataTimeUtils;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import com.hqwx.codegeneration.shared.utils.FreeMarkerUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/ProjectConfigClassCommandServiceImpl.class */
public class ProjectConfigClassCommandServiceImpl implements ProjectConfigClassCommandService {
    @Override // com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService
    public int generateConfigClass(CodeGeneratorConfigCommand configCommand) {
        ProjectConfigClassDTO configClass = syncCheckProjectConfigClass(configCommand);
        int updateNum = generateSwaggerConfig(configCommand, configClass);
        return updateNum + generateMybatisPlusConfig(configCommand, configClass) + generateWebConfig(configCommand, configClass) + generateLogTraceConfig(configCommand, configClass) + generateCacheConfig(configCommand, configClass);
    }

    @Override // com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService
    public int generateSwaggerConfig(CodeGeneratorConfigCommand configCommand, ProjectConfigClassDTO configClass) {
        if (configClass == null || StringUtils.isBlank(configClass.getConfigClassMainDirectory()) || configClass.getSwaggerExistFlag() == 1) {
            return 0;
        }
        String configDirectory = configClass.getConfigClassMainDirectory() + "swagger/";
        FileUtils.checkAndCreateFileDirectory(configDirectory);
        int updateNum = 0;
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand, configClass);
        String classFilePath = configDirectory + "SwaggerConfig.java";
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassSwagger.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService
    public int generateMybatisPlusConfig(CodeGeneratorConfigCommand configCommand, ProjectConfigClassDTO configClass) {
        if (configClass == null || StringUtils.isBlank(configClass.getConfigClassMainDirectory()) || configClass.getMybatisPlusExistFlag() == 1) {
            return 0;
        }
        String configDirectory = configClass.getConfigClassMainDirectory() + "mybatisplus/";
        FileUtils.checkAndCreateFileDirectory(configDirectory);
        int updateNum = 0;
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand, configClass);
        String classFilePath = configDirectory + "TenantFilterProperties.java";
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassMyBatisTenantFilterProperties.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String classFilePath2 = configDirectory + "TenantIdContext.java";
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassMyBatisTenantIdContext.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        String classFilePath3 = configDirectory + "TenantHandlerImpl.java";
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassMyBatisTenantHandler.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        String classFilePath4 = configDirectory + "MyBatisPlusConfig.java";
        String classContent4 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassMyBatisPlus.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath4, classContent4)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService
    public int generateWebConfig(CodeGeneratorConfigCommand configCommand, ProjectConfigClassDTO configClass) {
        if (configClass == null || StringUtils.isBlank(configClass.getConfigClassMainDirectory()) || configClass.getWebControllerExistFlag() == 1) {
            return 0;
        }
        String configDirectory = configClass.getConfigClassMainDirectory() + "web/";
        FileUtils.checkAndCreateFileDirectory(configDirectory);
        int updateNum = 0;
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand, configClass);
        String classFilePath = configDirectory + "WebControllerExceptionAdvice.java";
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassWebExceptionAdvice.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String classFilePath2 = configCommand.getPackageMainDirectory() + "shared/constants/ProjectConstants.java";
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassConstants.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        String classFilePath3 = configDirectory + "WebControllerConfig.java";
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassWebController.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService
    public int generateLogTraceConfig(CodeGeneratorConfigCommand configCommand, ProjectConfigClassDTO configClass) {
        if (configClass == null || StringUtils.isBlank(configClass.getConfigClassMainDirectory())) {
            return 0;
        }
        String configDirectory = configClass.getConfigClassMainDirectory() + "log/";
        FileUtils.checkAndCreateFileDirectory(configDirectory);
        int updateNum = 0;
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand, configClass);
        String classFilePath = configDirectory + "LogTraceFilter.java";
        if (!FileUtils.checkFileIsExist(classFilePath) && configClass.getLogTraceExistFlag() == 0) {
            String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigLogTraceFilter.ftl", tempParamMap);
            if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
                updateNum = 0 + 1;
            }
        }
        String classFilePath2 = configDirectory + "FeignTraceInterceptor.java";
        if (!FileUtils.checkFileIsExist(classFilePath2) && configClass.getLogTraceExistFlag() == 0) {
            String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigLogTraceFeignInterceptor.ftl", tempParamMap);
            if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
                updateNum++;
            }
        }
        String classFilePath3 = configDirectory + "ControllerTraceInterceptor.java";
        if (!FileUtils.checkFileIsExist(classFilePath3) && !FileUtils.checkFileIsExist(configDirectory + "ControllerInterceptor.java")) {
            String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigLogTraceControllerInterceptor.ftl", tempParamMap);
            if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
                updateNum++;
            }
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService
    public int generateCacheConfig(CodeGeneratorConfigCommand configCommand, ProjectConfigClassDTO configClass) {
        if (configClass == null || StringUtils.isBlank(configClass.getConfigClassMainDirectory()) || configClass.getCacheExistFlag() == 1) {
            return 0;
        }
        String configDirectory = configClass.getConfigClassMainDirectory() + "cache/";
        FileUtils.checkAndCreateFileDirectory(configDirectory);
        int updateNum = 0;
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand, configClass);
        String classFilePath = configDirectory + "CacheConfig.java";
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "ProjectConfigClassCache.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        return updateNum;
    }

    private ProjectConfigClassDTO syncCheckProjectConfigClass(CodeGeneratorConfigCommand configCommand) {
        ProjectConfigClassDTO configClass = new ProjectConfigClassDTO();
        String configDirPath1 = configCommand.getPackageMainDirectory() + "infrastructure/config/";
        String configDirPath2 = configCommand.getPackageMainDirectory() + "config/";
        File configDir1 = new File(configDirPath1);
        File configDir2 = new File(configDirPath2);
        if (configDir1.exists() && configDir1.isDirectory()) {
            if (configDir1.listFiles() != null && configDir1.listFiles().length > 0) {
                configClass.setConfigClassMainDirectory(configDirPath1);
                configClass.setConfigClassPackagePath("infrastructure.config");
            }
            traverseAndParseConfigClass(configDir1, configClass);
        }
        if (configDir2.exists() && configDir2.isDirectory()) {
            if (StringUtils.isBlank(configClass.getConfigClassMainDirectory()) && configDir2.listFiles() != null && configDir2.listFiles().length > 0) {
                configClass.setConfigClassMainDirectory(configDirPath2);
                configClass.setConfigClassPackagePath("config");
            }
            traverseAndParseConfigClass(configDir2, configClass);
        }
        if (StringUtils.isBlank(configClass.getConfigClassMainDirectory())) {
            configClass.setConfigClassMainDirectory(configDirPath1);
            configClass.setConfigClassPackagePath("infrastructure.config");
        }
        return configClass;
    }

    private void traverseAndParseConfigClass(File configDir, ProjectConfigClassDTO configClass) {
        File[] subFileList = configDir.listFiles();
        if (subFileList == null || subFileList.length <= 0) {
            return;
        }
        for (File subFile : subFileList) {
            if (subFile.isFile()) {
                if (subFile.getName().lastIndexOf("java") >= 1) {
                    try {
                        InputStreamReader streamReader = new InputStreamReader(new FileInputStream(subFile), "UTF-8");
                        BufferedReader buffReader = new BufferedReader(streamReader);
                        boolean isConfigClassFlag = false;
                        for (String line = buffReader.readLine(); line != null; line = buffReader.readLine()) {
                            if (line.contains("@Configuration")) {
                                isConfigClassFlag = true;
                            }
                            if (isConfigClassFlag && line.contains("Swagger")) {
                                configClass.setSwaggerExistFlag(1);
                            }
                            if (line.contains("WebMvcConfigurationSupport")) {
                                configClass.setWebControllerExistFlag(1);
                            }
                            if (isConfigClassFlag && line.contains("Mybatis")) {
                                configClass.setMybatisPlusExistFlag(1);
                            }
                            if (isConfigClassFlag && line.contains(" PaginationInterceptor")) {
                                configClass.setMybatisPlusExistFlag(1);
                            }
                            if (isConfigClassFlag && line.contains("traceId")) {
                                configClass.setLogTraceExistFlag(1);
                            }
                            if (isConfigClassFlag && line.contains("Cache")) {
                                configClass.setCacheExistFlag(1);
                            }
                        }
                        buffReader.close();
                        streamReader.close();
                    } catch (Exception err) {
                        System.out.println("traverseAndParseConfigClass_error, file path: " + subFile.getAbsolutePath() + ", error: " + err.toString());
                    }
                }
            } else if (subFile.isDirectory()) {
                traverseAndParseConfigClass(subFile, configClass);
            }
        }
    }

    private Map<String, Object> buildTempParamMap(CodeGeneratorConfigCommand configCommand, ProjectConfigClassDTO configClass) {
        Map<String, Object> tempParamMap = new HashMap<>();
        tempParamMap.put("_currentDate", DataTimeUtils.findCurrentDateStr());
        tempParamMap.put("_currentTime", DataTimeUtils.findCurrentDateTimeStr());
        tempParamMap.put("_configCommand", configCommand);
        tempParamMap.put("_configClass", configClass);
        return tempParamMap;
    }
}
