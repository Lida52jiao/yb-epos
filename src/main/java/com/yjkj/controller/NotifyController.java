package com.yjkj.controller;

import com.alibaba.fastjson.JSON;
import com.miitang.gateway.sdk.MtGatewaySdk;
import com.miitang.gateway.sdk.dto.res.MerchantAccessCallBack;
import com.miitang.gateway.sdk.dto.res.PayResultCallBack;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import com.yjkj.constant.YbConstant;
import com.yjkj.entity.BindEntity;
import com.yjkj.entity.InstitutionEntity;
import com.yjkj.entity.OrderEntity;
import com.yjkj.entity.user.EposMerRate;
import com.yjkj.entity.user.MerChants;
import com.yjkj.mq.SendShare;
import com.yjkj.mq.param.EposNotify;
import com.yjkj.service.epos.BindEntityService;
import com.yjkj.service.epos.InstitutionService;
import com.yjkj.service.epos.OrderEntityService;
import com.yjkj.service.user.MerChantsService;
import com.yjkj.util.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by bin on 2019/1/17.
 */
@RestController
@RequestMapping("/notify/")
public class NotifyController {
    @Autowired
    private BindEntityService bindService;
    @Autowired
    private OrderEntityService orderService;
    @Autowired
    private SendShare sendShare;
    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private InstitutionService institutionService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotifyController.class);
    /*@RequestMapping("notify")
    public String notify(@RequestBody String json) throws Exception {
        System.out.println(json);
        return json;
    }*/


    @RequestMapping("/img")
    public String img(@RequestBody String uriStr)throws Exception {
        //signData=169195607B485FE37A28AEFC127FAEF9&data=qlJRJCFNRrwTTieCtPQuqQAhSRjj7xgo6tsNw5OqGZkDkRaGF1ABEfWVJgu6iKrb3nqnNsIP4UKP%250D%250A3Vc%252BBu77Fl1GFHphikVUrc39En7A3IKZDIKZ8jWMQQhQ0x%252BHc52f%250D%250A&parentMerchantNo=1046407769
        logger.info(uriStr);
        String json=JSON.toJSONString(MapUtil.uriStringToMap(uriStr));
        MerchantAccessCallBack response = MtGatewaySdk.response(URLDecoder.decode(json,"UTF-8"), MerchantAccessCallBack.class);
        logger.info(JSON.toJSONString(response));
        BindEntity bind=new BindEntity();
        bind.setAisleMerId(response.getMerchantNo());
        bind.setAisleCode(YbConstant.aisleCode);
        bind=bindService.findByObject(bind);
        bind.setState("0000");
        bindService.update(bind);
        return "success";
    }
    @RequestMapping("/pay")
    public String pay(@RequestBody String uriStr)throws Exception {
        logger.info(uriStr);
        String json=JSON.toJSONString(MapUtil.uriStringToMap(uriStr));
        PayResultCallBack response = MtGatewaySdk.response(URLDecoder.decode(json,"UTF-8"), PayResultCallBack.class);
        logger.info(JSON.toJSONString(response));
        OrderEntity order=orderService.findByPrimaryKey(response.getRequestNo());
        if ("MT00000".equals(response.getCode())){//response.getOrderStatus()   cashStatus
            if("SUCCESS".equals(response.getOrderStatus())){
                if ("T00000014".equals(order.getInstitutionId())){
                    order.setState("3");
                    order.setFinishTime(new Date().getTime());
                    order.setCause(response.getMessage());
                    orderService.update(order);
                    InstitutionEntity in=institutionService.findByPrimaryKey("T00000014");
                    MerChants mer=merChantsService.getMer(order.getMerchantId(),in.getMerHost());
                    EposMerRate rate=merChantsService.getEposMerRate(order.getMerchantId(),order.getAisleCode(),in.getMerHost());

                    logger.info(JSON.toJSONString(mer));
                    HashMap<String,Object> outMap=new HashMap<>();
                    outMap.put("merchantId", order.getMerchantId());
                    outMap.put("customerNo", mer.getCustomerNo()==null?"":mer.getCustomerNo());//TODO
                    outMap.put("currencyType", "CNY");
                    outMap.put("issuer",order.getCreditCardCode()==null?"未知":order.getCreditCardCode());
                    outMap.put("pan",order.getCreditCardNumber()==null?"未知":order.getCreditCardNumber());
                    outMap.put("orderNo",order.getOrderNo());
                    String amount= BigDecilmalUtil.div(new Double(order.getAmount()+""),new Double("100"),2)+"";
                    String customerFee=BigDecilmalUtil.div(new Double(order.getFee()+""),new Double("100"),2)+"";
                    outMap.put("amount",amount);
                    outMap.put("customerFee",customerFee);
                    outMap.put("completeTime", DateUtil.longToString(new Date().getTime(),"yyyy-MM-dd HH:mm:ss"));
                    outMap.put("customerName",order.getName());
                    outMap.put("status","SUCCESS");//TODO
                    outMap.put("ownerNo",mer.getOwnerNo()==null?"":mer.getOwnerNo());//TODO
                    outMap.put("cardType","信用卡");
                    String createTime=order.getCreateTime();
                    outMap.put("createTime",createTime.substring(0,createTime.length()-2));
                    outMap.put("custFee",rate.getRate());
                    outMap.put("mdFee","1");
                    outMap.put("settleType","T0");
                    outMap.put("type",mer.getType()==null?"":mer.getType());
                    outMap.put("aisleCode",order.getAisleCode());
                    String outSign=SignUtil.createYJSign(outMap,in.getK());
                    outMap.put("sign",outSign);
                    /*{
                        "customerNo": "862732984",    //商户编号
                            "currencyType": "CNY",  // 货币类型
                            "issuer": "ICBC",  //发卡行
                            "pan": "",  //银行卡号
                            "orderNo": "BNQQFTTSG1D37T3P1JS0", //订单号
                            "amount": "157.0", //交易金额
                            "customerFee": "1.07", //商户手续费
                            "completeTime": "2019-10-24  19:16:00",  // 交易完成时间
                            "customerName": "张**", //商户注册的姓名
                            "status": "SUCCESS", //订单状态
                            "ownerNo": "862085", // 归属人编号
                            "cardType": "", //卡类型  借记卡、贷记卡
                            "createTime": "2019-10-24 19:16:00", //订单创建时间
                            "custFee": , //费率
                        "mdFee": ,//+1手续费
                        "settleType": "T0" //T0，T1
                    }*/
                    logger.info(JSON.toJSONString(outMap));
                    String outResult= HttpsClientUtil.doPostSSL(in.getCallbackUrl(),outMap);
                    logger.info(outResult);
                    return outResult;
                }
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("orderNo",order.getOrderNo());
                hashMap.put("amount",order.getAmount());
                hashMap.put("state","success");
                hashMap.put("merchantId",order.getMerchantId());
                hashMap.put("institutionId",order.getInstitutionId());
                hashMap.put("aisleCode",order.getAisleCode());
                String accountSign= SignUtil.createYJSign(hashMap);
                hashMap.put("sign",accountSign);

                EposNotify notify=null;
                try {
                    notify=(EposNotify) MapUtil.mapToObject(hashMap,EposNotify.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(accountSign);
                if (notify==null){
                    return "ERROR";
                }
                boolean mqRsult=sendShare.send2(notify);

                //String accResult=HttpClientUtil.doPost(UrlConstant.EPOS_CALLBACK,hashMap);
                order.setState("3");
                order.setFinishTime(new Date().getTime());
                order.setCause(response.getMessage());
                order.setIsShare(mqRsult+"");
                orderService.update(order);
                return mqRsult?"success":mqRsult+"";
            }else {
                order.setState("4");
                order.setFinishTime(new Date().getTime());
                order.setCause(response.getMessage());
                orderService.update(order);
                return "error";
            }
        }
        return "error";
    }

    @RequestMapping("/yhb")
    public String yhb(String orderNo)throws Exception {
        OrderEntity order=orderService.findByPrimaryKey(orderNo);
        if (!"3".equals(order.getState())){
            return "4";
        }
        InstitutionEntity in=institutionService.findByPrimaryKey("T00000014");
        MerChants mer=merChantsService.getMer(order.getMerchantId(),in.getMerHost());
        EposMerRate rate=merChantsService.getEposMerRate(order.getMerchantId(),order.getAisleCode(),in.getMerHost());

        logger.info(JSON.toJSONString(mer));
        HashMap<String,Object> outMap=new HashMap<>();
        outMap.put("merchantId", order.getMerchantId());
        outMap.put("customerNo", mer.getCustomerNo()==null?"":mer.getCustomerNo());//TODO
        outMap.put("currencyType", "CNY");
        outMap.put("issuer",order.getCreditCardCode()==null?"未知":order.getCreditCardCode());
        outMap.put("pan",order.getCreditCardNumber()==null?"未知":order.getCreditCardNumber());
        outMap.put("orderNo",order.getOrderNo());
        String amount= BigDecilmalUtil.div(new Double(order.getAmount()+""),new Double("100"),2)+"";
        String customerFee=BigDecilmalUtil.div(new Double(order.getFee()+""),new Double("100"),2)+"";
        outMap.put("amount",amount);
        outMap.put("customerFee",customerFee);
        outMap.put("completeTime", DateUtil.longToString(new Date().getTime(),"yyyy-MM-dd HH:mm:ss"));
        outMap.put("customerName",order.getName());
        outMap.put("status","SUCCESS");//TODO
        outMap.put("ownerNo",mer.getOwnerNo()==null?"":mer.getOwnerNo());//TODO
        outMap.put("cardType","信用卡");
        String createTime=order.getCreateTime();
        outMap.put("createTime",createTime.substring(0,createTime.length()-2));
        outMap.put("custFee",rate.getRate());
        outMap.put("mdFee","1");
        outMap.put("settleType","T0");
        outMap.put("type",mer.getType()==null?"":mer.getType());
        outMap.put("aisleCode",order.getAisleCode());
        String outSign=SignUtil.createYJSign(outMap,in.getK());
        outMap.put("sign",outSign);
                    /*{
                        "customerNo": "862732984",    //商户编号
                            "currencyType": "CNY",  // 货币类型
                            "issuer": "ICBC",  //发卡行
                            "pan": "",  //银行卡号
                            "orderNo": "BNQQFTTSG1D37T3P1JS0", //订单号
                            "amount": "157.0", //交易金额
                            "customerFee": "1.07", //商户手续费
                            "completeTime": "2019-10-24  19:16:00",  // 交易完成时间
                            "customerName": "张**", //商户注册的姓名
                            "status": "SUCCESS", //订单状态
                            "ownerNo": "862085", // 归属人编号
                            "cardType": "", //卡类型  借记卡、贷记卡
                            "createTime": "2019-10-24 19:16:00", //订单创建时间
                            "custFee": , //费率
                        "mdFee": ,//+1手续费
                        "settleType": "T0" //T0，T1
                    }*/
        logger.info(JSON.toJSONString(outMap));
        String outResult= HttpsClientUtil.doPostSSL(in.getCallbackUrl(),outMap);
        logger.info(outResult);
        return outResult;
    }

    public static void main(String[] args) {
        String s="http://hnocar.1818pay.cn/location.html?merchantId=M57489773241541427214611&institutionId=T00000006&appId=0000&token=0fd304b8e23139d3e2e36f47117fc7af";
        System.out.println(s.length());
    }
}
