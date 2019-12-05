package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 类描述: 响应基类<br>
 *
 * @author: shouyu.li
 * @since: 2019/07/01 13:47
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements Serializable {

    /**
     *返回码
     */
    protected String code;
    /**
     *返回信息
     */
    protected String message;

}
