package ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.command.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import java.util.Date;

/**
* ${_poEntity.entityNameChinese}创建参数
* create time: ${_currentTime}
*/
@Getter
@Setter
@ToString
@ApiModel(value="${_poEntity.entityNameChinese}创建参数")
public class ${_poEntity.entityName}AddCommand implements Serializable {

    //TODO 以下是自动生成的初始参数, 请根据业务需要进行调整

<#if _poEntity.propertyList?exists && _poEntity.propertyList?size gt 0>
  <#list _poEntity.propertyList as __propertyObj>
    <#if __propertyObj.propertyName == 'id' || __propertyObj.propertyName == 'schId' || __propertyObj.propertyName == 'landId' || __propertyObj.propertyName == 'delFlag'
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
