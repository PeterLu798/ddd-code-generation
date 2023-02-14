package ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName};

import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import cn.huanju.edu100.bizplat.base.model.DomainEntity;

/**
* ${_poEntity.entityNameChinese}实体
* create time: ${_currentTime}
*/
public class ${_poEntity.entityName} implements DomainEntity {
    private ${_poEntity.entityName}PO baseData;

    public ${_poEntity.entityName}() {
    }

    public ${_poEntity.entityName}(${_poEntity.entityName}PO baseData) {
        this.baseData = baseData;
    }

    public ${_poEntity.entityName}PO getBaseData() {
        return baseData;
    }
    public void setBaseData(${_poEntity.entityName}PO baseData) {
        this.baseData = baseData;
    }
}
