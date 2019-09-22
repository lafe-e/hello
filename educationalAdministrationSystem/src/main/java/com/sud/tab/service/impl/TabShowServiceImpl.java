package com.sud.tab.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sud.tab.model.TabShow;
import com.sud.tab.repository.TabShowRepository;
import com.sud.tab.service.TabShowService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TabShowServiceImpl implements TabShowService {

    @Resource
    TabShowRepository tabShowRepository;

    @Override
    public JSONObject getTabShows(Integer grade, Integer pageNo, Integer pageSize, Integer sortType) {
        JSONObject result = new JSONObject();

        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize<1) {
            pageSize = 10;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "date"); //创建时间降序排序
        if (sortType != null && sortType.equals(1)) {
            sort = new Sort(Sort.Direction.ASC, "date");
        }
        Pageable pageable = new PageRequest(pageNo-1, pageSize, sort);
        Page<TabShow> page = tabShowRepository.findByGrade(grade, pageable);

        result.put("count",page.getTotalElements());
        result.put("totalPages",page.getTotalPages());
        productDateStr(page.getContent());
        result.put("data",page.getContent());

        return result;
    }

    private void productDateStr(List<TabShow> content) {
        for(TabShow tabShow:content){
            tabShow.setDataString();
        }
    }

    @Override
    public void addTabShow(TabShow tabShow) {
        tabShowRepository.save(tabShow);
    }
}
