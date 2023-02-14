package com.hqwx.codegeneration.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.annotation.Nullable;
import javax.swing.JComponent;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/dialog/ConfigDialog.class */
public class ConfigDialog extends DialogWrapper {
    private Project project;
    private ConfigSwing configSwing;

    public ConfigDialog(@Nullable Project project) {
        super(project);
        this.project = project;
        this.configSwing = new ConfigSwing(this, project);
        setTitle("环球DDD代码生成工具 - 参数配置");
        init();
    }

    protected JComponent createNorthPanel() {
        return this.configSwing.initNorth();
    }

    protected JComponent createSouthPanel() {
        return this.configSwing.initSouth();
    }

    protected JComponent createCenterPanel() {
        return this.configSwing.initCenter();
    }
}
