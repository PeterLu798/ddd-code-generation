package ${_poEntity.packageMainPath}.infrastructure.repository.mapper;

import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* ${_poEntity.entityNameChinese}查询接口(数据库)
* create time: ${_currentTime}
*
* 可以使用 @DS 注解可以指定数据源(未指定时默认使用 slave), 写法示例如下:
* @DS("xxx_xx_read")
*/
@Mapper
public interface ${_poEntity.entityName}QueryMapper extends BaseMapper<${_poEntity.entityName}PO> {
}