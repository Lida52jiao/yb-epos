package com.yjkj.util;

import java.io.Serializable;

/**
 * 自定义响应信息
 */
public class YJResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7109353265833616747L;

	// 响应业务状态
	private String code;
	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static YJResult build(String code, String msg, Object data) {
		return new YJResult(code, msg, data);
	}

	public static YJResult build(String code, String msg) {
		return new YJResult(code, msg);
	}
	public static YJResult ok(Object data) {
		return new YJResult(data);
	}

	public static YJResult ok() {
		return new YJResult(null);
	}

	public YJResult() {
		super();
	}

	public YJResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	

	public YJResult(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public YJResult(Object data) {
		this.code = "0000";
		this.msg = "SUCCESS";
		this.data = data;
	}

	@Override
	public String toString() {
		return "YJResult{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
