package com.yjkj.controller;

import com.yjkj.entity.AisleEntity;
import com.yjkj.service.epos.AisleService;
import com.yjkj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bin on 2018/8/14.
 */
@RestController
@RequestMapping("/aisle/")
public class AisleController {
    @Autowired
    private AisleService aisleService;

    @RequestMapping(value = "find")
    public YJResult find(AisleEntity aisle) {
        if ("".equals(aisle.getInstitutionId())){
            aisle.setInstitutionId(null);
        }
        if ("".equals(aisle.getAisleCode())){
            aisle.setAisleCode(null);
        }
        if ("".equals(aisle.getAppId())){
            aisle.setAppId(null);
        }
        return YJResult.ok(aisleService.queryPageForList(aisle));
    }

    @RequestMapping("saveOrUpdate")
    public YJResult saveOrUpdate(AisleEntity aisle){
        return YJResult.ok(aisleService.saveOrUpdate(aisle));
    }



}
