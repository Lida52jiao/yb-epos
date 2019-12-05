package com.yjkj.service.user.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjkj.constant.UrlConstant;
import com.yjkj.entity.user.CardInformation;
import com.yjkj.entity.user.EposMerRate;
import com.yjkj.entity.user.MerChants;
import com.yjkj.service.user.MerChantsService;
import com.yjkj.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by bin on 2017/11/7.
 */
@Service
public class MerChantsServiceImpl implements MerChantsService {
    @Override
    public MerChants getMer(String merId, String merHost) {
        String url= merHost+UrlConstant.GET_MER+merId;
        System.out.println(url);
        String result=new HttpClientUtil().doGet(url);
        MerChants mer= JSON.parseObject(result,MerChants.class);
        System.out.println(result);
        return mer;
    }
    @Override
    public List<CardInformation> getCardList(String merId, String cardType, String token, String merHost){
        LinkedHashMap<String, Object> hashMap=new LinkedHashMap<String, Object>();
        hashMap.put("merChantId",merId);
        hashMap.put("cardType",cardType);
        hashMap.put("token",token);
        String result=new HttpClientUtil().doPost(merHost+ UrlConstant.GET_CARD_LIST,hashMap);
        System.out.println(result);
        JSONObject job = JSONObject.parseObject(result);
        List<CardInformation> cardList=JSON.parseArray(job.getString("data"),CardInformation.class);
        return cardList;
    }
    @Override
    public EposMerRate getEposMerRate(String merId, String aisleCode, String merHost){
        LinkedHashMap<String, Object> hashMap=new LinkedHashMap<String, Object>();
        hashMap.put("merChantId",merId);
        hashMap.put("aisleCode",aisleCode);
        String result=new HttpClientUtil().doPost(merHost+ UrlConstant.USER_EPOS_RATE,hashMap);
        System.out.println(result);
        EposMerRate merRate= JSON.parseObject(result,EposMerRate.class);
        return merRate;
    }
    @Override
    public List<EposMerRate> getEposMerRateList(String merId, String aisleCodes, String merHost){
        LinkedHashMap<String, Object> hashMap=new LinkedHashMap<String, Object>();
        hashMap.put("merChantId",merId);
        hashMap.put("aisleCodes",aisleCodes);
        String result=new HttpClientUtil().doPost(merHost+ UrlConstant.USER_EPOS_RATE_LIST,hashMap);
        System.out.println(result);
        List<EposMerRate> merRate= JSON.parseArray(result,EposMerRate.class);
        return merRate;
    }

    public static void main(String[] args) {
        //http://47.104.133.253/yj-mer/MerChantsRate/selectMerChantsRate.shtml?merChantId=M63845759204943872010004&aisleCode=ybq
        LinkedHashMap<String, Object> hashMap=new LinkedHashMap<String, Object>();
        hashMap.put("merChantId","M63845759204943872010004");
        hashMap.put("aisleCode","ybq");
        String result=new HttpClientUtil().doPost("http://47.104.133.253/yj-mer/"+ UrlConstant.USER_EPOS_RATE,hashMap);
        System.out.println(result);

    }
}
