package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.RegisterAndAccessRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 商户注册<br>
 *
 * @author: shouyu.li
 * @since: 2019/07/01 13:50
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RegisterAndAccessReq extends BaseRequest {

    @Override
    public String requestUrl() {
        return "v1/merchant/registerAndAccess";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<RegisterAndAccessRes> responseType() {
        return RegisterAndAccessRes.class;
    }

    /**
     * 商户姓名
     */
    private String merchantName;


    /**
     * 注册使用的手机号
     */
    private String phoneNumber;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 证件失效时间 非必填
     */
    private String idCardNoEnd;

    /**
     * 证件生效时间 非必填
     */
    private String idCardNoStart;


    /**
     * 结算银行卡号
     */
    private String settleBankAccountNo;

    /**
     * 结算银行开户名
     */
    private String settleBankName;


    /**
     * 结算卡开户姓名
     */
    private String accountName;

    /**
     * 结算银行卡编码
     */
    private String settleBankCode;

    /**
     * 结算银行支行名称 非必填
     */
    private String branchBankName;

    /**
     * 银行开户省编码 非必填
     */
    private String bankProvinceCode;

    /**
     * 银行开户省 非必填
     */
    private String bankProvinceName;

    /**
     * 开户市编码 非必填
     */
    private String bankCityCode;

    /**
     * 开户市 非必填
     */
    private String bankCityName;

    /**
     * 结算卡银行预留手机号
     */
    private String settleBankReservePhone;

}
