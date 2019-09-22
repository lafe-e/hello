package com.sud.common.dto;

public class BaseRes<T> {
    private String message;

    private Integer status = 4;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if(status == 1){
            status = 0;
        }else if(status == 0){
            status = 1;
        }
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "{\"message\":\""+this.message+"\",\"status\":"+this.status+"}";
    }
}
