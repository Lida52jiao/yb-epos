package com.yjkj.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by bin on 2018/4/8.
 */
@Table(name = "epos_bind")
public class BindEntity implements java.io.Serializable{
   
	private static final long serialVersionUID = 6341999501120646308L;
	@Id
    @Column(name = "orderNo", unique = true, nullable = false)
    private String orderNo;//单号
    @Column(name = "createTime")
    private String createTime;
    @Column(name ="state")
	private String state;

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
    @Column(name = "name")
    private String name;//姓名
    @Column(name = "phone")
    private String phone;//手机号
    @Column(name = "idCard")
    private String idCard;//身份证号
    @Column(name = "cardNumber")
    private String cardNumber;//结算卡卡号
    @Column(name = "issuingBank")
    private String issuingBank;//结算卡发卡行
    @Column(name = "issuingBankCode")
    private String issuingBankCode;//结算卡发卡行编码
    @Column(name = "provinceCode")
    private String provinceCode;//省份编码
    @Column(name = "cityCode")
    private String cityCode;//城市编码
    @Column(name = "address")
    private String address;//地址
    @Column(name = "rate")
    private BigDecimal rate;//费率
    @Column(name = "d0Fee")
    private Long d0Fee;//提现费
    @Column(name = "bankCode")
    private String bankCode;//联行号
    @Column(name = "bankCardPhoto")
    private String bankCardPhoto;//银行卡正面
    @Column(name = "idCardPhoto")
    private String idCardPhoto;//身份证正面
    @Column(name = "idCardBackPhoto")
    private String idCardBackPhoto;//身份证背面
    @Column(name = "personPhoto")
    private String personPhoto;//身份证+银行卡+本人合照

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	public String getIssuingBankCode() {
		return issuingBankCode;
	}
	public void setIssuingBankCode(String issuingBankCode) {
		this.issuingBankCode = issuingBankCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankCardPhoto() {
		return bankCardPhoto;
	}
	public void setBankCardPhoto(String bankCardPhoto) {
		this.bankCardPhoto = bankCardPhoto;
	}
	public String getIdCardPhoto() {
		return idCardPhoto;
	}
	public void setIdCardPhoto(String idCardPhoto) {
		this.idCardPhoto = idCardPhoto;
	}
	public String getIdCardBackPhoto() {
		return idCardBackPhoto;
	}
	public void setIdCardBackPhoto(String idCardBackPhoto) {
		this.idCardBackPhoto = idCardBackPhoto;
	}
	public String getPersonPhoto() {
		return personPhoto;
	}
	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}
    
}
