package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.mybatisplus;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 租户配置属性
 * 配置model所在的包路径, 租户ID对应的列名, excludeTableNames可选，如果不配置，会自动去扫描model包下的Entity, 配置示例如下:
 * tenant-filter:
 *   scan-package: cn.xxx.xxx.infrastructure.repository.model
 *   # tenantIdColumn: sch_id
 *   excludeTableNames:
 *   - test_table1
 *   - test_table2
 *   或者
 *   excludeTableNames: [test_table1, test_table2]
 *
 * create time: ${_currentTime}
 */
@ConfigurationProperties(prefix = "tenant-filter")
public class TenantFilterProperties {

    /**
     * model所在包路径
     */
    private String scanPackage;

    /**
     * 租户ID对应的数据表列名
     */
    private String tenantIdColumn = "sch_id";

    /**
     * 多租户要排除的表名(多个), 当某个表中不存在租户ID时可以将该表名添加到 excludeTableNames 属性下面, 配置示例如下:
     * tenant-filter:
     *   scan-package: cn.xxx.xxx.infrastructure.repository.model
     *   excludeTableNames:
     *   - test_table1
     *   - test_table2
     *   或者
     *   excludeTableNames: [test_table1, test_table2]
     */
    private List<String> excludeTableNames;

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getTenantIdColumn() {
        return tenantIdColumn;
    }

    public void setTenantIdColumn(String tenantIdColumn) {
        this.tenantIdColumn = tenantIdColumn;
    }

    public List<String> getExcludeTableNames() {
        return excludeTableNames;
    }

    public void setExcludeTableNames(List<String> excludeTableNames) {
        this.excludeTableNames = excludeTableNames;
    }
}
