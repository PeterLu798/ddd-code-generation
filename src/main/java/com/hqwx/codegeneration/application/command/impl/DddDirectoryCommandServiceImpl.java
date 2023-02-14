package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.DddDirectoryCommandService;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/DddDirectoryCommandServiceImpl.class */
public class DddDirectoryCommandServiceImpl implements DddDirectoryCommandService {
    @Override // com.hqwx.codegeneration.application.command.DddDirectoryCommandService
    public void buildDddPackageDirectory(String serviceType, String packageMainDir) {
        if (StringUtils.isBlank(packageMainDir)) {
            return;
        }
        if (!FileUtils.checkFileDirectoryIsExist(packageMainDir)) {
            System.out.println("buildDddPackageDirectory_failed, packageMainDir not correct, " + packageMainDir);
            return;
        }
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "api/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "api/feign/");
        if (StringUtils.isNotBlank(serviceType)) {
            if (serviceType.toLowerCase().contains("admin")) {
                if (!FileUtils.checkFileDirectoryIsExist(packageMainDir + "api/siteadmin/")) {
                    FileUtils.checkAndCreateFileDirectory(packageMainDir + "api/admin/");
                }
            } else if (serviceType.toLowerCase().contains("siteapp") && !FileUtils.checkFileDirectoryIsExist(packageMainDir + "api/siteapp/")) {
                FileUtils.checkAndCreateFileDirectory(packageMainDir + "api/app/");
            }
        }
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "application/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "domain/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "domain/aggregate/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/");
        if (!FileUtils.checkFileDirectoryIsExist(packageMainDir + "config/")) {
            FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/config");
        }
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/repository/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/repository/model/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/repository/mapper/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/repository/converter/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/repository/impl/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "infrastructure/utils/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "shared/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "shared/constants");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "shared/enums/");
        FileUtils.checkAndCreateFileDirectory(packageMainDir + "shared/feigncall/");
    }

    @Override // com.hqwx.codegeneration.application.command.DddDirectoryCommandService
    public void buildAggregatePackageDirectory(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity) {
        if (poEntity == null || StringUtils.isBlank(poEntity.getAggregateName()) || StringUtils.isBlank(poEntity.getPackageMainFileDirectory())) {
            return;
        }
        String applicationMainDirectory = poEntity.getPackageMainFileDirectory() + "application/" + poEntity.getAggregateName() + "/";
        FileUtils.checkAndCreateFileDirectory(applicationMainDirectory);
        FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "dto/");
        FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "query/");
        if (configCommand.getOnlyRepositoryFlag() != null && configCommand.getOnlyRepositoryFlag().intValue() == 0) {
            FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "query/param/");
            FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "query/impl/");
            if (poEntity.getServiceType() == null || !poEntity.getServiceType().contains("siteapp")) {
                FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "assembler/");
                FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "command/");
                FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "command/param/");
                FileUtils.checkAndCreateFileDirectory(applicationMainDirectory + "command/impl/");
            }
        }
        String aggregateDomainMain = poEntity.getPackageMainFileDirectory() + "domain/aggregate/" + poEntity.getAggregateName() + "/";
        FileUtils.checkAndCreateFileDirectory(aggregateDomainMain);
    }
}
