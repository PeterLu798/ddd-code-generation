package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
* ${_poEntity.entityNameChinese}内部Feign调用DTO
* 被其它内部服务接口调用时传递的DTO
* create time: ${_currentTime}
*/
@Getter
@Setter
@ToString
@ApiModel(value="${_poEntity.entityNameChinese}内部Feign调用DTO")
public class ${_poEntity.entityName}FeignDTO extends ${_poEntity.entityName}BaseDTO {

<#if _poEntity.propertyList?exists && _poEntity.propertyList?size gt 0>
  <#list _poEntity.propertyList as __propertyObj>
    <#if __propertyObj.propertyName == 'id' || __propertyObj.propertyName == 'name' || __propertyObj.propertyName == 'delFlag'
      || __propertyObj.propertyName == 'createTime' || __propertyObj.propertyName == 'creatorId' || __propertyObj.propertyName == 'updateTime' || __propertyObj.propertyName == 'updaterId' || __propertyObj.propertyName == 'updaterInfo'
      || __propertyObj.propertyName == 'createDate' || __propertyObj.propertyName == 'createBy' || __propertyObj.propertyName == 'updateBy' || __propertyObj.propertyName == 'updateDate' || __propertyObj.propertyName == 'updateInfo' >
      <#-- 排除上面这些属性, 不生成 -->
    <#else>
    @ApiModelProperty(value = "<#if __propertyObj.propertyDesc != ''>${__propertyObj.propertyDesc}<#else>${__propertyObj.propertyName}</#if>", position = ${__propertyObj.position})
    private ${__propertyObj.propertyType} ${__propertyObj.propertyName};

    </#if>
  </#list>
</#if>
}
