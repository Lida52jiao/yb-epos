package com.yjkj.controller;

import com.yjkj.constant.YbConstant;
import com.yjkj.entity.InstitutionEntity;
import com.yjkj.entity.user.EposMerRate;
import com.yjkj.service.epos.InstitutionService;
import com.yjkj.service.user.MerChantsService;
import com.yjkj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bin on 2018/4/8.
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private InstitutionService institutionService;
    @RequestMapping("/getCardList")
    public YJResult getCardList(String cardType, String merchantId, String institutionId, String appId, String token){
        InstitutionEntity institutionEntity=institutionService.findByPrimaryKey(institutionId);
        return YJResult.ok(merChantsService.getCardList(merchantId,cardType,token,institutionEntity.getMerHost()));
    }
    @RequestMapping("/findRate")
    public YJResult findRate(String aisleCode,String merchantId, String institutionId){
        InstitutionEntity institutionEntity=institutionService.findByPrimaryKey(institutionId);
        EposMerRate merRateEntity=merChantsService.getEposMerRate(merchantId,aisleCode,institutionEntity.getMerHost());
        return YJResult.ok(merRateEntity);
    }

}
