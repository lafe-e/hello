package com.sud.tab.dto;

import com.sud.common.dto.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class TabRequest extends BasePageReq {


    @ApiModelProperty(value = "1：工作动态，2：公告通知",example = "1")
    private Integer grade;
    @ApiModelProperty(value = "1：asc，其他：desc",example = "0")
    private Integer sortType;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }
}
