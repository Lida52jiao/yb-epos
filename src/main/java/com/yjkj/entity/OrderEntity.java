package com.yjkj.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "epos_order")
public class OrderEntity implements java.io.Serializable{

	private static final long serialVersionUID = -461170910261203431L;
	@Id
    @Column(name = "orderNo", unique = true, nullable = false)
    private String orderNo;//订单号
	@Column(name = "withdrawOrderNo")
	private String withdrawOrderNo;

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
    @Column(name = "name")
    private String name;//姓名
    @Column(name = "phone")
    private String phone;//手机号
    @Column(name = "cardNumber")
    private String cardNumber;//结算卡卡号
    @Column(name = "issuingBankCode")
    private String issuingBankCode;//结算卡发卡行编码
    @Column(name = "creditCardNumber")
    private String creditCardNumber;//信用卡卡号
	@Column(name = "creditPhone")
	private String creditPhone;//信用卡卡号
    @Column(name = "creditCardCode")
    private String creditCardCode;//信用卡发卡行编码
    @Column(name = "cv2")
    private String cv2;//信用卡cvv
    @Column(name = "effectiveYear")
    private String effectiveYear;//信用卡的有效期年
    @Column(name = "effectiveMonth")
    private String effectiveMonth;//信用卡的有效期月
    @Column(name = "amount")
    private Long amount;//交易金额
    @Column(name = "fee")
    private Long fee;//交易手续费
	@Column(name = "d0Fee")
	private Long d0Fee;
    @Column(name = "arrivalAmount")
    private Long arrivalAmount;//到账金额
    @Column(name = "aisleOrderNo")
	private String aisleOrderNo;//通道订单号
    @Column(name = "finishTime")
   	private Long finishTime;//结束的时间
    @Column(name = "state")
   	private String state;//订单的状态
	@Column(name = "cause")
	private String cause;
	@Column(name = "isShare")
    private String isShare;

	@Column(name = "agencyId")
	private Long agencyId;
	@Column(name = "commodityName")
	private String commodityName;
	@Column(name = "description")
	private String description;
	@Column(name = "img")
	private String img;
	@Column(name = "address")
	private String address;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getWithdrawOrderNo() {
		return withdrawOrderNo;
	}

	public void setWithdrawOrderNo(String withdrawOrderNo) {
		this.withdrawOrderNo = withdrawOrderNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public Long getD0Fee() {
		return d0Fee;
	}

	public void setD0Fee(Long d0Fee) {
		this.d0Fee = d0Fee;
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getIssuingBankCode() {
		return issuingBankCode;
	}
	public void setIssuingBankCode(String issuingBankCode) {
		this.issuingBankCode = issuingBankCode;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCreditCardCode() {
		return creditCardCode;
	}
	public void setCreditCardCode(String creditCardCode) {
		this.creditCardCode = creditCardCode;

	}

	public String getCreditPhone() {
		return creditPhone;
	}

	public void setCreditPhone(String creditPhone) {
		this.creditPhone = creditPhone;
	}

	public String getCv2() {
		return cv2;
	}

	public void setCv2(String cv2) {
		this.cv2 = cv2;
	}

	public String getEffectiveYear() {
		return effectiveYear;
	}
	public void setEffectiveYear(String effectiveYear) {
		this.effectiveYear = effectiveYear;
	}
	public String getEffectiveMonth() {
		return effectiveMonth;
	}
	public void setEffectiveMonth(String effectiveMonth) {
		this.effectiveMonth = effectiveMonth;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getArrivalAmount() {
		return arrivalAmount;
	}
	public void setArrivalAmount(Long arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getAisleOrderNo() {
		return aisleOrderNo;
	}

	public void setAisleOrderNo(String aisleOrderNo) {

		this.aisleOrderNo = aisleOrderNo;
	}

	public Long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
}
