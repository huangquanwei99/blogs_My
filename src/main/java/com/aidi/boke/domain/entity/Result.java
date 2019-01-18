package com.aidi.boke.domain.entity;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    //是否成功
    private boolean success;

    //返回一个字符串
    private String message;

    //对象返回
    private List list;

    public Result() {
    }
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public Result(boolean success, String message, List list) {
        this.success = success;
        this.message = message;
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
