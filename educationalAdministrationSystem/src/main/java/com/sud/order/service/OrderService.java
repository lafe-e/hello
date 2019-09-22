package com.sud.order.service;

import com.alibaba.fastjson.JSONObject;
import com.sud.order.dto.OrderReq;
import com.sud.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<Order> findByHandleLoginName(String loginName, Pageable pageable);

    public void productHtml(List<Order> content);

    JSONObject findAll(OrderReq orderReq);

}
