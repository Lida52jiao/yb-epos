package com.yjkj.mapper;

import com.yjkj.entity.OrderEntity;
import com.yjkj.entity.param.PlanAmountCount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderEntityMapper extends Mapper<OrderEntity> {
    List<OrderEntity> findOrderByTime(@Param("orderNo") String orderNo,
                                      @Param("phone") String phone,
                                      @Param("name") String name,
                                      @Param("state") String state,
                                      @Param("merchantId") String merchantId,
                                      @Param("agentId") String agentId,
                                      @Param("institutionId") String institutionId,
                                      @Param("appId") String appId,
                                      @Param("aisleCode") String aisleCode,
                                      @Param("startTime") String startTime,
                                      @Param("finishTime") String finishTime);
    PlanAmountCount amountCount(@Param("agentIds") String agentIds,
                                @Param("aisleCode") String aisleCode,
                                @Param("startTime") String startTime,
                                @Param("finishTime") String finishTime);
    PlanAmountCount amountCount2(@Param("institutionId") String institutionId,
                                 @Param("aisleCode") String aisleCode,
                                 @Param("startTime") String startTime,
                                 @Param("finishTime") String finishTime);

    OrderEntity findOrderByOrderNo(@Param("requestNo") String orderNo);
}
