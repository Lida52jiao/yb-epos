package com.yjkj.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by bin on 2018/4/18.
 */
@Table(name = "epos_update_rate")
public class UpdateRateEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "updateId", unique = true, nullable = false)
    private Long updateId;
    @Column(name = "createTime")
    private String createTime;

    @Column(name = "institutionId")
    private String institutionId;//机构号
    @Column(name = "agentId")
    private String agentId;
    @Column(name = "appId")
    private String appId;//APPID

    @Column(name = "merchantId")
    private String merchantId;//商户号
    @Column(name = "aisleCode")
    private String aisleCode;//通道编号
    @Column(name = "aisleMerId")
    private String aisleMerId;//通道商户号

    @Column(name = "oldRate")
    private BigDecimal oldRate;
    @Column(name = "newRate")
    private BigDecimal newRate;
    @Column(name = "oldD0Fee")
    private Long oldD0Fee;
    @Column(name = "newD0Fee")
    private Long newD0Fee;
    @Column(name = "state")
    private String state;

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getOldRate() {
        return oldRate;
    }

    public void setOldRate(BigDecimal oldRate) {
        this.oldRate = oldRate;
    }

    public BigDecimal getNewRate() {
        return newRate;
    }

    public void setNewRate(BigDecimal newRate) {
        this.newRate = newRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAisleCode() {
        return aisleCode;
    }

    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode;
    }

    public String getAisleMerId() {
        return aisleMerId;
    }

    public void setAisleMerId(String aisleMerId) {
        this.aisleMerId = aisleMerId;
    }

    public Long getOldD0Fee() {
        return oldD0Fee;
    }

    public void setOldD0Fee(Long oldD0Fee) {
        this.oldD0Fee = oldD0Fee;
    }

    public Long getNewD0Fee() {
        return newD0Fee;
    }

    public void setNewD0Fee(Long newD0Fee) {
        this.newD0Fee = newD0Fee;
    }
}
