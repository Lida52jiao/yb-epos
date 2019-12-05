package com.yjkj.service.epos.impl;

import com.yjkj.entity.OrderEntity;
import com.yjkj.entity.param.PlanAmountCount;
import com.yjkj.mapper.OrderEntityMapper;
import com.yjkj.service.BaseServiceImpl;
import com.yjkj.service.epos.OrderEntityService;
import com.yjkj.util.MyStringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderEntityServiceImpl extends BaseServiceImpl<OrderEntity> implements OrderEntityService {
    @Autowired
    private OrderEntityMapper orderEntityMapper;
    @Override
    public PageInfo<OrderEntity> findOrderByTime(String orderNo,
                                                 String phone,
                                                 String name,
                                                 String state,
                                                 String merchantId,
                                                 String agentId,
                                                 String institutionId,
                                                 String appId,
                                                 String aisleCode,
                                                 Long startTime,
                                                 Long finishTime){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getParameter("pageNum")+" "+request.getParameter("pageSize")+" "+request.getParameter("sort")+" "+request.getParameter("order"));
        Integer pageNum = MyStringUtil.valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = MyStringUtil.valueOf(request.getParameter("pageSize"), 10);
        PageHelper.startPage(pageNum,pageSize);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        if (MyStringUtil.isNotEmpty(orderField)) {
            PageHelper.orderBy(orderField);
            if (MyStringUtil.isNotEmpty(orderDirection)) {
                PageHelper.orderBy(orderField + " " + orderDirection);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String startTimeStr = startTime==null?"":sdf.format(new Date(startTime));
        String finishTimeStr = finishTime==null?"":sdf.format(new Date(finishTime));
        return new PageInfo<OrderEntity>(orderEntityMapper.findOrderByTime(orderNo,phone,name,state,merchantId,agentId,institutionId,appId,aisleCode,startTimeStr,finishTimeStr));
    }
    @Override
    public PlanAmountCount amountCount(List<String> agentList, String institutionId, String aisleCode, Long startTime,
                                       Long finishTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String startTimeStr = startTime==null?"":sdf.format(new Date(startTime));
        String finishTimeStr = finishTime==null?"":sdf.format(new Date(finishTime));
        if (agentList==null||agentList.size()==0){
            return orderEntityMapper.amountCount2(institutionId,aisleCode,startTimeStr,finishTimeStr);
        }
        String agents = "'"+ StringUtils.join(agentList,"','")+"'";
        return orderEntityMapper.amountCount(agents,aisleCode,startTimeStr,finishTimeStr);
    }


}
