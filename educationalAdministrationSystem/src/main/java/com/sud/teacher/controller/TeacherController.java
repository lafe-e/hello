package com.sud.teacher.controller;

import com.alibaba.fastjson.JSONObject;
import com.sud.common.WebContent;
import com.sud.common.dto.BaseRes;
import com.sud.order.dto.OrderReq;
import com.sud.order.model.Order;
import com.sud.order.repository.OrderRepository;
import com.sud.order.service.OrderService;
import com.sud.user.entity.User;
import com.sud.user.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/order/teacher")
@Api(description = "教师")
public class TeacherController {
    @Resource
    OrderRepository orderRepository;
    @Resource
    OrderService orderService;

    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "获取订单", notes = "获取订单")
    @RequestMapping(value = "/getOrder", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes getTab(@RequestBody OrderReq orderReq) {
        BaseRes result = new BaseRes();
        try {
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
            Page<Order> page = orderRepository.findAll(pageable);

            data.put("count",page.getTotalElements());
            data.put("totalPages",page.getTotalPages());
            orderService.productHtml(page.getContent());
            data.put("data",page.getContent());
            result.setData(data);
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("拉取数据异常");
            e.printStackTrace();
        }

        return result;
    }

    @ApiOperation(value = "更新订单", notes = "更新订单")
    @RequestMapping(value = "/modOrder", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes modOrder(@RequestBody Order order) {
        BaseRes result = new BaseRes();
        try {
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
    @ApiOperation(value = "更新用户", notes = "更新订单")
    @RequestMapping(value = "/modTip", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes modTip(HttpServletRequest request, @RequestBody User user) {
        BaseRes result = new BaseRes();
        try {
            User user1 = userRepository.findByLoginName(user.getLoginName());
            user1.setTip(user.getTip());
            userRepository.save(user1);
            HttpSession session = request.getSession();
            session.setAttribute("username",user1.getName());
            session.setAttribute("loginName",user1.getLoginName());
            session.setAttribute("user",user1);
            session.setMaxInactiveInterval(30 * 6000000);
            result.setData(user1);
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("拉取数据异常");
            e.printStackTrace();
        }

        return result;
    }
}
