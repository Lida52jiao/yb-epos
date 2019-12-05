package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.QueryOrderRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 查询订单接口<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class QueryOrderReq extends BaseRequest {

    @Override
    public String requestUrl() {
        return "v1/trade/queryOrder";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<QueryOrderRes> responseType() {
        return QueryOrderRes.class;
    }

    /**
     * 子商户号
     */
    private String merchantNo;

    /**
     * 请求号
     */
    private String requestNo;
    /**
     * 订单号
     */
    private String uniqueOrderNo;
}
