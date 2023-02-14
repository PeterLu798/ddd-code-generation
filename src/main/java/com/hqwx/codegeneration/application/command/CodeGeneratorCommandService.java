package com.hqwx.codegeneration.application.command;

import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/CodeGeneratorCommandService.class */
public interface CodeGeneratorCommandService {
    String codeGeneration(CodeGeneratorConfigCommand codeGeneratorConfigCommand);

    String configSync(CodeGeneratorConfigCommand codeGeneratorConfigCommand);
}
