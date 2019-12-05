package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 商户注册<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterAndAccessRes extends BaseResponse {

    /**
     * 运营商编号
     */
    private String parentMerchantNo;

    /**
     * 商户编号
     */
    private String merchantNo;

}
