package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.assembler;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}AddCommand;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}UpdateCommand;
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName};
import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import org.springframework.beans.BeanUtils;

/**
* ${_poEntity.entityNameChinese}实体组装器, 可用于将创建、修改、复制等VO参数组装成实体对象
* create time: ${_currentTime}
*/
public class ${_poEntity.entityName}Assembler {
    public static ${_poEntity.entityName} toEntityByAdd(${_poEntity.entityName}AddCommand addCommand) {
        ${_poEntity.entityName}PO baseData = new ${_poEntity.entityName}PO();

        BeanUtils.copyProperties(addCommand, baseData);

        // 添加其它属性，比如 设置默认值，有效时间，创建者更新者信息等

        return new ${_poEntity.entityName}(baseData);
    }

    public static ${_poEntity.entityName} toEntityByUpdate(${_poEntity.entityName}UpdateCommand updateCommand) {
        ${_poEntity.entityName}PO baseData = new ${_poEntity.entityName}PO();

        BeanUtils.copyProperties(updateCommand, baseData);

        // 添加其它属性，比如 设置默认值，有效时间，更新者信息等

        return new ${_poEntity.entityName}(baseData);
    }

    private ${_poEntity.entityName}Assembler() {
    }
}
