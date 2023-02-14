package ${_poEntity.packageMainPath}.infrastructure.repository.converter;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}BaseDTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
import ${_poEntity.packageMainPath}.infrastructure.repository.model.${_poEntity.entityName}PO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* ${_poEntity.entityNameChinese}PO对象转换成DTO对象的工具类
* create time: ${_currentTime}
*/
@Slf4j
public class ${_poEntity.entityName}Converter {

    public static ${_poEntity.entityName}DTO toDTO(${_poEntity.entityName}PO po) {
        if (po == null) {
            return null;
        }

        ${_poEntity.entityName}DTO dto = new ${_poEntity.entityName}DTO();
        BeanUtils.copyProperties(po, dto);

        // 不同名属性或扩展属性的值在这里转换或设置

        return dto;
    }

     public static Function<${_poEntity.entityName}PO, ${_poEntity.entityName}DTO> toDTO = (po) -> {
         return ${_poEntity.entityName}Converter.toDTO(po);
     };

    /**
     * 将 PO 对象转换成各种类型的 DTO对象
     */
    public static <T extends ${_poEntity.entityName}BaseDTO>T toDTO(${_poEntity.entityName}PO po, Class<T> clazz) {
        if (po == null && clazz != null) {
            return null;
        }

        T obj = null;
        try {
            obj = clazz.newInstance();

            BeanUtils.copyProperties(po, obj);
            
            // 不同名属性或扩展属性的值在这里转换或设置, 示例如下
            // if (obj instanceof ${_poEntity.entityName}DTO) {
            //     ${_poEntity.entityName}DTO dto = (${_poEntity.entityName}DTO)obj;
            //     // do something
            // } else if (obj instanceof ${_poEntity.entityName}FeignDTO) {
            //     ${_poEntity.entityName}FeignDTO dto = (${_poEntity.entityName}FeignDTO)obj;
            //     // do something
            // }
        } catch (Exception err) {
            log.error("toDTO_error, {}", err.toString(), err);
        }

        return obj;
    }

    public static List<${_poEntity.entityName}DTO> toDtoList(List<${_poEntity.entityName}PO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }
        
        return poList.stream().map(${_poEntity.entityName}Converter::toDTO).collect(Collectors.toList());
    }

    public static <T extends ${_poEntity.entityName}BaseDTO> List<T> toDtoList(List<${_poEntity.entityName}PO> poList, Class<T> clazz) {
        if (poList == null) {
            return new ArrayList<>();
        }

        List<T> dtoList = new ArrayList<>();
        for (${_poEntity.entityName}PO po : poList) {
            T dto = ${_poEntity.entityName}Converter.toDTO(po, clazz);
            dtoList.add(dto);
        }

        return dtoList;
    }

    private ${_poEntity.entityName}Converter() {
    }
}
