package com.yjkj.service.epos;

import com.yjkj.entity.OrderEntity;
import com.yjkj.entity.param.PlanAmountCount;
import com.yjkj.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderEntityService extends BaseService<OrderEntity> {

    PageInfo<OrderEntity> findOrderByTime(String orderNo,
                                          String phone,
                                          String name,
                                          String state,
                                          String merchantId,
                                          String agentId,
                                          String institutionId,
                                          String appId,
                                          String aisleCode,
                                          Long startTime,
                                          Long finishTime);

    PlanAmountCount amountCount(List<String> agentList, String institutionId, String aisleCode, Long startTime,
                                Long finishTime);

}
