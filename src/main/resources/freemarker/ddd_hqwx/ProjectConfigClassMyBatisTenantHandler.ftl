package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.hqwx.common.security.SecurityManager;
import com.hqwx.common.security.model.UserInfo;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.List;

/**
 * 多租户处理实现类
 * create time: ${_currentTime}
 */
public class TenantHandlerImpl implements TenantLineHandler {

	/**
	 * 多租户配置
	 */
	private TenantFilterProperties properties;

	public TenantHandlerImpl(TenantFilterProperties properties) {
		this.properties = properties;
	}

	/**
	 * 获取租户ID值
	 */
	@Override
	public Expression getTenantId() {
		UserInfo userInfo = SecurityManager.getUserQuietly();
		if (userInfo != null) {
			return new LongValue(userInfo.getSchId());
		}

		Long schId = TenantIdContext.getSchId();
		if (schId != null) {
			return new LongValue(schId);
		}

		return new LongValue("-1000");
	}

	/**
	 * 租户ID对应表的所有的列,默认sch_id, 可以在配置文件里覆盖
	 */
	@Override
	public String getTenantIdColumn() {
		// 当机构id的值为null时，则不进行机构隔离操作，-1000仅用来判断是否需使用mybatis-plus的机构隔离，无其他含义。
		LongValue longValue = (LongValue)this.getTenantId();
		return longValue.getValue() == -1000L ? "-1000" : properties.getTenantIdColumn();
	}
	
	/**
	 * 判断该表名在执行sql时是否要排除 租户ID 这个字段或条件项
     * 当某个表中不存在租户ID时可以将该表名添加到 excludeTableNames 属性下面, 配置示例如下:
     * tenant-filter:
     *   scan-package: cn.xxx.xxx.infrastructure.repository.model
     *   excludeTableNames:
     *   - test_table1
     *   - test_table2
     *   或者
     *   excludeTableNames: [test_table1, test_table2]
     */
	@Override
	public boolean ignoreTable(String tableName) {
		List<String> excludeTableNames = properties.getExcludeTableNames();
		return excludeTableNames != null && excludeTableNames.contains(tableName);
	}

}
