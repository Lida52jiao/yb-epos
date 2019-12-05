package com.yjkj.mq.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量
 */
@Component
public class MqConstant {

    //topic
    @Value("${mqConfig.TOPIC}")
    private String TOPIC;

    //无卡
    @Value("${mqConfig.TAGS_EPOS}")
    private String TAGS_EPOS;
    //无卡
    @Value("${mqConfig.GID_EPOS}")
    private String GID_EPOS;


    public String getTOPIC() {
        return TOPIC;
    }

    public void setTOPIC(String TOPIC) {
        this.TOPIC = TOPIC;
    }

    public String getTAGS_EPOS() {
        return TAGS_EPOS;
    }

    public void setTAGS_EPOS(String TAGS_EPOS) {
        this.TAGS_EPOS = TAGS_EPOS;
    }

    public String getGID_EPOS() {
        return GID_EPOS;
    }

    public void setGID_EPOS(String GID_EPOS) {
        this.GID_EPOS = GID_EPOS;
    }
}
