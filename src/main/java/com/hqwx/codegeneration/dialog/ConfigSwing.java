package com.hqwx.codegeneration.dialog;

import com.hqwx.codegeneration.application.command.CodeGeneratorCommandService;
import com.hqwx.codegeneration.application.command.impl.CodeGeneratorCommandServiceImpl;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.shared.model.ElementItem;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang3.StringUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/dialog/ConfigSwing.class */
public class ConfigSwing {
    private DialogWrapper dialog;
    private Project project;
    private String PACKAGE_MAIN_PATH_LATEST = "";
    private CodeGeneratorCommandService codeGeneratorCommandService = new CodeGeneratorCommandServiceImpl();
    private JPanel north = new JPanel();
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    JLabel promptMsg = new JLabel("");
    JLabel promptMsg2 = new JLabel("");
    private JLabel projectMainDirLabel = new JLabel("项目主目录：");
    private JTextField projectMainDirText = new JTextField();
    private JLabel packageMainPathLabel = new JLabel("包主路径：");
    private JTextField packageMainPathText = new JTextField();
    private JLabel serviceTypeLabel = new JLabel("服务类型：");
    private JComboBox<ElementItem> serviceTypeSelect = new ComboBox();
    private JLabel overwriteExistFileLabel = new JLabel("覆盖已有类文件：");
    private JComboBox<ElementItem> overwriteExistFileSelect = new ComboBox();
    private JLabel onlyRepositoryLabel = new JLabel("只生成Repository接口：");
    private JComboBox<ElementItem> onlyRepositorySelect = new ComboBox();
    private JLabel batchProcessSupportLabel = new JLabel("是否支持批量保存：");
    private JComboBox<ElementItem> batchProcessSupportSelect = new ComboBox();
    private JLabel aggregateNameLabel = new JLabel("PO所在聚合名 (包名)：");
    private JTextField aggregateNameText = new JTextField();
    private JLabel poClassNameLabel = new JLabel("PO类名：");
    private JComboBox<ElementItem> poClassNameSelect = new ComboBox();

    public ConfigSwing(DialogWrapper dialog, @Nullable Project project) {
        this.project = project;
        this.dialog = dialog;
    }

    public JPanel initNorth() {
        this.promptMsg.setForeground(new Color(255, 99, 71));
        this.promptMsg2.setForeground(new Color(255, 99, 71));
        this.north.setLayout(new GridLayout(0, 1));
        this.north.add(this.promptMsg);
        this.north.add(this.promptMsg2);
        return this.north;
    }

    public JPanel initCenter() {
        this.center.setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JPanel inputPanel = new JPanel();
        titlePanel.setLayout(new GridLayout(0, 1));
        inputPanel.setLayout(new GridLayout(0, 1));
        this.center.add(titlePanel, "West");
        this.center.add(inputPanel, "Center");
        titlePanel.add(this.projectMainDirLabel);
        inputPanel.add(this.projectMainDirText);
        titlePanel.add(this.packageMainPathLabel);
        inputPanel.add(this.packageMainPathText);
        this.packageMainPathText.addFocusListener(new FocusListener() { // from class: com.hqwx.codegeneration.dialog.ConfigSwing.1
            public void focusLost(FocusEvent e) {
                if (ConfigSwing.this.checkPackageMainPathIsCorrect()) {
                    ConfigSwing.this.loadPoClassNameToSelect();
                }
            }

            public void focusGained(FocusEvent e) {
            }
        });
        this.serviceTypeSelect.addItem(new ElementItem("", "请选择"));
        this.serviceTypeSelect.addItem(new ElementItem("api_app", "C端应用API"));
        this.serviceTypeSelect.addItem(new ElementItem("api_admin", "管理后台API"));
        this.serviceTypeSelect.addItem(new ElementItem("backend_service", "纯后台服务"));
        this.serviceTypeSelect.setSelectedIndex(0);
        titlePanel.add(this.serviceTypeLabel);
        inputPanel.add(this.serviceTypeSelect);
        this.overwriteExistFileSelect.addItem(new ElementItem("0", "否, 不覆盖"));
        this.overwriteExistFileSelect.addItem(new ElementItem("1", "是"));
        this.overwriteExistFileSelect.setSelectedIndex(0);
        titlePanel.add(this.overwriteExistFileLabel);
        inputPanel.add(this.overwriteExistFileSelect);
        this.onlyRepositorySelect.addItem(new ElementItem("1", "是"));
        this.onlyRepositorySelect.addItem(new ElementItem("0", "否, 全部生成"));
        this.onlyRepositorySelect.setSelectedIndex(0);
        titlePanel.add(this.onlyRepositoryLabel);
        inputPanel.add(this.onlyRepositorySelect);
        this.batchProcessSupportSelect.addItem(new ElementItem("0", "否"));
        this.batchProcessSupportSelect.addItem(new ElementItem("1", "是, 增加批量保存接口"));
        this.batchProcessSupportSelect.setSelectedIndex(0);
        titlePanel.add(this.batchProcessSupportLabel);
        inputPanel.add(this.batchProcessSupportSelect);
        titlePanel.add(this.poClassNameLabel);
        inputPanel.add(this.poClassNameSelect);
        titlePanel.add(this.aggregateNameLabel);
        inputPanel.add(this.aggregateNameText);
        initDataForElement();
        return this.center;
    }

    public JPanel initSouth() {
        JButton configSync = new JButton("配置同步");
        configSync.setHorizontalAlignment(0);
        configSync.setVerticalAlignment(0);
        this.south.add(configSync);
        JButton submit = new JButton("代码生成");
        submit.setHorizontalAlignment(0);
        submit.setVerticalAlignment(0);
        this.south.add(submit);
        JButton close = new JButton("关闭");
        close.setHorizontalAlignment(0);
        close.setVerticalAlignment(0);
        this.south.add(close);
        configSync.addActionListener(e -> {
            startConfigSync();
        });
        submit.addActionListener(e2 -> {
            startCodeGeneration();
        });
        close.addActionListener(e3 -> {
            this.dialog.close(-1);
        });
        return this.south;
    }

    private void startConfigSync() {
        try {
            CodeGeneratorConfigCommand configCommand = checkAndAssembleConfigByParam();
            if (configCommand == null) {
                return;
            }
            int opt = JOptionPane.showConfirmDialog(this.center, "确定开始进行配置同步吗? \n\n 可用于初始项目的pom、bootstrap、swagger、web、mybatis-plus等配置生成 \n 该操作不会覆盖已经存在的配置", "确认", 0);
            if (opt == 0) {
                String result = this.codeGeneratorCommandService.configSync(configCommand);
                if (StringUtils.isBlank(result)) {
                    this.promptMsg.setText("同步完成");
                } else {
                    this.promptMsg.setText(result);
                    this.promptMsg2.setText("如有文件更新请手动执行 Reload from Disk 进行刷新");
                }
            }
        } catch (Exception err) {
            this.promptMsg.setText(String.format("操作失败, %s", err.getMessage()));
        }
    }

    private void startCodeGeneration() {
        try {
            CodeGeneratorConfigCommand configCommand = checkAndAssembleConfigByParam();
            if (configCommand == null) {
                return;
            }
            int opt = JOptionPane.showConfirmDialog(this.center, "确定开始生成吗?", "确认", 0);
            if (opt == 0) {
                String result = this.codeGeneratorCommandService.codeGeneration(configCommand);
                if (StringUtils.isBlank(result)) {
                    this.promptMsg.setText("操作完成");
                } else {
                    this.promptMsg.setText(result);
                    this.promptMsg2.setText("如有文件更新请手动执行 Reload from Disk 进行刷新");
                }
            }
        } catch (Exception err) {
            this.promptMsg.setText(String.format("操作失败, %s", err.getMessage()));
        }
    }

    private CodeGeneratorConfigCommand checkAndAssembleConfigByParam() {
        this.promptMsg.setText("");
        this.promptMsg2.setText("");
        if (StringUtils.isBlank(this.projectMainDirText.getText())) {
            this.promptMsg.setText("项目主目录不能为空, 值形如 d:/xxx/xxx/");
            return null;
        } else if (!checkPackageMainPathIsCorrect()) {
            return null;
        } else {
            ElementItem serviceTypeSelectItem = (ElementItem) this.serviceTypeSelect.getSelectedItem();
            String serviceType = serviceTypeSelectItem.getCode();
            if (StringUtils.isBlank(serviceType)) {
                this.promptMsg.setText("请选择对应的服务类型");
                return null;
            }
            ElementItem overwriteExistFileSelectItem = (ElementItem) this.overwriteExistFileSelect.getSelectedItem();
            ElementItem onlyRepositorySelectItem = (ElementItem) this.onlyRepositorySelect.getSelectedItem();
            ElementItem batchProcessSupportSelectItem = (ElementItem) this.batchProcessSupportSelect.getSelectedItem();
            String aggregateName = this.aggregateNameText.getText().trim();
            String poClassName = "";
            if (this.poClassNameSelect.getSelectedItem() != null) {
                ElementItem poClassNameSelectItem = (ElementItem) this.poClassNameSelect.getSelectedItem();
                poClassName = poClassNameSelectItem.getCode();
            }
            if (StringUtils.isNotBlank(poClassName)) {
                if (StringUtils.isBlank(aggregateName)) {
                    this.promptMsg.setText("PO类所在的聚合名不能为空");
                    return null;
                } else if (!aggregateName.toLowerCase().equals(aggregateName)) {
                    this.promptMsg.setText("聚合名不能含有大写字母, 请检查");
                    return null;
                }
            }
            String projectMainDirectory = FileUtils.replaceDirSeparator(this.projectMainDirText.getText().trim());
            CodeGeneratorConfigCommand configCommand = new CodeGeneratorConfigCommand();
            configCommand.setTempType("ddd_hqwx");
            configCommand.setProjectMainDirectory(projectMainDirectory);
            configCommand.setProjectParentDirectory(findProjectParentDirectory(projectMainDirectory));
            configCommand.setPackageMainPath(this.packageMainPathText.getText().trim());
            configCommand.setPackageMainDirectory("");
            configCommand.setServiceType(serviceType);
            configCommand.setOverwriteExistFileFlag(Integer.valueOf(overwriteExistFileSelectItem.getCode()));
            configCommand.setOnlyRepositoryFlag(Integer.valueOf(onlyRepositorySelectItem.getCode()));
            configCommand.setBatchProcessSupportFlag(Integer.valueOf(batchProcessSupportSelectItem.getCode()));
            configCommand.setAggregateName(aggregateName);
            configCommand.setPoName(poClassName);
            return configCommand;
        }
    }

    private String findProjectParentDirectory(String projectMainDirectory) {
        File projectMainFileDir = new File(projectMainDirectory);
        if (!projectMainFileDir.isDirectory()) {
            return "";
        }
        File projectParentFileDir = new File(projectMainFileDir.getParent());
        File[] subFileArray = projectParentFileDir.listFiles();
        for (File subFile : subFileArray) {
            if (subFile.isFile() && subFile.getName().equals("pom.xml")) {
                return FileUtils.replaceDirSeparator(projectMainFileDir.getParent());
            }
        }
        return "";
    }

    private boolean checkPackageMainPathIsCorrect() {
        this.promptMsg.setText("");
        this.promptMsg2.setText("");
        String packageMainPath = this.packageMainPathText.getText();
        if (StringUtils.isBlank(packageMainPath)) {
            this.promptMsg.setText("包主路径不能为空, 值形如 com.xxx.xxx");
            return false;
        }
        StringBuffer directory = new StringBuffer("");
        directory.append(this.projectMainDirText.getText());
        directory.append("/src/main/java/");
        directory.append(packageMainPath.trim().replaceAll("\\.", "/")).append("/");
        if (!FileUtils.checkFileDirectoryIsExist(directory.toString())) {
            this.promptMsg.setText("包主路径对应文件目录不存在，请检查");
            this.promptMsg2.setText(directory.toString());
            return false;
        }
        return true;
    }

    private void initDataForElement() {
        this.projectMainDirText.setText(FileUtils.replaceDirSeparator(this.project.getBasePath()));
        this.packageMainPathText.setText("com.xxx.xxx");
        File projectMainDir = new File(this.project.getBasePath());
        findProjectBootApplicationClass("", projectMainDir);
        loadPoClassNameToSelect();
    }

    private boolean findProjectBootApplicationClass(String parentName, File fileDir) {
        File[] subFileList;
        if (parentName == null || fileDir == null || !fileDir.isDirectory() || fileDir.getName().equals(".idea") || fileDir.getName().equals(".git") || fileDir.getName().equals("target")) {
            return false;
        }
        if (parentName.equals("main") && fileDir.getName().equals("resources")) {
            return false;
        }
        if ((parentName.equals("src") && fileDir.getName().equals("test")) || (subFileList = fileDir.listFiles()) == null || subFileList.length <= 0) {
            return false;
        }
        for (File subFile : subFileList) {
            if (subFile.isFile() && ((subFile.getName().endsWith("Application.java") || subFile.getName().endsWith("App.java")) && checkIsSpringBootApplicationClass(subFile))) {
                String mainClassDirStr = FileUtils.replaceDirSeparator(fileDir.getPath());
                String[] subArray = mainClassDirStr.split("/src/main/java/");
                if (subArray.length >= 2 && StringUtils.isNotBlank(subArray[1])) {
                    this.projectMainDirText.setText(subArray[0]);
                    this.packageMainPathText.setText(subArray[1].replaceAll("/", "."));
                    return true;
                }
                return true;
            } else if (subFile.isDirectory() && findProjectBootApplicationClass(fileDir.getName(), subFile)) {
                return true;
            }
        }
        return false;
    }

    private void loadPoClassNameToSelect() {
        if (this.PACKAGE_MAIN_PATH_LATEST.equals(this.packageMainPathText.getText())) {
            return;
        }
        this.PACKAGE_MAIN_PATH_LATEST = this.packageMainPathText.getText();
        if (this.poClassNameSelect.getItemCount() > 0) {
            this.poClassNameSelect.removeAllItems();
        }
        StringBuffer paths = new StringBuffer("");
        paths.append(this.projectMainDirText.getText());
        paths.append("/src/main/java/");
        paths.append(this.packageMainPathText.getText().replaceAll("\\.", "/"));
        paths.append("/infrastructure/repository/model/");
        this.poClassNameSelect.addItem(new ElementItem("", "请选择"));
        File poFileDir = new File(paths.toString());
        if (!poFileDir.exists() || !poFileDir.isDirectory()) {
            return;
        }
        List<String> poClassList = new ArrayList<>();
        traversalPoClassDirectory(poFileDir, poClassList);
        for (String poClass : poClassList) {
            this.poClassNameSelect.addItem(new ElementItem(poClass, poClass));
        }
    }

    private void traversalPoClassDirectory(File poFileDir, List<String> poClassList) {
        File[] subFileArray = poFileDir.listFiles();
        if (subFileArray == null || subFileArray.length <= 0) {
            return;
        }
        for (File subFile : subFileArray) {
            if (subFile.isFile()) {
                if (subFile.getName().endsWith("PO.java")) {
                    String poClassPath = subFile.getPath().replaceAll("\\\\", "/");
                    String[] subArray = poClassPath.split("repository/model/");
                    if (subArray.length == 2 && subArray[1] != null && subArray[1].length() > 5) {
                        String poClassName = subArray[1].replace(".java", "");
                        poClassList.add(poClassName);
                    }
                }
            } else if (subFile.isDirectory()) {
                traversalPoClassDirectory(subFile, poClassList);
            }
        }
    }

    private boolean checkIsSpringBootApplicationClass(File javaFile) {
        if (javaFile == null || !javaFile.isFile()) {
            return false;
        }
        boolean matchFlag = false;
        try {
            InputStreamReader streamReader1 = new InputStreamReader(new FileInputStream(javaFile), "UTF-8");
            BufferedReader buffReader1 = new BufferedReader(streamReader1);
            String line = buffReader1.readLine();
            while (true) {
                if (line == null) {
                    break;
                } else if (line.contains("@SpringBootApplication")) {
                    matchFlag = true;
                    break;
                } else {
                    line = buffReader1.readLine();
                }
            }
            buffReader1.close();
            streamReader1.close();
        } catch (Exception err) {
            System.out.println("checkIsSpringBootApplicationClass_error, " + err.toString());
        }
        return matchFlag;
    }
}
