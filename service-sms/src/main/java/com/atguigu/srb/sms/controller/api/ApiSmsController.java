package com.atguigu.srb.sms.controller.api;

import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.util.RandomUtils;
import com.atguigu.srb.common.util.RegexValidateUtils;
import com.atguigu.srb.sms.client.CoreUserInfoClient;
import com.atguigu.srb.sms.service.SmsService;
import com.atguigu.srb.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wanfeng
 * @created 2022/4/30 20:07
 * @package com.atguigu.srb.sms.controller.api
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private CoreUserInfoClient coreUserInfoClient;


    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        // feign远程调用 查看当前手机号是否已经注册
        Assert.isTrue(!coreUserInfoClient.checkMobile(mobile),ResponseEnum.MOBILE_EXIST_ERROR);
        //生成验证码
        String code = RandomUtils.getFourBitRandom();
        //组装短信模板参数
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        //将验证码存入redis   肯定得先村redis  不然 redis宕机 信息发送失效了！   只存储5分钟
        redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);
        //发送短信
        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, param);


        return R.ok().message("短信发送成功");
    }
}