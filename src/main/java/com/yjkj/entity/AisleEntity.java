package com.yjkj.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by bin on 2018/4/8.
 */
@Table(name = "epos_aisle")
public class AisleEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aisleId", unique = true, nullable = false)
    private Long aisleId;
    @Column(name = "createTime")
    private String createTime;

    @Column(name = "aisleType")
    private String aisleType;
    @Column(name = "isUpdateRate")
    private String isUpdateRate;

    @Column(name = "payTime")
    private String payTime;

    @Column(name = "aisleCode")
    private String aisleCode;
    @Column(name = "aisleName")
    private String aisleName;//
    @Column(name = "icon")
    private String icon;
    @Column(name = "orderBy")
    private String orderBy;
    @Column(name = "open")
    private String open;

    @Column(name = "institutionId")
    private String institutionId;//机构号
    @Column(name = "appId")
    private String appId;//APPID

    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "d0Fee")
    private Long d0Fee;
    @Column(name = "maxAmount")
    private Long maxAmount;
    @Column(name = "minAmount")
    private Long minAmount;
    @Column(name = "remarks")
    private String remarks;

    public String getIsUpdateRate() {
        return isUpdateRate;
    }

    public void setIsUpdateRate(String isUpdateRate) {
        this.isUpdateRate = isUpdateRate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getAisleId() {
        return aisleId;
    }

    public void setAisleId(Long aisleId) {
        this.aisleId = aisleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAisleCode() {
        return aisleCode;
    }

    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode;
    }

    public String getAisleName() {
        return aisleName;
    }

    public void setAisleName(String aisleName) {
        this.aisleName = aisleName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getD0Fee() {
        return d0Fee;
    }

    public void setD0Fee(Long d0Fee) {
        this.d0Fee = d0Fee;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public String getAisleType() {
        return aisleType;
    }

    public void setAisleType(String aisleType) {
        this.aisleType = aisleType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
