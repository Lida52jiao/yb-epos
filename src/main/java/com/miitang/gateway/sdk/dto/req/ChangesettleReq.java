package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.ChangesettleRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类名称: ChangesettleReq <br>
 * 类描述: <br>
 *
 * @author: feng.li
 * @since: 2019/8/1 15:14
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ChangesettleReq extends BaseRequest {

	@Override
	public String requestUrl() {
		return "v1/merchant/changesettle";
	}

	@Override
	public boolean needEncryption() {
		return true;
	}

	@Override
	public Class<ChangesettleRes> responseType() {
		return ChangesettleRes.class;
	}
	/**
	 * 子商户号
	 */
	private String merchantNo;
	/**
	 * 银行卡号
	 */
	private String settleBankAccountNo;

	/**
	 * 银行卡预留手机号
	 */
	private String settleBankReservePhone;

}
