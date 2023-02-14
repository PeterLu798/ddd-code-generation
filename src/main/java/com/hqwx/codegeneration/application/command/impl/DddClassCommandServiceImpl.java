package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.DddClassCommandService;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;
import com.hqwx.codegeneration.shared.enums.DevServiceTypeEnum;
import com.hqwx.codegeneration.shared.utils.DataTimeUtils;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import com.hqwx.codegeneration.shared.utils.FreeMarkerUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/DddClassCommandServiceImpl.class */
public class DddClassCommandServiceImpl implements DddClassCommandService {
    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity) {
        Map<String, Object> tempParamMap = buildTempParamMap(configCommand, poEntity);
        String serviceType = configCommand.getServiceType() != null ? configCommand.getServiceType().trim() : "";
        Integer batchProcessSupportFlag = Integer.valueOf(configCommand.getBatchProcessSupportFlag() != null ? configCommand.getBatchProcessSupportFlag().intValue() : 0);
        Integer onlyRepositoryFlag = Integer.valueOf(configCommand.getOnlyRepositoryFlag() != null ? configCommand.getOnlyRepositoryFlag().intValue() : 1);
        int updateNum = 0 + generateDtoClass(configCommand, poEntity, tempParamMap) + generateDomainClass(configCommand, poEntity, tempParamMap) + generateRepositoryClass(configCommand, poEntity, tempParamMap);
        if (batchProcessSupportFlag.equals(1)) {
            updateNum += generateBatchProcessClass(configCommand, poEntity, tempParamMap);
        }
        if (onlyRepositoryFlag.equals(0)) {
            int updateNum2 = updateNum + generateApplicationQueryClass(configCommand, poEntity, tempParamMap);
            if (serviceType.equals(DevServiceTypeEnum.API_ADMIN.getCode())) {
                updateNum2 += generateApplicationCommandClass(configCommand, poEntity, tempParamMap);
            }
            updateNum = updateNum2 + generateApiClass(configCommand, poEntity, tempParamMap);
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateDtoClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        String classMainDirectory = poEntity.getPackageMainFileDirectory() + "application/" + poEntity.getAggregateName() + "/dto/";
        String className = poEntity.getEntityName() + "BaseDTO.java";
        String classFilePath = classMainDirectory + className;
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempBaseDTO.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String className2 = poEntity.getEntityName() + "DTO.java";
        String classFilePath2 = classMainDirectory + className2;
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempDTO.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        String className3 = poEntity.getEntityName() + "FeignDTO.java";
        String classFilePath3 = classMainDirectory + className3;
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempFeignDTO.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateRepositoryClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        String classMainDirectory = poEntity.getPackageMainFileDirectory() + "infrastructure/repository/";
        String className = poEntity.getEntityName() + "QueryMapper.java";
        String classFilePath = classMainDirectory + "mapper/" + className;
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempQueryMapper.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String className2 = poEntity.getEntityName() + "CommandMapper.java";
        String classFilePath2 = classMainDirectory + "mapper/" + className2;
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempCommandMapper.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        String className3 = poEntity.getEntityName() + "Converter.java";
        String classFilePath3 = classMainDirectory + "converter/" + className3;
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempConverter.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        String className4 = poEntity.getEntityName() + "Repository.java";
        String classFilePath4 = poEntity.getPackageMainFileDirectory() + "domain/aggregate/" + poEntity.getAggregateName() + "/" + className4;
        String classContent4 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempRepository.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath4, classContent4)) {
            updateNum++;
        }
        String className5 = poEntity.getEntityName() + "RepositoryImpl.java";
        String classFilePath5 = classMainDirectory + "impl/" + className5;
        String classContent5 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempRepositoryImpl.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath5, classContent5)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateBatchProcessClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        FileUtils.checkAndCreateFileDirectory(poEntity.getPackageMainFileDirectory() + "infrastructure/repository/daoservice/");
        FileUtils.checkAndCreateFileDirectory(poEntity.getPackageMainFileDirectory() + "infrastructure/repository/daoservice/impl/");
        String classMainDirectory = poEntity.getPackageMainFileDirectory() + "infrastructure/repository/";
        String className = poEntity.getEntityName() + "DaoService.java";
        String classFilePath = classMainDirectory + "daoservice/" + className;
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempDaoService.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String className2 = poEntity.getEntityName() + "DaoServiceImpl.java";
        String classFilePath2 = classMainDirectory + "daoservice/impl/" + className2;
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempDaoServiceImpl.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateDomainClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        String classMainDirectory = poEntity.getPackageMainFileDirectory() + "domain/aggregate/" + poEntity.getAggregateName() + "/";
        String className = poEntity.getEntityName() + ".java";
        String classFilePath = classMainDirectory + className;
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempEntity.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateApplicationQueryClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        String classMainDirectory = poEntity.getPackageMainFileDirectory() + "application/" + poEntity.getAggregateName() + "/query/";
        String className = poEntity.getEntityName() + "Query.java";
        String classFilePath = classMainDirectory + "param/" + className;
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempQuery.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String className2 = poEntity.getEntityName() + "QueryService.java";
        String classFilePath2 = classMainDirectory + className2;
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempQueryService.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        String className3 = poEntity.getEntityName() + "QueryServiceImpl.java";
        String classFilePath3 = classMainDirectory + "impl/" + className3;
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempQueryServiceImpl.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateApplicationCommandClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        String classMainDirectory = poEntity.getPackageMainFileDirectory() + "application/" + poEntity.getAggregateName() + "/command/";
        String className = poEntity.getEntityName() + "AddCommand.java";
        String classFilePath = classMainDirectory + "param/" + className;
        String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempAddCommand.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
            updateNum = 0 + 1;
        }
        String className2 = poEntity.getEntityName() + "UpdateCommand.java";
        String classFilePath2 = classMainDirectory + "param/" + className2;
        String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempUpdateCommand.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
            updateNum++;
        }
        String className3 = poEntity.getEntityName() + "DeleteCommand.java";
        String classFilePath3 = classMainDirectory + "param/" + className3;
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempDeleteCommand.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        String className4 = poEntity.getEntityName() + "Assembler.java";
        String classFilePath4 = poEntity.getPackageMainFileDirectory() + "application/" + poEntity.getAggregateName() + "/assembler/" + className4;
        String classContent4 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempAssembler.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath4, classContent4)) {
            updateNum++;
        }
        String className5 = poEntity.getEntityName() + "CommandService.java";
        String classFilePath5 = classMainDirectory + className5;
        String classContent5 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempCommandService.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath5, classContent5)) {
            updateNum++;
        }
        String className6 = poEntity.getEntityName() + "CommandServiceImpl.java";
        String classFilePath6 = classMainDirectory + "impl/" + className6;
        String classContent6 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempCommandServiceImpl.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath6, classContent6)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public int generateApiClass(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity, Map<String, Object> tempParamMap) {
        String classMainDirectory;
        String classMainDirectory2;
        if (tempParamMap == null) {
            tempParamMap = buildTempParamMap(configCommand, poEntity);
        }
        int updateNum = 0;
        String serviceType = configCommand.getServiceType() != null ? configCommand.getServiceType().trim() : "";
        if (serviceType.equals(DevServiceTypeEnum.API_ADMIN.getCode())) {
            if (FileUtils.checkFileDirectoryIsExist(poEntity.getPackageMainFileDirectory() + "api/siteadmin/")) {
                classMainDirectory2 = poEntity.getPackageMainFileDirectory() + "api/siteadmin/";
            } else {
                classMainDirectory2 = poEntity.getPackageMainFileDirectory() + "api/admin/";
            }
            String className = poEntity.getEntityName() + "Controller.java";
            String classFilePath = classMainDirectory2 + className;
            String classContent = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempAdminController.ftl", tempParamMap);
            if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath, classContent)) {
                updateNum = 0 + 1;
            }
        } else if (serviceType.equals(DevServiceTypeEnum.API_APP.getCode())) {
            if (FileUtils.checkFileDirectoryIsExist(poEntity.getPackageMainFileDirectory() + "api/siteapp/")) {
                classMainDirectory = poEntity.getPackageMainFileDirectory() + "api/siteapp/";
            } else {
                classMainDirectory = poEntity.getPackageMainFileDirectory() + "api/app/";
            }
            String className2 = poEntity.getEntityName() + "Controller.java";
            String classFilePath2 = classMainDirectory + className2;
            String classContent2 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempAppController.ftl", tempParamMap);
            if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath2, classContent2)) {
                updateNum = 0 + 1;
            }
        }
        String classMainDirectory3 = poEntity.getPackageMainFileDirectory() + "api/feign/";
        String className3 = poEntity.getEntityName() + "FeignController.java";
        String classFilePath3 = classMainDirectory3 + className3;
        String classContent3 = FreeMarkerUtils.generate(configCommand.getTempType(), "DddEntityTempFeignController.ftl", tempParamMap);
        if (FileUtils.outputToFile(configCommand.getOverwriteExistFileFlag(), classFilePath3, classContent3)) {
            updateNum++;
        }
        return updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.DddClassCommandService
    public Map<String, Object> buildTempParamMap(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity) {
        Map<String, Object> tempParamMap = new HashMap<>();
        tempParamMap.put("_currentDate", DataTimeUtils.findCurrentDateStr());
        tempParamMap.put("_currentTime", DataTimeUtils.findCurrentDateTimeStr());
        tempParamMap.put("_poEntity", poEntity);
        if (configCommand.getOverwriteExistFileFlag() == null) {
            configCommand.setOverwriteExistFileFlag(0);
        }
        if (configCommand.getOnlyRepositoryFlag() == null) {
            configCommand.setOnlyRepositoryFlag(1);
        }
        if (configCommand.getBatchProcessSupportFlag() == null) {
            configCommand.setBatchProcessSupportFlag(0);
        }
        tempParamMap.put("_configCommand", configCommand);
        return tempParamMap;
    }
}
