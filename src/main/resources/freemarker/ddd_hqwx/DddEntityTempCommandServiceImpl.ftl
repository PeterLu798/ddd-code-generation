package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.impl;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.assembler.${_poEntity.entityName}Assembler;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.${_poEntity.entityName}CommandService;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}AddCommand;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}DeleteCommand;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param.${_poEntity.entityName}UpdateCommand;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName};
import ${_poEntity.packageMainPath}.domain.aggregate.${_poEntity.aggregateName}.${_poEntity.entityName}Repository;
import cn.huanju.edu100.bizplat.base.exception.DataNotFoundException;
import cn.huanju.edu100.bizplat.base.exception.ParamErrorException;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* ${_poEntity.entityNameChinese}命令服务实现类, 可用于对实体数据进行创建、修改、删除等操作
* create time: ${_currentTime}
*/
@Slf4j
@Service
public class ${_poEntity.entityName}CommandServiceImpl implements ${_poEntity.entityName}CommandService {
    @Resource
    private ${_poEntity.entityName}Repository ${_poEntity.entityNameInitialLowercase}Repository;

    @Override
    public Long add(${_poEntity.entityName}AddCommand addCommand) {
        ${_poEntity.entityName} entity = ${_poEntity.entityName}Assembler.toEntityByAdd(addCommand);

        return ${_poEntity.entityNameInitialLowercase}Repository.add(entity);
    }

    @Override
    public int update(${_poEntity.entityName}UpdateCommand updateCommand) {
        ${_poEntity.entityName} entity = ${_poEntity.entityName}Assembler.toEntityByUpdate(updateCommand);

        return ${_poEntity.entityNameInitialLowercase}Repository.update(entity);
    }

    /**
     * 支持单个删除或批量删除
     */
    @Override
    public int delete(${_poEntity.entityName}DeleteCommand deleteCommand) {
        List<Long> deleteIds = null;
        if (deleteCommand.getId() != null && deleteCommand.getId() > 0L) {
            deleteIds = new ArrayList<>();
            deleteIds.add(deleteCommand.getId());
        } else if (CollectionUtils.isNotEmpty(deleteCommand.getIds())) {
            deleteIds = deleteCommand.getIds();
        } else {
            throw new ParamErrorException("参数不正确，请检查");
        }

        List<${_poEntity.entityName}DTO> dataList = ${_poEntity.entityNameInitialLowercase}Repository.findByIds(deleteIds, ${_poEntity.entityName}DTO.class);
        if (CollectionUtils.isEmpty(dataList)) {
            throw new DataNotFoundException("待删除的数据不存在，请检查");
        }

        int num = ${_poEntity.entityNameInitialLowercase}Repository.deleteByIds(deleteIds);
        if (num > 0) {
            for (${_poEntity.entityName}DTO data : dataList) {
                // 此处还可以获取 操作用户的信息 并记录到日志或DB中
                log.info("delete${_poEntity.entityName}ById success, id: {}, data: {}", data.getId(), data.toString());
            }
        }

        return num;
    }
}
