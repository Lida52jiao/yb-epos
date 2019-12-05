package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 类描述: 支付产品手续费<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayCompanyFeeDTO implements Serializable {

    private String productCode;

    private String feeType;

    private String feeAmount;

    private String feeRate;

    private String version;

}