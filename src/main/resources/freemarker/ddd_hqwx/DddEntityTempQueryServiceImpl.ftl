package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.impl;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}BaseDTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.${_poEntity.entityName}QueryService;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.param.${_poEntity.entityName}Query;
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName};
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName}Repository;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* ${_poEntity.entityNameChinese}查询服务实现类
* create time: ${_currentTime}
*/
@Service
public class ${_poEntity.entityName}QueryServiceImpl implements ${_poEntity.entityName}QueryService {
    @Resource
    private ${_poEntity.entityName}Repository ${_poEntity.entityNameInitialLowercase}Repository;

    @Override
    public ${_poEntity.entityName} findEntityById(Long id) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findEntityById(id);
    }

    @Override
    public ${_poEntity.entityName}DTO findById(Long id) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findById(id);
    }

    @Override
    public <T extends ${_poEntity.entityName}BaseDTO>T findById(Long id, Class<T> clazz) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findById(id, clazz);
    }

    /**
     * 从写库中获取数据，用于实时性要求高的处理, 比如批量处理前判断数据是否已经存在, 创建后立即返回最新的数据等等
     */
    @Override
    public ${_poEntity.entityName}DTO findByIdFromWriteDb(Long id) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findByIdFromWriteDb(id);
    }

    @Override
    public <T extends ${_poEntity.entityName}BaseDTO> List<T> findByIds(List<Long> ids, Class<T> clazz) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findByIds(ids, clazz);
    }

    @Override
    public List<${_poEntity.entityName}DTO> findList(${_poEntity.entityName}Query query) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findList(query);
    }

    @Override
    public <T extends ${_poEntity.entityName}BaseDTO> List<T> findList(${_poEntity.entityName}Query query, Class<T> clazz) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findList(query, clazz);
    }

    @Override
    public Page<${_poEntity.entityName}DTO> findPage(${_poEntity.entityName}Query query) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findPage(query);
    }

    @Override
    public Page findPage(${_poEntity.entityName}Query query, Class clazz) {
        return ${_poEntity.entityNameInitialLowercase}Repository.findPage(query, clazz);
    }
}
