package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
* ${_poEntity.entityNameChinese}DTO
* 该DTO包含的属性比较完整, 可用于管理端或前端的数据查询
* create time: ${_currentTime}
*/
@Getter
@Setter
@ToString
@ApiModel(value="${_poEntity.entityNameChinese}DTO")
public class ${_poEntity.entityName}DTO extends ${_poEntity.entityName}BaseDTO {

<#if _poEntity.propertyList?exists && _poEntity.propertyList?size gt 0>
  <#list _poEntity.propertyList as __propertyObj>
    <#if __propertyObj.propertyName == 'id' || __propertyObj.propertyName == 'name' || __propertyObj.propertyName == 'delFlag' >
      <#-- 排除上面这些属性, 不生成 -->
    <#else>
    @ApiModelProperty(value = "<#if __propertyObj.propertyDesc != ''>${__propertyObj.propertyDesc}<#else>${__propertyObj.propertyName}</#if>", position = ${__propertyObj.position})
    private ${__propertyObj.propertyType} ${__propertyObj.propertyName};

    </#if>
  </#list>
</#if>
}