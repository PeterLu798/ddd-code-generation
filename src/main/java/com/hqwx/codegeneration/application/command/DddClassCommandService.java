package com.hqwx.codegeneration.application.command;

import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;
import java.util.Map;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/DddClassCommandService.class */
public interface DddClassCommandService {
    int generateClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO);

    int generateDtoClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    int generateRepositoryClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    int generateBatchProcessClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    int generateDomainClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    int generateApplicationQueryClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    int generateApplicationCommandClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    int generateApiClass(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO, Map<String, Object> map);

    Map<String, Object> buildTempParamMap(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO);
}
