package com.sud.user.userEnum;

public enum Role {
    STUDENT(1,"学生"),
    TEACHER(2,"教师"),
    REPAIROR(3,"维修人员");

    private Integer code;
    private String name;

    Role(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRoleByCode(Integer code){
        for(Role role: Role.values()){
            if(code.equals(role.getCode())){
                return role;
            }
        }
        return null;
    }
}
