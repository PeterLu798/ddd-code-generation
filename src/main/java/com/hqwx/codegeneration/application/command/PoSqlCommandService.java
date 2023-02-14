package com.hqwx.codegeneration.application.command;

import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/PoSqlCommandService.class */
public interface PoSqlCommandService {
    void generateTableSQL(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO);
}
