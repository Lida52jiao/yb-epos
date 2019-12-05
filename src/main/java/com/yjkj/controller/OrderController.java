package com.yjkj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miitang.gateway.sdk.MtGatewaySdk;
import com.miitang.gateway.sdk.dto.req.CreateOrderInfoReq;
import com.miitang.gateway.sdk.dto.req.QueryOrderReq;
import com.miitang.gateway.sdk.dto.req.UploadQualificationReq;
import com.miitang.gateway.sdk.dto.res.CreateOrderRes;
import com.miitang.gateway.sdk.dto.res.QueryOrderRes;
import com.miitang.gateway.sdk.dto.res.UploadQualificationRes;
import com.yjkj.constant.UrlConstant;
import com.yjkj.constant.YbConstant;
import com.yjkj.entity.BindEntity;
import com.yjkj.entity.InstitutionEntity;
import com.yjkj.entity.OrderEntity;
import com.yjkj.entity.user.EposMerRate;
import com.yjkj.entity.user.MerChants;
import com.yjkj.service.epos.BindEntityService;
import com.yjkj.service.epos.InstitutionService;
import com.yjkj.service.epos.OrderEntityService;
import com.yjkj.service.epos.UpdateRateService;
import com.yjkj.service.user.MerChantsService;
import com.yjkj.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by bin on 2018/8/14.
 */
@RestController
@RequestMapping("/order/")
public class OrderController {
    @Autowired
    private OrderEntityService orderService;
    @Autowired
    private BindEntityService bindService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private SnowflakeIdWorker idWorker;
    @Autowired
    private UpdateRateService updateRateService;

    @RequestMapping("pay")
    public YJResult pay(String productCode,String aisleCode,String frontCallBackUrl,String lng,String lat,String merchantId,String institutionId,String aisleMerId,Long amount,String creditCardNo,String creditPhone,String creditCardCode,String cv2,String effectiveYear,String effectiveMonth) throws Exception {
        if(DateUtil.payTime(aisleCode)){
            if ("ybq".equals(aisleCode)){
                return YJResult.build("2311","交易时间为8:00-23:20");
            }
            return YJResult.build("2311","交易时间为8:00-19:50");
        }
        String orderNo= OrderUtil.createOrderNo(idWorker,"EP");
        InstitutionEntity institutionEntity=institutionService.findByPrimaryKey(institutionId);
        MerChants mer=merChantsService.getMer(merchantId,institutionEntity.getMerHost());
        //保存订单
        String yuanAmount= BigDecilmalUtil.div(new Double(amount+""),100D,2)+"";
        BindEntity findBind=new BindEntity();
        findBind.setAisleCode(YbConstant.aisleCode);
        findBind.setAisleMerId(aisleMerId);
        findBind.setMerchantId(merchantId);
        BindEntity bind=bindService.findByObject(findBind);
        OrderEntity order=new OrderEntity();
        order.setOrderNo(orderNo);
        order.setInstitutionId(bind.getInstitutionId());
        order.setAgentId(bind.getAgentId());
        order.setAppId(bind.getAppId());
        order.setMerchantId(merchantId);
        order.setAisleCode(aisleCode);
        order.setName(bind.getName());
        order.setPhone(bind.getPhone());
        order.setCardNumber(bind.getCardNumber());
        order.setIssuingBankCode(bind.getIssuingBankCode());
        order.setCreditCardNumber(creditCardNo);
        order.setCreditCardCode(creditCardCode);
        order.setAmount(amount);
        EposMerRate merRateEntity=merChantsService.getEposMerRate(merchantId,aisleCode,institutionEntity.getMerHost());
        Long fee = (long)(amount*new Double(merRateEntity.getRate()));
        order.setFee(fee);
        order.setD0Fee(merRateEntity.getD0Fee());
        order.setArrivalAmount(amount-fee-merRateEntity.getD0Fee());
        order.setState("1");
        orderService.save(order);
        CreateOrderInfoReq createOrderInfoReq = new CreateOrderInfoReq();
        createOrderInfoReq.setRequestNo(orderNo);
        createOrderInfoReq.setOrderAmount(yuanAmount+"");
        createOrderInfoReq.setProductName("日用百货");
        createOrderInfoReq.setProductDesc("日用百货");
        createOrderInfoReq.setPayWay(productCode);//"QUICK_PAY"UPOP
        createOrderInfoReq.setCallBackURL(YbConstant.pay);

        createOrderInfoReq.setMerchantNo(aisleMerId);
        if ("QUICK_PAY".equals(productCode)){
            createOrderInfoReq.setSalesWay("FLAT_ACCOUNT");
            createOrderInfoReq.setPayBankCardNo(creditCardNo);
            createOrderInfoReq.setPayBankPhoneNo(creditPhone);
        }else {
            createOrderInfoReq.setSalesWay("FACE");
        }
        createOrderInfoReq.setLng(lng);
        createOrderInfoReq.setLat(lat);
        createOrderInfoReq.setFrontCallBackUrl(frontCallBackUrl);
        createOrderInfoReq.setPayIdCardNo(mer.getCertNo());
        createOrderInfoReq.setPayIdCardName(mer.getMerName());
        createOrderInfoReq.setCvv2(cv2);
        createOrderInfoReq.setExpireDate(effectiveYear+effectiveMonth);
        CreateOrderRes result=null;
        try {
            result = MtGatewaySdk.request(createOrderInfoReq, createOrderInfoReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));
        if ("MT00000".equals(result.getCode())){
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }


    @RequestMapping("updateStatus")
    public  YJResult updateStatus(String requestNo){

        if (requestNo==""||requestNo==null) {
            return YJResult.build("9765","订单号不能为空");
        }
        OrderEntity order=orderService.findByPrimaryKey(requestNo);
        BindEntity bind=new BindEntity();
        bind.setMerchantId(order.getMerchantId());
        bind.setAisleCode(YbConstant.aisleCode);
        bind.setState("0000");
        bind=bindService.findByObject(bind);
        String merchantNo=bind.getAisleMerId();
        if (merchantNo==""||merchantNo==null) {
            return YJResult.build("3985","商户编号不能为空");
        }
//        Map<String,Object> map=new HashMap<>();
//        map.put("parentMerchantNo",YbConstant.parentMerchantNo);
//        map.put("merchantNo",merchantNo);
//        map.put("requestNo",requestNo);
//
//        String s=HttpClientUtil.doPost(UrlConstant.MT_NODE +"/v1/trade/queryOrder",map);
        //JSONObject jsonObject=JSON.parseObject(s);
        //String code=jsonObject.getString("code");
        QueryOrderReq QueryOrderReq = new QueryOrderReq();
        QueryOrderReq.setMerchantNo(merchantNo);
        QueryOrderReq.setRequestNo(requestNo);
//        String img=ImgUtil.ImageToBase64ByOnline(imgUrl);
//        uploadQualificationReq.setFile(img);
//        uploadQualificationReq.setType(imgType);
//        uploadQualificationReq.setFileName(imgType+merchantId);
//        uploadQualificationReq.setMerchantNo(aisleMerId);
        QueryOrderRes result=null;
        try {
            result=MtGatewaySdk.request(QueryOrderReq, QueryOrderReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));
        if ("MT00000".equals(result.getCode())){
            String orderStatus=result.getOrderStatus();
            if ("PROCESSING".equals(orderStatus)){
                order.setState("1");
            }else if ("SUCCESS".equals(orderStatus)){
                order.setState("3");
            }else if ("FAIL".equals(orderStatus)){
                order.setState("4");
            }

            String str=orderService.update(order);
            if ("ERROR".equals(str)){
                return YJResult.build("8943","更新数据失败");
            }
        }else {
            return YJResult.build("5469","请求访问失败");
        }
        return YJResult.ok();
    }




}
