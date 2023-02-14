package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
* ${_poEntity.entityNameChinese}查询条件
* create time: ${_currentTime}
*/
@Getter
@Setter
@ToString
@ApiModel(value="${_poEntity.entityNameChinese}查询条件")
public class ${_poEntity.entityName}Query implements Serializable {
    private Long id;

    private List<Long> ids;

    @ApiModelProperty(value = "名称, 一般用于 模糊查询", position = 120)
    private String name;
    
    //TODO 设置更多查询条件参数

    @ApiModelProperty(value = "分页页索引, 默认值1, 第一页", position = 910)
    private Integer pageIndex;

    @ApiModelProperty(value = "分页每页大小, 默认值10", position = 920)
    private Integer pageSize;
}
