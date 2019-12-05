package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.QueryAccessInfoRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 查询商户入网信息<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class QueryAccessInfoReq extends BaseRequest {

    @Override
    public String requestUrl() {
        return "v1/merchant/queryAccessInfo";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<QueryAccessInfoRes> responseType() {
        return QueryAccessInfoRes.class;
    }

    private String merchantNo;

    private String phoneNumber;

}
