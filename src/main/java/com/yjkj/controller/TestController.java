package com.yjkj.controller;

import com.alibaba.fastjson.JSON;
import com.miitang.gateway.sdk.MtGatewaySdk;
import com.miitang.gateway.sdk.dto.req.ModifyMerchantFeeReq;
import com.miitang.gateway.sdk.dto.req.RegisterAndAccessReq;
import com.miitang.gateway.sdk.dto.req.UploadQualificationReq;
import com.miitang.gateway.sdk.dto.res.ModifyMerchantFeeRes;
import com.miitang.gateway.sdk.dto.res.RegisterAndAccessRes;
import com.miitang.gateway.sdk.dto.res.UploadQualificationRes;
import com.yjkj.constant.YbConstant;
import com.yjkj.entity.BindEntity;
import com.yjkj.entity.InstitutionEntity;
import com.yjkj.entity.UpdateRateEntity;
import com.yjkj.entity.user.MerChants;
import com.yjkj.service.epos.BindEntityService;
import com.yjkj.service.epos.InstitutionService;
import com.yjkj.service.epos.UpdateRateService;
import com.yjkj.service.user.MerChantsService;
import com.yjkj.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bin on 2018/8/14.
 */
@RestController
@RequestMapping("/test/")
public class TestController {


   /* @RequestMapping(value = "t1")
    public YJResult t1(Long startTime,Long endTime) {
        //Long startTime=1551369600000L;//开始当天时间戳
        //Long endTime=1551369600000L;//结束当天时间戳

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int i=0;
        for (;;){
            i++;
            String str=sdf.format(new Date(startTime));
            System.out.println(str);
            String r=HttpClientUtil.doGet("http://47.104.22.226/yj-account/count/createAppT1?createDate="+str);//T1统计
            System.out.println(r);
            //String r2=HttpClientUtil.doGet("http://47.104.22.226/yj-account/count/createT1?createDate="+str);//T10单统计
            //System.out.println(r2);
            startTime+=24*60*60*1000L;
            if (DateUtil.zero(endTime)<DateUtil.zero(startTime)){
                break;
            }
        }
        return YJResult.ok(i);
    }

    @RequestMapping(value = "t1App")
    public YJResult t1App(Long startTime,Long endTime) {
        //Long startTime=1551369600000L;//开始当天时间戳
        //Long endTime=1551369600000L;//结束当天时间戳

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int i=0;
        for (;;){
            i++;
            String str=sdf.format(new Date(startTime));
            System.out.println(str);
            //String r=HttpClientUtil.doGet("http://47.104.22.226/yj-account/count/createAppT1?createDate="+str);//T1统计
            //System.out.println(r);
            String r2=HttpClientUtil.doGet("http://47.104.22.226/yj-account/count/createT1?createDate="+str);//T10单统计
            System.out.println(r2);
            startTime+=24*60*60*1000L;
            if (DateUtil.zero(endTime)<DateUtil.zero(startTime)){
                break;
            }
        }
        return YJResult.ok(i);
    }*/


}
