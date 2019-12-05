package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述:上传资质信息回参 <br>
 */
@Data@Getter
@Setter
@ToString(callSuper = true)@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadQualificationRes extends BaseResponse{

}
