package com.yjkj.controller;

import com.alibaba.fastjson.JSON;
import com.miitang.gateway.sdk.MtGatewaySdk;
import com.miitang.gateway.sdk.dto.req.*;
import com.miitang.gateway.sdk.dto.res.*;
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

/**
 * Created by bin on 2018/8/14.
 */
@RestController
@RequestMapping("/bind/")
public class BindController {
    @Autowired
    private BindEntityService bindService;
    @Autowired
    private SnowflakeIdWorker idWorker;
    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private UpdateRateService updateRateService;


    @RequestMapping(value = "findBind")
    public YJResult findBind(String merchantId,String debitCardNo) {
        BindEntity bindDB=new BindEntity();
        bindDB.setMerchantId(merchantId);
        bindDB.setAisleCode(YbConstant.aisleCode);
        bindDB=bindService.findByObject(bindDB);
        return YJResult.ok(bindDB);
    }

    @RequestMapping(value = "findBindAisle")
    public YJResult findBindAisle(String merchantId,String debitCardNo) {
        BindEntity bindDB=new BindEntity();
        bindDB.setMerchantId(merchantId);
        bindDB.setAisleCode(YbConstant.aisleCode);
        bindDB=bindService.findByObject(bindDB);
        QueryAccessInfoReq queryAccessInfoReq = new QueryAccessInfoReq();
        // 使用手机号或者商编都可以。二者都存在时以商编为准
//        queryAccessInfoReq.setPhoneNumber("11010273021");
        queryAccessInfoReq.setMerchantNo(bindDB.getAisleMerId());
        QueryAccessInfoRes result=null;
        try {
            result=MtGatewaySdk.request(queryAccessInfoReq, queryAccessInfoReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("MT00000".equals(result.getCode())){
            if ("COMPLETED".equals(result.getAccessStatus())){
                bindDB.setState("0000");
                bindService.update(bindDB);
            }
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }
    //http://47.104.25.147:1188/bind/bind?merchantId=M47494481333951692813586&institutionId=T00000002&debitCardNo=6213300100000268761&debitCardPhone=15210339424&debitCardBankName=%E4%B8%AD%E5%9B%BD&debitCardBankCode=BOC
    @RequestMapping("bind")
    public YJResult bind(String merchantId,String institutionId,String appId,String debitCardNo,String debitCardPhone,String debitCardBankName,String debitCardBankCode) throws Exception {
        InstitutionEntity institutionEntity=institutionService.findByPrimaryKey(institutionId);
        //EposMerRate merRateEntity=merChantsService.getEposMerRate(merchantId,aisleCode,institutionEntity.getMerHost());
        MerChants mer=merChantsService.getMer(merchantId,institutionEntity.getMerHost());
        RegisterAndAccessReq registerAndAccessReq = new RegisterAndAccessReq();
        registerAndAccessReq.setMerchantName(merchantId);
        registerAndAccessReq.setPhoneNumber(debitCardPhone);
        registerAndAccessReq.setIdCardNo(mer.getCertNo());
        registerAndAccessReq.setSettleBankAccountNo(debitCardNo);
        registerAndAccessReq.setSettleBankName(debitCardBankName);
        registerAndAccessReq.setAccountName(mer.getMerName());
        registerAndAccessReq.setSettleBankCode(debitCardBankCode);
        registerAndAccessReq.setSettleBankReservePhone(debitCardPhone);
        RegisterAndAccessRes result=null;
        try {
            result=MtGatewaySdk.request(registerAndAccessReq, registerAndAccessReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
            return YJResult.ok(result);
        }
        //{"code":"MT00000","merchantNo":"11070917577830","parentMerchantNo":"1046407769"}
        System.out.println(JSON.toJSONString(result));
        if ("MT00000".equals(result.getCode())){
            BindEntity bindDB=new BindEntity();
            bindDB.setAisleMerId(result.getMerchantNo());
            bindDB.setAisleCode(YbConstant.aisleCode);
            bindDB=bindService.findByObject(bindDB);
            if (bindDB==null){
                String orderNo= OrderUtil.createOrderNo(idWorker,"EM");
                BindEntity bind=new BindEntity();
                bind.setState("0");
                bind.setOrderNo(orderNo);
                bind.setMerchantId(merchantId);
                bind.setInstitutionId(institutionId);
                bind.setAgentId(mer.getAgentId());
                bind.setAppId(appId);
                bind.setAisleCode(YbConstant.aisleCode);
                bind.setName(mer.getMerName());
                bind.setIdCard(mer.getCertNo());
                bind.setCardNumber(debitCardNo);
                bind.setPhone(debitCardPhone);
                bind.setIssuingBankCode(debitCardBankCode);
                bind.setAisleMerId(result.getMerchantNo());
                bind.setD0Fee(null);
                bind.setRate(null);
                bindService.save(bind);
            }
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }

    @RequestMapping("updateRate")
    public YJResult updateRate(String productCode,Long amount,String aisleCode,String aisleMerId,String rate,String bankCode) throws Exception{
        if ("ybcs".equals(aisleCode)&&100000L<=amount.longValue()) {
            return YJResult.build("3576", "金额超限,请选择其他产品！");
        }
        BindEntity bind=new BindEntity();
        bind.setAisleMerId(aisleMerId);
        bind.setAisleCode(YbConstant.aisleCode);
        bind=bindService.findByObject(bind);
        if (bind==null){
            return YJResult.build("3948","找不到开户记录");
        }
        ModifyMerchantFeeReq modifyMerchantFeeReq = new ModifyMerchantFeeReq();
        modifyMerchantFeeReq.setFeeRate(rate);
        modifyMerchantFeeReq.setProductCode(productCode);//"QUICK_PAY"UPOP
        modifyMerchantFeeReq.setFeeType("FIXED_RATE");
        modifyMerchantFeeReq.setMerchantNo(aisleMerId);
        if ("ybq".equals(aisleCode)){
            /*CMBCHINA
                POST
                BOCO
                属于B版本*/
            if ("CMBCHINA".equals(bankCode)||"POST".equals(bankCode)||"BOCO".equals(bankCode)){
                modifyMerchantFeeReq.setVersion("B");
            }else {
                modifyMerchantFeeReq.setVersion("A");
            }
        } else if("ybc".equals(aisleCode)){
            modifyMerchantFeeReq.setVersion("B");
        } else if("ybcs".equals(aisleCode)){
            modifyMerchantFeeReq.setVersion("A");
        }

        ModifyMerchantFeeRes result=null;
        try {
            result=MtGatewaySdk.request(modifyMerchantFeeReq, modifyMerchantFeeReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));
        if ("MT00000".equals(result.getCode())){
            //添加修改费率记录
            UpdateRateEntity update=new UpdateRateEntity();
            update.setMerchantId(bind.getMerchantId());
            update.setInstitutionId(bind.getInstitutionId());
            update.setAgentId(bind.getAgentId());
            update.setAppId(bind.getAppId());
            update.setAisleCode(bind.getAisleCode());
            update.setAisleMerId(aisleMerId);
            update.setOldRate(bind.getRate());
            update.setNewRate(new BigDecimal(rate));
            update.setState("0000");
            updateRateService.save(update);
            //修改绑卡记录费率
            bind.setRate(new BigDecimal(rate));
            bindService.update(bind);
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }
    @RequestMapping("updateD0fee")
    public YJResult updateD0fee(String aisleMerId,String d0Fee) throws Exception{
        BindEntity bind=new BindEntity();
        bind.setAisleMerId(aisleMerId);
        bind.setAisleCode(YbConstant.aisleCode);
        bind=bindService.findByObject(bind);
        ModifyMerchantFeeReq modifyMerchantFeeReq = new ModifyMerchantFeeReq();
        modifyMerchantFeeReq.setFeeAmount(Long.parseLong(d0Fee)/100+"");
        modifyMerchantFeeReq.setProductCode("QUICK_CASH");
        modifyMerchantFeeReq.setFeeType("FIXED_AMOUNT");
        modifyMerchantFeeReq.setMerchantNo(aisleMerId);
        ModifyMerchantFeeRes result=null;
        try {
            result=MtGatewaySdk.request(modifyMerchantFeeReq, modifyMerchantFeeReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));
        if ("MT00000".equals(result.getCode())){
            //添加修改费率记录
            UpdateRateEntity update=new UpdateRateEntity();
            update.setMerchantId(bind.getMerchantId());
            update.setInstitutionId(bind.getInstitutionId());
            update.setAgentId(bind.getAgentId());
            update.setAppId(bind.getAppId());
            update.setAisleCode(bind.getAisleCode());
            update.setAisleMerId(aisleMerId);
            update.setOldD0Fee(bind.getD0Fee());
            update.setNewD0Fee(Long.parseLong(d0Fee));
            update.setState("0000");
            updateRateService.save(update);
            //修改绑卡记录费率
            bind.setD0Fee(Long.parseLong(d0Fee));
            bindService.update(bind);
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }
    //FRONT_ID_CARD_PHOTO上传身份证正面资质
    //BACK_ID_CARD_PHOTO上传身份证反面资质
    //BANK_CARD_IMG上传银行卡资质
    //HAND_ID_CARD_PHOTO上传手持身份证资质
    @RequestMapping("uploadImg")
    public YJResult uploadImg(String merchantId,String institutionId,String aisleMerId,String imgUrl,String imgType) throws Exception{
        UploadQualificationReq uploadQualificationReq = new UploadQualificationReq();
        uploadQualificationReq.setCallBackUrl(YbConstant.img);
        String img=ImgUtil.ImageToBase64ByOnline(imgUrl);
        uploadQualificationReq.setFile(img);
        uploadQualificationReq.setType(imgType);
        uploadQualificationReq.setFileName(imgType+merchantId);
        uploadQualificationReq.setMerchantNo(aisleMerId);
        UploadQualificationRes result=null;
        try {
            result=MtGatewaySdk.request(uploadQualificationReq, uploadQualificationReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));
        if ("MT00000".equals(result.getCode())){
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }

    @RequestMapping(value = "findImg")
    public String findImg(String merchantId) {
        return HttpClientUtil.doGet("http://47.104.4.155:1172/Information/select?merChantId="+merchantId);
    }
    @RequestMapping("updateDebitCard")
    public YJResult updateDebitCard(String merchantId,String aisleMerId,String debitCardNo,String debitCardPhone,String debitCardBankCode){
        BindEntity bindDB=new BindEntity();
        bindDB.setMerchantId(merchantId);
        bindDB.setAisleMerId(aisleMerId);
        bindDB.setAisleCode(YbConstant.aisleCode);
        bindDB=bindService.findByObject(bindDB);
        ChangesettleReq queryOrderReq = new ChangesettleReq();
        queryOrderReq.setSettleBankAccountNo(debitCardNo);
        queryOrderReq.setMerchantNo(aisleMerId);
        ChangesettleRes result=null;
        try {
            result = MtGatewaySdk.request(queryOrderReq, queryOrderReq.responseType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("MT00000".equals(result.getCode())){
            bindDB.setCardNumber(debitCardNo);
            bindDB.setBankCardPhoto(debitCardPhone);
            bindDB.setBankCode(debitCardBankCode);
            bindService.update(bindDB);
            return YJResult.ok(result);
        }
        return YJResult.build(result.getCode(),result.getMessage());
    }



}
