package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}BaseDTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.param.${_poEntity.entityName}Query;
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* ${_poEntity.entityNameChinese}查询服务接口
* create time: ${_currentTime}
*/
public interface ${_poEntity.entityName}QueryService {
    ${_poEntity.entityName} findEntityById(Long id);

    ${_poEntity.entityName}DTO findById(Long id);

    <T extends ${_poEntity.entityName}BaseDTO>T findById(Long id, Class<T> clazz);

    /**
     * 从写库中获取数据，用于实时性要求高的处理, 比如批量处理前判断数据是否已经存在, 创建后立即返回最新的数据等等
     */
    ${_poEntity.entityName}DTO findByIdFromWriteDb(Long id);

    <T extends ${_poEntity.entityName}BaseDTO> List<T> findByIds(List<Long> ids, Class<T> clazz);

    List<${_poEntity.entityName}DTO> findList(${_poEntity.entityName}Query query);

    <T extends ${_poEntity.entityName}BaseDTO> List<T> findList(${_poEntity.entityName}Query query, Class<T> clazz);

    Page<${_poEntity.entityName}DTO> findPage(${_poEntity.entityName}Query query);

    Page findPage(${_poEntity.entityName}Query query, Class clazz);
}
