package ${_poEntity.packageMainPath}.api.feign;

import ${_poEntity.packageMainPath}.application.${_poEntity.aggregateName}.dto.${_poEntity.entityName}FeignDTO;
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
 * feign->${_poEntity.entityNameChinese}查询和同步(内部服务调用)
 * Feign接口一般用于被其它内部服务接口调用，以内部查询为主，也可以包含少量更新状态、同步、回调等接口
 * create time: ${_currentTime}
 */
@ApiSort(300)
@Api(value = "feign->${_poEntity.entityNameChinese}查询和同步", tags = "feign->${_poEntity.entityNameChinese}查询和同步")
@RestController
@RequestMapping("/feign/v1/${_poEntity.entityNameSplitStr}")
public class ${_poEntity.entityName}FeignController {
    @Resource
    private ${_poEntity.entityName}QueryService ${_poEntity.entityNameInitialLowercase}QueryService;

    @ApiOperation(value="按主键查询", notes="按主键查询${_poEntity.entityNameChinese}", httpMethod="GET")
    @ApiOperationSupport(order=200)
    @GetMapping("/{id}")
    public ResponseBean<${_poEntity.entityName}FeignDTO> findById(@PathVariable("id") Long id) {
        return ResponseBean.success(${_poEntity.entityNameInitialLowercase}QueryService.findById(id, ${_poEntity.entityName}FeignDTO.class));
    }

    @ApiOperation(value = "批量主键查询", notes = "一次传入多个主键批量查询${_poEntity.entityNameChinese}列表", httpMethod = "POST")
    @ApiOperationSupport(order = 210)
    @PostMapping("/find-ids")
    public ResponseBean<List<${_poEntity.entityName}FeignDTO>> findByIds(@RequestBody List<Long> ids) {
        return ResponseBean.success(${_poEntity.entityNameInitialLowercase}QueryService.findByIds(ids, ${_poEntity.entityName}FeignDTO.class));
    }

    @ApiOperation(value="分页查询", notes="分页查询${_poEntity.entityNameChinese}(多条件, 分页)", httpMethod="POST")
    @ApiOperationSupport(order=220)
    @PostMapping("/search")
    public ResponseBean<Page<${_poEntity.entityName}FeignDTO>> search(@RequestBody ${_poEntity.entityName}Query query) {
        Page page = ${_poEntity.entityNameInitialLowercase}QueryService.findPage(query, ${_poEntity.entityName}FeignDTO.class);
        return ResponseBean.success(page);
    }

    @ApiOperation(value="查询列表", notes="查询${_poEntity.entityNameChinese}列表", httpMethod="POST")
    @ApiOperationSupport(order=236)
    @PostMapping("/find-list")
    public ResponseBean<List<${_poEntity.entityName}FeignDTO>> findList(@RequestBody ${_poEntity.entityName}Query query) {
        return ResponseBean.success(${_poEntity.entityNameInitialLowercase}QueryService.findList(query, ${_poEntity.entityName}FeignDTO.class));
    }

}
