package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 支付返回<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayRes extends BaseResponse{

    private String parentMerchantNo;

    private String merchantNo;

    private String merchantName;

    private String uniqueOrderNo;
}
