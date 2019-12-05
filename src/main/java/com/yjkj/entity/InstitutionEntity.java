package com.yjkj.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bin on 2018/3/14.
 */
@Table(name = "epos_institution")
public class InstitutionEntity implements java.io.Serializable{
    @Id
    @Column(name = "institutionId", unique = true, nullable = false)
    private String institutionId;
    @Column(name = "institutionName")
    private String institutionName;
    @Column(name = "apiHost")
    private String apiHost;
    @Column(name = "merHost")
    private String merHost;

    @Column(name = "eposRate")
    private String eposRate;
    @Column(name = "eposFee")
    private String eposFee;
    @Column(name = "callbackUrl")
    private String callbackUrl;
    @Column(name = "k")
    private String k;

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getMerHost() {
        return merHost;
    }

    public void setMerHost(String merHost) {
        this.merHost = merHost;
    }

    public String getEposRate() {
        return eposRate;
    }

    public void setEposRate(String eposRate) {
        this.eposRate = eposRate;
    }

    public String getEposFee() {
        return eposFee;
    }

    public void setEposFee(String eposFee) {
        this.eposFee = eposFee;
    }
}
