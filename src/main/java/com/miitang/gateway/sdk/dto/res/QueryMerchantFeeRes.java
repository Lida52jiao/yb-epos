package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 类描述: 查询手续费出餐<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryMerchantFeeRes extends BaseResponse {

    private List<PayCompanyFeeDTO> payCompanyFeeDTOList;

}
