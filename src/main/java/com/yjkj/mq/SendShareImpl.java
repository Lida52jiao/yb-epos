package com.yjkj.mq;

import com.alibaba.fastjson.JSON;
import com.yjkj.mq.constant.MqConstant;
import com.yjkj.mq.param.EposNotify;
import com.yjkj.util.HttpClientUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by bin on 2018/7/30.
 */
@Service
public class SendShareImpl implements SendShare {
    @Autowired
    private MqConstant mqConstant;
    @Override
    public boolean send2(EposNotify notify) {
        return send(mqConstant.getTAGS_EPOS(), JSON.toJSONString(notify),notify.getOrderNo());
    }
    public boolean send(String tags, String json, String key){
        String url="http://172.31.109.65/sendSimpleMsg";
        HashMap<String, Object> map= Maps.newHashMap();
        map.put("tags",tags);
        map.put("json",json);
        map.put("key",key);
        String result= HttpClientUtil.doPost(url,map);
        return "success".equals(result);
    }
}
