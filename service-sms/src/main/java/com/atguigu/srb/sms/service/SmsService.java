package com.atguigu.srb.sms.service;

import java.util.Map;

/**
 * @author wanfeng
 * @created 2022/4/30 19:56
 * @package com.atguigu.srb.sms
 */
public interface SmsService {
    void send(String mobile, String templateCode, Map<String,Object> param);
}
