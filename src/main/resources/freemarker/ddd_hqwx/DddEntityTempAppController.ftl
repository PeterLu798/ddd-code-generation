package ${_poEntity.packageMainPath}.api.app;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}DTO;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.${_poEntity.entityName}QueryService;
import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.query.param.${_poEntity.entityName}Query;
import cn.huanju.edu100.bizplat.base.model.ResponseBean;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${_poEntity.entityNameChinese}查询和交互(C端接口)
 * create time: ${_currentTime}
 */
@ApiSort(300)
@Api(value = "${_poEntity.entityNameChinese}查询和交互(C端)", tags = "${_poEntity.entityNameChinese}查询和交互")
@RestController
@RequestMapping("/app/v1/${_poEntity.entityNameSplitStr}")
public class ${_poEntity.entityName}Controller {
    @Resource
    private ${_poEntity.entityName}QueryService ${_poEntity.entityNameInitialLowercase}QueryService;

    @ApiOperation(value="按主键查询", notes="按主键查询${_poEntity.entityNameChinese}", httpMethod="GET")
    @ApiOperationSupport(order=200)
    @GetMapping("/{id}")
    public ResponseBean<${_poEntity.entityName}DTO> findById(@PathVariable("id") Long id) {
        ${_poEntity.entityName}DTO dto = ${_poEntity.entityNameInitialLowercase}QueryService.findById(id);
        return ResponseBean.success(dto);
    }

    @ApiOperation(value = "批量主键查询", notes = "一次传入多个主键批量查询${_poEntity.entityNameChinese}列表", httpMethod = "POST")
    @ApiOperationSupport(order = 210)
    @PostMapping("/find-ids")
    public ResponseBean<List<${_poEntity.entityName}DTO>> findByIds(@RequestBody List<Long> ids) {
        return ResponseBean.success(${_poEntity.entityNameInitialLowercase}QueryService.findByIds(ids, ${_poEntity.entityName}DTO.class));
    }

    @ApiOperation(value="分页查询", notes="分页查询${_poEntity.entityNameChinese}(多条件, 分页)", httpMethod="POST")
    @ApiOperationSupport(order=220)
    @PostMapping("/search")
    public ResponseBean<Page<${_poEntity.entityName}DTO>> search(@RequestBody ${_poEntity.entityName}Query query) {
        Page<${_poEntity.entityName}DTO> page = ${_poEntity.entityNameInitialLowercase}QueryService.findPage(query);
        return ResponseBean.success(page);
    }

    @ApiOperation(value="查询列表", notes="查询${_poEntity.entityNameChinese}列表", httpMethod="POST")
    @ApiOperationSupport(order=236)
    @PostMapping("/find-list")
    public ResponseBean<List<${_poEntity.entityName}DTO>> findList(@RequestBody ${_poEntity.entityName}Query query) {
        // return ResponseBean.success(${_poEntity.entityNameInitialLowercase}QueryService.findList(query, ${_poEntity.entityName}DTO.class));
        return ResponseBean.success(${_poEntity.entityNameInitialLowercase}QueryService.findList(query));
    }
}