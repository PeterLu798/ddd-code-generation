package ${_poEntity.packageMainPath}.infrastructure.repository.daoservice;

import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ${_poEntity.entityNameChinese} 数据访问服务接口，可以执行 saveBatch saveOrUpdateBatch 等批量处理方法，一般只能在基础设施层中使用
 * 该接口中的批量保存方法(saveBatch saveOrUpdateBatch等)只能在 master 主数据源所属的数据表上才能执行成功
 */
public interface ${_poEntity.entityName}DaoService extends IService<${_poEntity.entityName}PO> {
}
