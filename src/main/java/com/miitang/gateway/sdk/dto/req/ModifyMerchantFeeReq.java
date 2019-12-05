package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.ModifyMerchantFeeRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 修改费率<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ModifyMerchantFeeReq extends BaseRequest {

    @Override
    public String requestUrl() {
        return "v1/merchant/modifyApiProductFee";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<ModifyMerchantFeeRes> responseType() {
        return ModifyMerchantFeeRes.class;
    }

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 费率类型： 固定金额，固定比率
     */
    private String feeType;

    /**
     * 固定费用
     */
    private String feeAmount;

    /**
     * 固定费率比例
     */
    private String feeRate;

    /**
     * 版本
     */
    private String version;

}
