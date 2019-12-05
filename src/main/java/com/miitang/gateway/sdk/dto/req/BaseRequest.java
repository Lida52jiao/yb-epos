package com.miitang.gateway.sdk.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 类描述: <br>
 */
@Getter
@Setter
public abstract class BaseRequest implements Serializable {

    /**
     * 请求的url
     * @return
     */
    public abstract String requestUrl();

    /**
     * 是否需要加密
     * @return
     */
    public abstract boolean needEncryption();

    /**
     * 获取返回值类型
     * @return
     */
    public abstract Object responseType();
}
