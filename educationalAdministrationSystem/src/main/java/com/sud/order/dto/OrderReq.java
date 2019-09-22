package com.sud.order.dto;

import com.sud.common.dto.BasePageReq;
import io.swagger.annotations.ApiModelProperty;

public class OrderReq extends BasePageReq {
    @ApiModelProperty(
            value="单子状态，" +
                    "0：待审核；1：待受理；2：待派单；3：待完工；" +
                    "4：维修中；5：已完工；6：已回访；7：已维修；" +
                    "8：未验证；9：锁定；10：已驳回；11：已返修；" +
                    "12：已暂停；13：已评价", example = "0")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
