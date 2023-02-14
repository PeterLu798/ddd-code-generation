package ${_poEntity.packageMainPath}.infrastructure.repository.impl;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}BaseDTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
<#if _configCommand.onlyRepositoryFlag == 0 >
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.param.${_poEntity.entityName}Query;
</#if>
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName};
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName}Repository;
<#if _configCommand.batchProcessSupportFlag == 1 >
import ${_poEntity.packageMainPath}.infrastructure.repository.daoservice.${_poEntity.entityName}DaoService;
</#if>
import ${_poEntity.packageMainPath}.infrastructure.repository.converter.${_poEntity.entityName}Converter;
import ${_poEntity.packageMainPath}.infrastructure.repository.mapper.${_poEntity.entityName}CommandMapper;
import ${_poEntity.packageMainPath}.infrastructure.repository.mapper.${_poEntity.entityName}QueryMapper;
import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* ${_poEntity.entityNameChinese}存储库实现类, 对应数据表名为 <#if _poEntity.tableName?exists>${_poEntity.tableName}</#if>
* create time: ${_currentTime}
*/
@Component
public class ${_poEntity.entityName}RepositoryImpl implements ${_poEntity.entityName}Repository {
    @Resource
    private ${_poEntity.entityName}QueryMapper ${_poEntity.entityNameInitialLowercase}QueryMapper;
    @Resource
    private ${_poEntity.entityName}CommandMapper ${_poEntity.entityNameInitialLowercase}CommandMapper;
  <#if _configCommand.batchProcessSupportFlag == 1 >
    // 下面接口中的批量处理方法(saveBatch saveOrUpdateBatch等)只能在 master 主数据源所属的数据表上才能执行成功
    @Resource
    private ${_poEntity.entityName}DaoService ${_poEntity.entityNameInitialLowercase}DaoService;
  </#if>

    @Override
    public ${_poEntity.entityName} findEntityById(Long id) {
        ${_poEntity.entityName}PO po = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectById(id);
        if (po == null) {
            return null;
        }

        return new ${_poEntity.entityName}(po);
    }

    @Override
    public ${_poEntity.entityName}DTO findById(Long id) {
        ${_poEntity.entityName}PO po = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectById(id);
        return ${_poEntity.entityName}Converter.toDTO(po);
    }

    @Override
    public <T extends ${_poEntity.entityName}BaseDTO>T findById(Long id, Class<T> clazz) {
        ${_poEntity.entityName}PO po = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectById(id);
        return ${_poEntity.entityName}Converter.toDTO(po, clazz);
    }
    
    @Override
    public <T extends ${_poEntity.entityName}BaseDTO> List<T> findByIds(List<Long> ids, Class<T> clazz) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<${_poEntity.entityName}PO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(${_poEntity.entityName}PO::getId, ids);
        List<${_poEntity.entityName}PO> poList = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectList(queryWrapper);

        return ${_poEntity.entityName}Converter.toDtoList(poList, clazz);
    }

    /**
     * 从写库中获取数据，用于实时性要求高的处理, 比如批量处理前判断数据是否已经存在, 创建后立即返回最新的数据等等
     * 需要在 该方法名上 或 XxxCommandMapper 上加 @DS("master") 指定主数据源
     */
    // @DS("master")
    @Override
    public ${_poEntity.entityName}DTO findByIdFromWriteDb(Long id) {
        ${_poEntity.entityName}PO po = ${_poEntity.entityNameInitialLowercase}CommandMapper.selectById(id);
        return ${_poEntity.entityName}Converter.toDTO(po);
    }

  <#if _configCommand.onlyRepositoryFlag == 1 >
    /**
     * 下面这几个是默认生成的查询方法, 可用于API分页和列表查询，如不需要请自行删除
  </#if>
    @Override
    public List<${_poEntity.entityName}DTO> findList(${_poEntity.entityName}Query query) {
        LambdaQueryWrapper<${_poEntity.entityName}PO> queryWrapper = this.buildQueryWrapper(query, false);

        List<${_poEntity.entityName}PO> poList = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectList(queryWrapper);

        return ${_poEntity.entityName}Converter.toDtoList(poList);
    }

    @Override
    public <T extends ${_poEntity.entityName}BaseDTO> List<T> findList(${_poEntity.entityName}Query query, Class<T> clazz) {
        LambdaQueryWrapper<${_poEntity.entityName}PO> queryWrapper = this.buildQueryWrapper(query, false);

        List<${_poEntity.entityName}PO> poList = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectList(queryWrapper);

        return ${_poEntity.entityName}Converter.toDtoList(poList, clazz);
    }

    @Override
    public Page<${_poEntity.entityName}DTO> findPage(${_poEntity.entityName}Query query) {
        LambdaQueryWrapper<${_poEntity.entityName}PO> queryWrapper = this.buildQueryWrapper(query, true);

        Page<${_poEntity.entityName}PO> page = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectPage(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);

        List<${_poEntity.entityName}DTO> dtoList = ${_poEntity.entityName}Converter.toDtoList(page.getRecords());

        Page<${_poEntity.entityName}DTO> dtoPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        dtoPage.setRecords(dtoList);
        return dtoPage;
    }

    @Override
    public Page findPage(${_poEntity.entityName}Query query, Class clazz) {
        LambdaQueryWrapper<${_poEntity.entityName}PO> queryWrapper = this.buildQueryWrapper(query, true);

        Page<${_poEntity.entityName}PO> page = ${_poEntity.entityNameInitialLowercase}QueryMapper.selectPage(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);

        List dtoList = ${_poEntity.entityName}Converter.toDtoList(page.getRecords(), clazz);

        Page dtoPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        dtoPage.setRecords(dtoList);
        return dtoPage;
    }

    private LambdaQueryWrapper<${_poEntity.entityName}PO> buildQueryWrapper(${_poEntity.entityName}Query query, boolean pagingFlag) {
        LambdaQueryWrapper<${_poEntity.entityName}PO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(query.getId()), ${_poEntity.entityName}PO::getId, query.getId());
        queryWrapper.in(Objects.nonNull(query.getIds()), ${_poEntity.entityName}PO::getId, query.getIds());
        // queryWrapper.like(StringUtils.isNotBlank(query.getName()), ${_poEntity.entityName}PO::getName, query.getName());

        //TODO 其它更多查询条件请添加在这里

        // queryWrapper.orderByAsc(${_poEntity.entityName}PO::getSortNum);

        if (!pagingFlag) {
            // 如果不是分页查询, 设置默认一次查询最多返回N条, 防止返回数据量过大, 可以由调用方设置 pageSize 来调整最大返回数量
            int pageSize = 300;
            if (query.getPageSize() != null && query.getPageSize() > 0) {
                pageSize = query.getPageSize();
            }
            queryWrapper.last(" limit " + pageSize);
        }

        return queryWrapper;
    }
  <#if _configCommand.onlyRepositoryFlag == 1 >
    */
  </#if>

    @Override
    public Long add(${_poEntity.entityName} entity) {
        ${_poEntity.entityName}PO baseData = entity.getBaseData();

        ${_poEntity.entityNameInitialLowercase}CommandMapper.insert(baseData);

        return baseData.getId();
    }

  <#if _configCommand.batchProcessSupportFlag == 1 >
    /**
     * 批量添加
     */
    @Override
    public boolean addBatch(List<${_poEntity.entityName}> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        }

        List<${_poEntity.entityName}PO> poList = new ArrayList<>();
        for (${_poEntity.entityName} entity : entityList) {
            poList.add(entity.getBaseData());
        }

        return ${_poEntity.entityNameInitialLowercase}DaoService.saveBatch(poList);
    }
  </#if>

    @Override
    public int update(${_poEntity.entityName} entity) {
        ${_poEntity.entityName}PO baseData = entity.getBaseData();

        return ${_poEntity.entityNameInitialLowercase}CommandMapper.updateById(baseData);
    }

    @Override
    public int deleteById(Long id) {
        if (id == null) {
            return -1;
        }

        return ${_poEntity.entityNameInitialLowercase}CommandMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return -1;
        }

        return ${_poEntity.entityNameInitialLowercase}CommandMapper.deleteBatchIds(ids);
    }
}
