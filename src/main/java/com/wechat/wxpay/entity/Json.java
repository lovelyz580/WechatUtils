package com.wechat.wxpay.entity;

/**
 * @Description:
 * @Date: 2018/4/8
 * @Author: wcf
 */
public class Json {
    private boolean success;
    private String msg;
    private int  code;    //   200  成功   500  失败
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
