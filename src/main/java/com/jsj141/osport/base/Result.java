package com.jsj141.osport.base;

import com.iw86.base.JsonUtil;

/**
 * 定义的基础api返回字段
 */
public class Result {
    int code;
    String msg;
    Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.objToStr(this);
    }
}
