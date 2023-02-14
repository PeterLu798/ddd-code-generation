package com.hqwx.codegeneration.application.command.impl;

import com.hqwx.codegeneration.application.command.PoSqlCommandService;
import com.hqwx.codegeneration.application.command.param.CodeGeneratorConfigCommand;
import com.hqwx.codegeneration.application.dto.PoEntityClassDTO;
import com.hqwx.codegeneration.shared.utils.DataTimeUtils;
import com.hqwx.codegeneration.shared.utils.FileUtils;
import com.hqwx.codegeneration.shared.utils.FreeMarkerUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/application/command/impl/PoSqlCommandServiceImpl.class */
public class PoSqlCommandServiceImpl implements PoSqlCommandService {
    @Override // com.hqwx.codegeneration.application.command.PoSqlCommandService
    public void generateTableSQL(CodeGeneratorConfigCommand configCommand, PoEntityClassDTO poEntity) {
        if (poEntity == null || StringUtils.isBlank(poEntity.getTableName())) {
            return;
        }
        try {
            FileUtils.checkAndCreateFileDirectory(configCommand.getProjectMainDirectory() + "/doc/");
            FileUtils.checkAndCreateFileDirectory(configCommand.getProjectMainDirectory() + "/doc/sql/");
            String sqlFilePath = configCommand.getProjectMainDirectory() + "/doc/sql/table_" + poEntity.getTableName() + ".sql";
            File sqlFile = FileUtils.checkAndCreateFile(sqlFilePath);
            if (sqlFile == null) {
                return;
            }
            Map<String, Object> tempParamMap = new HashMap<>();
            tempParamMap.put("_currentDate", DataTimeUtils.findCurrentDateStr());
            tempParamMap.put("_currentTime", DataTimeUtils.findCurrentDateTimeStr());
            tempParamMap.put("_poEntity", poEntity);
            String sql = FreeMarkerUtils.generate(configCommand.getTempType(), "PoTableSqlTemp.ftl", tempParamMap);
            FileUtils.updateFileContent(sqlFile, sql);
            if (FileUtils.updateFileContent(sqlFile, sql)) {
                System.out.println(" > generateTableSQLByPO_success, sqlFilePath: " + sqlFilePath);
            } else {
                System.out.println(" > generateTableSQLByPO_failed, sqlFilePath: " + sqlFilePath);
            }
        } catch (Exception err) {
            System.out.println("generateTableSQLByPO_error, po: " + poEntity.toString() + ", " + err.toString());
        }
    }
}
