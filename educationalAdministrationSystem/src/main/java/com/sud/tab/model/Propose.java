package com.sud.tab.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "propose")
public class Propose {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "sys_uuid")
    @GenericGenerator(name = "sys_uuid", strategy = "uuid")
    @ApiModelProperty(hidden=true)
    private String id;

    @Column(name = "project",length = 50)
    @ApiModelProperty("项目")
    private String project;

    @Column(name = "content",length = 10000)
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}

