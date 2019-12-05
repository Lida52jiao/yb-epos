package com.yjkj.mq;


import com.yjkj.mq.param.EposNotify;

/**
 * Created by bin on 2018/7/30.
 */
public interface SendShare {
    boolean send2(EposNotify notify);
}
