<#-- 根据PO类定义创建对应的建表SQL语句  2022-01-10 add -->
# create time: ${_currentTime}
# 注: 自动生成的SQL只能作为参考, 不能完全作为上线的真实SQL, 有些缺失或不正确的信息需要自行补充

<#assign __tableName = 'unknown_table' />
<#if _poEntity.tableName?exists && _poEntity.tableName != ''>
  <#assign __tableName = _poEntity.tableName />
</#if>
CREATE TABLE ${__tableName} (
<#if _poEntity.propertyList?exists && _poEntity.propertyList?size gt 0>
  <#list _poEntity.propertyList as __propertyObj>
    <#assign __fieldType = 'varchar(60)' />
    <#assign __fieldDefaultValue = '' />
    <#if __propertyObj.propertyType == 'Long' >
      <#assign __fieldType = 'bigint(20)' />
      <#assign __fieldDefaultValue = '0' />
    <#elseif __propertyObj.propertyType == 'Integer' >
      <#assign __fieldType = 'int(11)' />
      <#assign __fieldDefaultValue = '0' />
    <#elseif __propertyObj.propertyType == 'Double' >
      <#assign __fieldType = 'double' />
      <#assign __fieldDefaultValue = '0' />
    <#elseif __propertyObj.propertyType == 'Float' >
      <#assign __fieldType = 'float' />
      <#assign __fieldDefaultValue = '0' />
    <#elseif __propertyObj.propertyType == 'BigDecimal' >
      <#assign __fieldType = 'decimal(12,2)' />
      <#assign __fieldDefaultValue = '0' />
    <#elseif __propertyObj.propertyType == 'Date' >
        <#assign __fieldType = 'datetime' />
        <#assign __fieldDefaultValue = 'NULL' />
    </#if>
    <#-- 首先进行默认字段的处理, 如 id, create_date -->
    <#if __propertyObj.fieldName == 'id' >
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    <#elseif __propertyObj.fieldName == 'create_time' >
    create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    <#elseif __propertyObj.fieldName == 'create_date' >
    create_date timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    <#elseif __propertyObj.fieldName == 'update_time' >
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
    <#elseif __propertyObj.fieldName == 'update_date' >
    update_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
    <#elseif __propertyObj.fieldName == 'del_flag' >
    del_flag tinyint(4) DEFAULT 0 COMMENT '删除标记, 默认 0 正常, 1 已删除(逻辑删除)',
    <#else>
      <#if __propertyObj.requiredFlag?exists && __propertyObj.requiredFlag == 1>
    ${__propertyObj.fieldName} ${__fieldType} NOT NULL COMMENT '<#if __propertyObj.propertyDesc != ''>${__propertyObj.propertyDesc}<#else>${__propertyObj.propertyName}</#if>',
      <#elseif __fieldType?index_of('varchar') gte 0>
    ${__propertyObj.fieldName} ${__fieldType} DEFAULT '' COMMENT '<#if __propertyObj.propertyDesc != ''>${__propertyObj.propertyDesc}<#else>${__propertyObj.propertyName}</#if>',
      <#else>
    ${__propertyObj.fieldName} ${__fieldType} DEFAULT ${__fieldDefaultValue} COMMENT '<#if __propertyObj.propertyDesc != ''>${__propertyObj.propertyDesc}<#else>${__propertyObj.propertyName}</#if>',
      </#if>
    </#if>
  </#list>
</#if>
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='<#if _poEntity.entityNameChinese?exists>${_poEntity.entityNameChinese}</#if><#if _currentDate?exists && _currentDate != ''>, ${_currentDate}</#if>';

# 注: 自动生成的SQL只能作为参考, 不能完全作为上线的真实SQL, 有些缺失或不正确的信息需要自行补充

# 增加新的表字段, 一般除时间类型的字段外都需要设置默认值, 示例SQL如下
ALTER TABLE xxx_db.${__tableName} ADD COLUMN field_xxx varchar(60) DEFAULT '' COMMENT '添加字符串列示例, ${_currentDate}';
ALTER TABLE xxx_db.${__tableName} ADD COLUMN field_xxx_id bigint(20) DEFAULT 0 COMMENT '添加长整型列示例, ${_currentDate}';
ALTER TABLE xxx_db.${__tableName} ADD COLUMN field_xxx_flag int(11) DEFAULT 0 COMMENT '添加整形列示例, ${_currentDate}';

# 增加表索引, 索引名称以 idx_ 为前缀
ALTER TABLE xxx_db.${__tableName} ADD INDEX idx_xx_xxx (field_xx1, field_xx2);
