package com.yjkj.entity.user;

import java.io.Serializable;

public class MerChants implements Serializable {
	//
	private Long id;

	//商户号
	private String merChantId;
	//姓名
	private String merName;
	//手机号
	private String merMp;
	//身份证
	private String certNo;
	//实名S未实名F
	private String merStat;
	//验证码
	private String identifying;
	//用户费率
	private String merChantFee;
	//提现代发费用
	private String generationFee;
	//还款代发费用
	private String generationFeeRepayment;
	//Y激活N没激活
	private String status;
	//代理商ID
	private String agentId;
	//机构号
	private String institutionId;
	//appId
	private String appId;
	private String appName;
	//推荐人
	private String recommendId;
	//用户等级
	private String merType;
	//是否冻结
	private String frozen;
	//头像
	private String faceImgUrl;
	//注册时间
	private String regDate;
	//实名时间
	private String merStatTime;
	//激活时间
	private String statusDate;
	private String remarks;
	//余额
	private String balance;
	//冻结余额
	private String balanceFrozen;
	//分润余额
	private String balanceProfit;
	//冻结分润余额
	private String balanceProfitFrozen;

	private String oneMerId;

	private String twoMerId;

	private String threeMerId;

	private String province;

	private String city;

	private String county;

	private String customerNo;

	private String ownerNo;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getOneMerId() {
		return oneMerId;
	}

	public void setOneMerId(String oneMerId) {
		this.oneMerId = oneMerId;
	}

	public String getTwoMerId() {
		return twoMerId;
	}

	public void setTwoMerId(String twoMerId) {
		this.twoMerId = twoMerId;
	}

	public String getThreeMerId() {
		return threeMerId;
	}

	public void setThreeMerId(String threeMerId) {
		this.threeMerId = threeMerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerMp() {
		return merMp;
	}

	public void setMerMp(String merMp) {
		this.merMp = merMp;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMerStat() {
		return merStat;
	}

	public void setMerStat(String merStat) {
		this.merStat = merStat;
	}

	public String getIdentifying() {
		return identifying;
	}

	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}

	public String getMerChantFee() {
		return merChantFee;
	}

	public void setMerChantFee(String merChantFee) {
		this.merChantFee = merChantFee;
	}

	public String getGenerationFee() {
		return generationFee;
	}

	public void setGenerationFee(String generationFee) {
		this.generationFee = generationFee;
	}

	public String getGenerationFeeRepayment() {
		return generationFeeRepayment;
	}

	public void setGenerationFeeRepayment(String generationFeeRepayment) {
		this.generationFeeRepayment = generationFeeRepayment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getFrozen() {
		return frozen;
	}

	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}

	public String getFaceImgUrl() {
		return faceImgUrl;
	}

	public void setFaceImgUrl(String faceImgUrl) {
		this.faceImgUrl = faceImgUrl;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getMerStatTime() {
		return merStatTime;
	}

	public void setMerStatTime(String merStatTime) {
		this.merStatTime = merStatTime;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBalanceFrozen() {
		return balanceFrozen;
	}

	public void setBalanceFrozen(String balanceFrozen) {
		this.balanceFrozen = balanceFrozen;
	}

	public String getBalanceProfit() {
		return balanceProfit;
	}

	public void setBalanceProfit(String balanceProfit) {
		this.balanceProfit = balanceProfit;
	}

	public String getBalanceProfitFrozen() {
		return balanceProfitFrozen;
	}

	public void setBalanceProfitFrozen(String balanceProfitFrozen) {
		this.balanceProfitFrozen = balanceProfitFrozen;
	}
}
