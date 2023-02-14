package ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName};

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}BaseDTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
<#if _configCommand.onlyRepositoryFlag == 0 >
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.param.${_poEntity.entityName}Query;
</#if>

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* ${_poEntity.entityNameChinese}存储库接口, 对应数据表名为 <#if _poEntity.tableName?exists>${_poEntity.tableName}</#if>
* 该接口具体实现类在基础设施层 infrastructure.repository.impl
* create time: ${_currentTime}
*/
public interface ${_poEntity.entityName}Repository {

    ${_poEntity.entityName} findEntityById(Long id);

    ${_poEntity.entityName}DTO findById(Long id);

    <T extends ${_poEntity.entityName}BaseDTO>T findById(Long id, Class<T> clazz);

    /**
     * 从写库中获取数据，用于实时性要求高的处理, 比如批量处理前判断数据是否已经存在, 创建后立即返回最新的数据等等
     * 需要在 该方法名上 或 XxxCommandMapper 上加 @DS("master") 指定主数据源
     */
    ${_poEntity.entityName}DTO findByIdFromWriteDb(Long id);

    <T extends ${_poEntity.entityName}BaseDTO> List<T> findByIds(List<Long> ids, Class<T> clazz);

  <#if _configCommand.onlyRepositoryFlag == 1 >
    /**
     * 下面这几个是默认生成的查询方法, 可用于API分页和列表查询，如不需要请自行删除
    List<${_poEntity.entityName}DTO> findList(${_poEntity.entityName}Query query);

    <T extends ${_poEntity.entityName}BaseDTO> List<T> findList(${_poEntity.entityName}Query query, Class<T> clazz);

    Page<${_poEntity.entityName}DTO> findPage(${_poEntity.entityName}Query query);

    Page findPage(${_poEntity.entityName}Query query, Class clazz);
     */
  <#else>
    List<${_poEntity.entityName}DTO> findList(${_poEntity.entityName}Query query);

    <T extends ${_poEntity.entityName}BaseDTO> List<T> findList(${_poEntity.entityName}Query query, Class<T> clazz);

    Page<${_poEntity.entityName}DTO> findPage(${_poEntity.entityName}Query query);

    Page findPage(${_poEntity.entityName}Query query, Class clazz);
  </#if>

    Long add(${_poEntity.entityName} entity);

  <#if _configCommand.batchProcessSupportFlag == 1 >
    /**
     * 批量添加
     */
    boolean addBatch(List<${_poEntity.entityName}> entityList);
  </#if>

    int update(${_poEntity.entityName} entity);

    int deleteById(Long id);

    int deleteByIds(List<Long> ids);
}
