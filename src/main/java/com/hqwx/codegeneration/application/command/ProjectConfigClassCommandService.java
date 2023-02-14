package com.hqwx.codegeneration.application.command;

import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.ProjectConfigClassDTO;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/ProjectConfigClassCommandService.class */
public interface ProjectConfigClassCommandService {
    int generateConfigClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand);

    int generateSwaggerConfig(CodeGeneratorConfigCommand codeGeneratorConfigCommand, ProjectConfigClassDTO projectConfigClassDTO);

    int generateMybatisPlusConfig(CodeGeneratorConfigCommand codeGeneratorConfigCommand, ProjectConfigClassDTO projectConfigClassDTO);

    int generateWebConfig(CodeGeneratorConfigCommand codeGeneratorConfigCommand, ProjectConfigClassDTO projectConfigClassDTO);

    int generateLogTraceConfig(CodeGeneratorConfigCommand codeGeneratorConfigCommand, ProjectConfigClassDTO projectConfigClassDTO);

    int generateCacheConfig(CodeGeneratorConfigCommand codeGeneratorConfigCommand, ProjectConfigClassDTO projectConfigClassDTO);
}
