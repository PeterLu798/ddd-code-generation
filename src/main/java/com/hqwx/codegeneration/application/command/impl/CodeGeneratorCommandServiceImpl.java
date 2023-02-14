package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.CodeGeneratorCommandService;
import com.hqwx.codegeneration.application.command.DddClassCommandService;
import com.hqwx.codegeneration.application.command.DddDirectoryCommandService;
import com.hqwx.codegeneration.application.command.PoParseCommandService;
import com.hqwx.codegeneration.application.command.PoSqlCommandService;
import com.hqwx.codegeneration.application.command.ProjectConfigClassCommandService;
import com.hqwx.codegeneration.application.command.ProjectSettingCommandService;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;
import com.hqwx.codegeneration.shared.exception.ParamErrorException;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/CodeGeneratorCommandServiceImpl.class */
public class CodeGeneratorCommandServiceImpl implements CodeGeneratorCommandService {
    private DddDirectoryCommandService dddDirectoryCommandService = new DddDirectoryCommandServiceImpl();
    private ProjectSettingCommandService projectSettingCommandService = new ProjectSettingCommandServiceImpl();
    private ProjectConfigClassCommandService projectConfigClassCommandService = new ProjectConfigClassCommandServiceImpl();
    private PoParseCommandService poParseCommandService = new PoParseCommandServiceImpl();
    private PoSqlCommandService poSqlCommandService = new PoSqlCommandServiceImpl();
    private DddClassCommandService dddClassCommandService = new DddClassCommandServiceImpl();

    @Override // com.hqwx.codegeneration.application.command.CodeGeneratorCommandService
    public String codeGeneration(CodeGeneratorConfigCommand configCommand) {
        checkAndInitConfigCommand(configCommand);
        this.dddDirectoryCommandService.buildDddPackageDirectory(configCommand.getServiceType(), configCommand.getPackageMainDirectory());
        if (StringUtils.isBlank(configCommand.getPoName())) {
            return "DDD主目录构建完成";
        }
        PoEntityClassDTO poEntity = this.poParseCommandService.parseAndAssembleEntity(configCommand.getAggregateName(), configCommand.getPoName(), configCommand.getPackageMainPath(), configCommand.getPackageMainDirectory());
        poEntity.setServiceType(configCommand.getServiceType());
        this.dddDirectoryCommandService.buildAggregatePackageDirectory(configCommand, poEntity);
        this.poSqlCommandService.generateTableSQL(configCommand, poEntity);
        int updateNum = this.dddClassCommandService.generateClass(configCommand, poEntity);
        return "创建或更新类文件数量: " + updateNum;
    }

    @Override // com.hqwx.codegeneration.application.command.CodeGeneratorCommandService
    public String configSync(CodeGeneratorConfigCommand configCommand) {
        checkAndInitConfigCommand(configCommand);
        this.dddDirectoryCommandService.buildDddPackageDirectory(configCommand.getServiceType(), configCommand.getPackageMainDirectory());
        int updateNum = 0 + this.projectSettingCommandService.syncSetting(configCommand);
        return "更新配置项数量: " + (updateNum + this.projectConfigClassCommandService.generateConfigClass(configCommand));
    }

    private void checkAndInitConfigCommand(CodeGeneratorConfigCommand configCommand) {
        String packageMainFileDirectory = findAndCheckProjectAndPackagePath(configCommand);
        if (StringUtils.isBlank(packageMainFileDirectory)) {
            System.out.println("package main file directory is not exist, " + packageMainFileDirectory);
            throw new ParamErrorException("包对应的文件目录不存在，请检查", getClass());
        } else {
            configCommand.setPackageMainDirectory(packageMainFileDirectory);
        }
    }

    private String findAndCheckProjectAndPackagePath(CodeGeneratorConfigCommand submitCommand) {
        if (StringUtils.isBlank(submitCommand.getProjectMainDirectory()) || StringUtils.isBlank(submitCommand.getPackageMainPath())) {
            System.out.println("params is not correct, " + submitCommand.toString());
            return "";
        } else if (!FileUtils.checkFileDirectoryIsExist(submitCommand.getProjectMainDirectory())) {
            System.out.println("project main path is not exist, " + submitCommand.getProjectMainDirectory());
            return "";
        } else {
            StringBuffer directory = new StringBuffer("");
            directory.append(submitCommand.getProjectMainDirectory());
            directory.append("/src/main/java/");
            if (!FileUtils.checkFileDirectoryIsExist(directory.toString())) {
                System.out.println("project java main directory is not exist, " + directory.toString());
                return "";
            }
            directory.append(submitCommand.getPackageMainPath().replaceAll("\\.", "/")).append("/");
            if (!FileUtils.checkFileDirectoryIsExist(directory.toString())) {
                System.out.println("package main directory is not exist, " + directory.toString());
                return "";
            }
            System.out.println(" > package main file directory: " + directory.toString());
            return directory.toString();
        }
    }
}
