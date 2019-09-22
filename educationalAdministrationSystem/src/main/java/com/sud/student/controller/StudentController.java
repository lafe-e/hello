package com.sud.student.controller;

import com.alibaba.fastjson.JSONObject;
import com.sud.common.WebContent;
import com.sud.common.dto.BaseRes;
import com.sud.order.dto.OrderReq;
import com.sud.order.model.Order;
import com.sud.order.repository.OrderRepository;
import com.sud.order.service.OrderService;
import com.sud.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/order/student")
@Api(description = "学生")
public class StudentController {
    @Resource
    OrderRepository orderRepository;
    @Resource
    OrderService orderService;
    @ApiOperation(value = "获取订单", notes = "获取订单")
    @RequestMapping(value = "/getOrder", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes getTab(@RequestBody OrderReq orderReq) {
        BaseRes result = new BaseRes();
        try {
            result.setData(orderService.findAll(orderReq));
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("拉取数据异常");
            e.printStackTrace();
        }

        return result;
    }

    @ApiOperation(value = "添加订单", notes = "添加订单")
    @RequestMapping(value = "/addOrder", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes addOrder(@RequestBody Order order) {
        BaseRes result = new BaseRes();
        try {
            User user = WebContent.getInstance().getUserThreadLocal().get();
            order.setDate(new Date());
            order.setLoginName(user.getLoginName());
            order.setArea(user.getArea());
            order.setStatus(0);
            order.setName(user.getName());
            orderRepository.save(order);
            result.setData(order);
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("拉取数据异常");
            e.printStackTrace();
        }

        return result;
    }
}
