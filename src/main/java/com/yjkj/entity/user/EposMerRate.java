package com.yjkj.entity.user;

import java.io.Serializable;

public class EposMerRate implements Serializable {

	private Long id;
	private String merType;
	private String rate;
	private Long d0Fee;
    private String aisleCode;
	private String appId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMerType() {
		return merType;
	}
	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Long getD0Fee() {
		return d0Fee;
	}
	public void setD0Fee(Long d0Fee) {
		this.d0Fee = d0Fee;
	}
	public String getAisleCode() {
		return aisleCode;
	}
	public void setAisleCode(String aisleCode) {
		this.aisleCode = aisleCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}
