package com.sud.order.model;

import com.sud.common.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "education_order")
public class Order {
    @Id
    @GeneratedValue(generator = "sys_uuid")
    @GenericGenerator(name = "sys_uuid", strategy = "uuid")
    @ApiModelProperty(hidden=true)
    private String id;

    @Column(name = "area",length = 50)
    @ApiModelProperty(hidden=true)
    private String area;

    @Column(name = "project",length = 50)
    @ApiModelProperty("项目")
    private String project;

    @Column(name = "content",length = 50)
    @ApiModelProperty("内容")
    private String content;

    @Column(name = "name",length = 50)
    @ApiModelProperty(hidden=true)
    private String name;

    @Column(name = "login_name",length = 50)
    @ApiModelProperty(hidden=true)
    private String loginName;

    @Column(name = "date")
    @ApiModelProperty(hidden=true)
    private Date date;

    @Column(name = "handle_company",length = 50)
    @ApiModelProperty(hidden=true)
    private String handleCompany;

    @Column(name = "handle_name",length = 50)
    @ApiModelProperty(hidden=true)
    private String handleName;

    @Column(name = "handle_login_name",length = 50)
    @ApiModelProperty(hidden=true)
    private String handleLoginName;

    @Column(name = "handle_date")
    @ApiModelProperty(hidden=true)
    private Date handleDate;

    @Column(name = "status")
    @ApiModelProperty(
            value="单子状态，" +
                    "0：待审核；1：待受理；2：待派单；3：待完工；" +
                    "4：维修中；5：已完工；6：已回访；7：已维修；" +
                    "8：未验证；9：锁定；10：已驳回；11：已返修；" +
                    "12：已暂停；13：已评价", example = "0")
    private Integer status;

    @ApiModelProperty(hidden=true)
    private String code;
    @ApiModelProperty(hidden=true)
    private String dataString;
    @ApiModelProperty(hidden=true)
    private String handleTime;

    @Column(name = "evaluate",length = 50)
    @ApiModelProperty("评价")
    private String evaluate;


    @Column(name = "result",length = 50)
    @ApiModelProperty("维修结果")
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHandleCompany() {
        return handleCompany;
    }

    public void setHandleCompany(String handleCompany) {
        this.handleCompany = handleCompany;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getHandleLoginName() {
        return handleLoginName;
    }

    public void setHandleLoginName(String handleLoginName) {
        this.handleLoginName = handleLoginName;
        this.setHandleDate(new Date());
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode() {
        if(this.date != null){
            this.code = DateUtil.stampToCode(this.date.getTime());
        }
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString() {
        if(this.date != null){
            this.dataString = DateUtil.stampToDate2(this.date.getTime());
        }
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setHandleTime() {
        if(this.handleDate != null){
            long t = new Date().getTime()-this.handleDate.getTime();
            BigDecimal bg = new BigDecimal(t/1000/60/60);
            this.handleTime = bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
    }
}
