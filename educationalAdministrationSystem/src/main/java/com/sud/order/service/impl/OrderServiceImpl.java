package com.sud.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sud.common.WebContent;
import com.sud.order.dto.OrderReq;
import com.sud.order.model.Order;
import com.sud.order.repository.OrderRepository;
import com.sud.order.service.OrderService;
import com.sud.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository orderRepository;

    @Override
    public Page<Order> findByHandleLoginName(String loginName, Pageable pageable) {
        Page<Order> page = orderRepository.findByHandleLoginName(loginName, pageable);
        productHtml(page.getContent());
        return page;
    }

    @Override
    public void productHtml(List<Order> content) {
        for(Order order:content){
            order.setHandleTime();
            order.setCode();
            order.setDataString();
        }
    }

    @Override
    public JSONObject findAll(OrderReq orderReq) {
        JSONObject data = new JSONObject();
        Integer pageNo = orderReq.getPageNo();
        Integer pageSize = orderReq.getPageSize();
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize<1) {
            pageSize = 10;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "date"); //创建时间降序排序
        Pageable pageable = new PageRequest(pageNo-1, pageSize, sort);
        Page<Order> page = orderRepository.findByLoginName(
                WebContent.getInstance().getUserThreadLocal().get().getLoginName(), pageable);

        data.put("count",page.getTotalElements());
        data.put("totalPages",page.getTotalPages());
        productHtml(page.getContent());
        data.put("data",page.getContent());
        return data;
    }
}
