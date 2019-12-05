package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 修改商户手续费返回参数<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyMerchantFeeRes extends BaseResponse {
}
