package com.yjkj.constant;

/**
 * Created by bin on 2018/4/8.
 */
public class UrlConstant {

    public static final String ACCOUNT_HOST="http://47.104.22.226/yj-account/";
    public static final String EPOS_CALLBACK=ACCOUNT_HOST+"callback/eposCallback";

    public static final String AISLE_ZUUL_HOST="http://47.104.25.147:1002/";
    public static final String AISLE_BIND="/bind/bind";
    public static final String AISLE_PAY="/order/pay";
    public static final String AISLE_UPDATE_RATE="/bind/updateRate";

    public static final String AISLE_SMS="/bind/send";
    public static final String AISLE_SIGN="/bind/agreement";

    public static final String USER_EPOS_RATE="MerChantsRate/selectMerChantsRate.shtml";
    public static final String USER_EPOS_RATE_LIST="MerChantsRate/selectRateList.shtml";
    //用户信息
    public final static String GET_MER="MerChants/getMerChantsById?merChantId=";
    //卡
    public final static String GET_CARD_LIST="CardInformation/getCard.shtml";

    //蜜堂网关地址
    public final static String MT_NODE="https://open.miitang.com";

}
