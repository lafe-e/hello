package com.sud.tab.service;

import com.alibaba.fastjson.JSONObject;
import com.sud.tab.model.TabShow;

public interface TabShowService {
    JSONObject getTabShows(Integer grade, Integer pageNo, Integer pageSize, Integer sortType);

    void addTabShow(TabShow tabShow);
}

