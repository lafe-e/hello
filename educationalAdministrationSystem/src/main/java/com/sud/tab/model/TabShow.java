package com.sud.tab.model;

import com.sud.common.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tab_show")
public class TabShow {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "sys_uuid")
    @GenericGenerator(name = "sys_uuid", strategy = "uuid")
    @ApiModelProperty(hidden=true)
    private String id;


    @Column(name = "content",length = 10000)
    @ApiModelProperty("内容")
    private String content;

    @Column(name = "grade")
    @ApiModelProperty(value="分类，1：工作动态，2：公告通知", example = "1")
    private Integer grade;

    @Column(name = "date")
    @ApiModelProperty("时间")
    private Date date;

    @ApiModelProperty(hidden=true)
    private String dataString;

    @Column(name = "link",length = 500)
    @ApiModelProperty("链接")
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString() {
        this.dataString = DateUtil.stampToDate(date.getTime());
    }
}

