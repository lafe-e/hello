package com.sud.tab.controller;

import com.sud.common.dto.BaseRes;
import com.sud.tab.dto.TabRequest;
import com.sud.tab.model.TabShow;
import com.sud.tab.service.TabShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tab")
@Api(description = "首页控制类")
public class TabAction {
    private Logger logger = LoggerFactory.getLogger(TabAction.class);

    @Resource
    TabShowService tabShowService;

    @ApiOperation(value = "获取tab页数据", notes = "获取tab页数据")
    @RequestMapping(value = "/getTab", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes getTab(@RequestBody TabRequest tabRequest) {
        BaseRes result = new BaseRes();
        try {
            result.setData(tabShowService.getTabShows(tabRequest.getGrade(),
                    tabRequest.getPageNo(), tabRequest.getPageSize(), tabRequest.getSortType()));
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("拉取数据异常");
            e.printStackTrace();
        }

        return result;
    }

    @ApiOperation(value = "添加tab页数据", notes = "添加tab页数据")
    @RequestMapping(value = "/addTab", consumes = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public BaseRes<TabShow> addTab(@RequestBody TabShow tabShow) {
        BaseRes<TabShow> result = new BaseRes<TabShow>();
        try {
            tabShowService.addTabShow(tabShow);
            result.setData(tabShow);
            result.setStatus(1);
        } catch (Exception e) {
            result.setStatus(0);
            result.setMessage("添加数据异常");
            e.printStackTrace();
        }

        return result;
    }

}

