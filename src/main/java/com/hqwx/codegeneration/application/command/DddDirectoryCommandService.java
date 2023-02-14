package com.hqwx.codegeneration.application.command;

import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/DddDirectoryCommandService.class */
public interface DddDirectoryCommandService {
    void buildDddPackageDirectory(String str, String str2);

    void buildAggregatePackageDirectory(CodeGeneratorConfigCommand codeGeneratorConfigCommand, PoEntityClassDTO poEntityClassDTO);
}
