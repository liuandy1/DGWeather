package com.fgwx.dgweather.bean;

/**
 * Created by senghor on 2016/1/2.
 */
public class HomeForecastBaseBean {

    private int code;
    private String msg;
    private HomeForecastBean data;

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

    public HomeForecastBean getData() {
        return data;
    }

    public void setData(HomeForecastBean data) {
        this.data = data;
    }
}
