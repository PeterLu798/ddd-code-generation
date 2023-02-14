package com.hqwx.codegeneration.action;

import com.hqwx.codegeneration.dialog.ConfigDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/action/CodeGenerationForDddMainAction.class */
public class MyCodeGenerationForDddMainAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        ConfigDialog configDialog = new ConfigDialog(project);
        configDialog.setResizable(true);
        configDialog.show();
    }
}
