package com.sud.order.controller;

import com.sud.common.dto.BaseRes;
import com.sud.order.model.Order;
import com.sud.order.repository.OrderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Api(description = "订单受理控制类")
public class OrderController {

    @Resource
    OrderRepository orderRepository;

    @ApiOperation(value = "获取tab页数据", notes = "获取tab页数据")
    @RequestMapping(value = "/getOrders", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes getTab() {
        BaseRes result = new BaseRes();
        return result;
    }
    @ApiOperation(value = "获取tab页数据", notes = "获取tab页数据")
    @RequestMapping(value = "/saveOrder", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes saveOrder(@RequestBody Order order) {
        BaseRes result = new BaseRes();
        orderRepository.save(order);
        result.setStatus(1);
        return result;
    }
}
