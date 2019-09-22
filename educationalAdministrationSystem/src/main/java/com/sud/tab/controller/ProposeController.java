package com.sud.tab.controller;

import com.sud.common.WebContent;
import com.sud.common.dto.BaseRes;
import com.sud.tab.model.Propose;
import com.sud.tab.model.TabShow;
import com.sud.tab.repository.ProposeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/order/propose")
@Api(description = "")
public class ProposeController {

    @Resource
    ProposeRepository proposeRepository;

    @ApiOperation(value = "", notes = "")
    @RequestMapping(value = "/addPropose", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes<Propose> addPropose(@RequestBody Propose propose) {
        BaseRes<Propose> result = new BaseRes<Propose>();
        try {
            propose.setDate(new Date());
            propose.setLoginName(WebContent.getInstance().getUserThreadLocal().get().getLoginName());
            propose.setName(WebContent.getInstance().getUserThreadLocal().get().getName());
            proposeRepository.save(propose);
            result.setData(propose);
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("添加数据异常");
            e.printStackTrace();
        }

        return result;
    }
    @ApiOperation(value = "", notes = "")
    @RequestMapping(value = "/getPropose", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes<Propose> getPropose() {
        BaseRes result = new BaseRes();
        try {
            result.setData(proposeRepository.findAll());
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("添加数据异常");
            e.printStackTrace();
        }

        return result;
    }
}
