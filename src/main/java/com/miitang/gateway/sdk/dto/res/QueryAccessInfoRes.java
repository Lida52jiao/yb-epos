package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 类描述: 查询入网信息响应<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccessInfoRes extends BaseResponse{

    private String parentMerchantNo;

    private String merchantNo;

    private String merchantName;

    private String phoneNumber;

    private String idCardNo;

    private String idCardNoEnd;

    private String idCardNoStart;

    private String settleBankAccountNo;

    private String settleBankName;

    private String accountName;

    private String settleBankCode;

    private String branchBankName;

    private String bankProvinceCode;

    private String bankProvinceName;

    private String bankCityCode;

    private String bankCityName;

    private String settleBankReservePhone;

    private String accessStatus;
}
