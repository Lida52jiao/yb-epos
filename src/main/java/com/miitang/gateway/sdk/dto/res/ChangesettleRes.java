package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类名称: ChangesettleRes <br>
 * 类描述: <br>
 *
 * @author: feng.li
 * @since: 2019/8/1 15:15
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangesettleRes extends BaseResponse {
	/**
	 * 银行卡号
	 */
	private String settleBankAccountNo;
	/**
	 * 银行名称
	 */
	private String settleBankName;
	/**
	 * 银行编码
	 */
	private String bankCode;
}
