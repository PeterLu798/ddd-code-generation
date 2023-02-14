<#-- DDD领域实体对象基础DTO对象生成模板  2022-01-10 add -->
package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
* ${_poEntity.entityNameChinese}基础DTO
* 只包含最基础的属性, 用于被各种业务场景派生的DTO继承
* create time: ${_currentTime}
*/
@Getter
@Setter
@ToString
@ApiModel(value="${_poEntity.entityNameChinese}基础DTO")
public class ${_poEntity.entityName}BaseDTO implements Serializable {

    @ApiModelProperty(value = "ID", position = 110)
    private Long id;

<#if _poEntity.propertyList?exists && _poEntity.propertyList?size gt 0>
  <#list _poEntity.propertyList as __propertyObj>
    <#if __propertyObj.propertyName == 'name' >
    @ApiModelProperty(value = "名称", position = 120)
    private String name;
    </#if>
  </#list>
</#if>
}