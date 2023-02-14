package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
* ${_poEntity.entityNameChinese}删除参数, 支持单个或者批量删除
* create time: ${_currentTime}
*/
@Getter
@Setter
@ToString
@ApiModel(value="${_poEntity.entityNameChinese}删除参数")
public class ${_poEntity.entityName}DeleteCommand implements Serializable {

    @ApiModelProperty(value = "单个删除, 与批量删除之间只能选择其中一种方式, 优先单个删除", position = 110)
    private Long id;

    @ApiModelProperty(value = "批量删除", position = 120)
    private List<Long> ids;
}
