package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.UploadQualificationRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 上传资质文件<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UploadQualificationReq extends BaseRequest{

    @Override
    public String requestUrl() {
        return "v1/file/merchant/uploadQualification";
    }

    @Override
    public boolean needEncryption() {
        return false;
    }

    @Override
    public Class<UploadQualificationRes> responseType() {
        return UploadQualificationRes.class;
    }

    /**
     * 商编
     */
    private String merchantNo;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 资质文件的类型
     */
    private String type;

    /**
     * base64文件，大小不超过2M
     */
    private String file;

    /**
     * 入网结果回调地址
     */
    private String callBackUrl;

}
