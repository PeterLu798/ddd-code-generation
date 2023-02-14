package ${_poEntity.packageMainPath}.infrastructure.repository.daoservice.impl;

import ${_poEntity.packageMainPath}.infrastructure.repository.daoservice.${_poEntity.entityName}DaoService;
import ${_poEntity.packageMainPath}.infrastructure.repository.mapper.${_poEntity.entityName}CommandMapper;
import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class ${_poEntity.entityName}DaoServiceImpl extends ServiceImpl<${_poEntity.entityName}CommandMapper, ${_poEntity.entityName}PO> implements ${_poEntity.entityName}DaoService {
}
