package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}AddCommand;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}DeleteCommand;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}UpdateCommand;

/**
* ${_poEntity.entityNameChinese}命令服务接口, 可用于对实体数据进行创建、修改、删除等操作
* create time: ${_currentTime}
*/
public interface ${_poEntity.entityName}CommandService {
    Long add(${_poEntity.entityName}AddCommand addCommand);

    int update(${_poEntity.entityName}UpdateCommand updateCommand);

    /**
     * 支持单个删除或批量删除
     */
    int delete(${_poEntity.entityName}DeleteCommand deleteCommand);
}