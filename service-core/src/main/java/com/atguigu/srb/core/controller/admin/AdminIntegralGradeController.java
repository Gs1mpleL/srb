package com.atguigu.srb.core.controller.admin;

import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanfeng
 * @created 2022/4/30 12:09
 * @package com.atguigu.srb.core.controller.admin
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
@Slf4j
public class AdminIntegralGradeController {

    @Autowired
    private IntegralGradeService integralGradeService;

    @GetMapping("/list")
    @ApiOperation("列举积分段")
    public R listAll(){
        return R.ok().data("list",integralGradeService.list());
    }

    @ApiOperation(value = "根据id删除积分等级", notes="逻辑删除")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id){
        boolean result = integralGradeService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id
    ){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if(integralGrade != null){
            return R.ok().data("record", integralGrade);
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("修改失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.save(integralGrade);
        //如果借款额度为空就手动抛出一个自定义的异常！
        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

}
