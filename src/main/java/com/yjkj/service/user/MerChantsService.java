package com.yjkj.service.user;


import com.yjkj.entity.user.CardInformation;
import com.yjkj.entity.user.EposMerRate;
import com.yjkj.entity.user.MerChants;

import java.util.List;

/**
 * Created by bin on 2017/11/7.
 */
public interface MerChantsService {

    MerChants getMer(String merId, String merHost);

    List<CardInformation> getCardList(String merId, String cardType, String token, String merHost);

    EposMerRate getEposMerRate(String merId, String aisleCode, String merHost);

    List<EposMerRate> getEposMerRateList(String merId, String aisleCodes, String merHost);
}
