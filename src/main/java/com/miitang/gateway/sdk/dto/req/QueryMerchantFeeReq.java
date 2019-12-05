package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.QueryMerchantFeeRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 查询商户费率<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class QueryMerchantFeeReq extends BaseRequest{

    @Override
    public String requestUrl() {
        return "v1/merchant/queryProductFee";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<QueryMerchantFeeRes> responseType() {
        return QueryMerchantFeeRes.class;
    }

    private String merchantNo;

}
