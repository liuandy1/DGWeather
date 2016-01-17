package com.fgwx.dgweather.bean;

import java.util.List;

/**
 * Created by senghor on 2016/1/17.
 */
public class SiteMonitorBaseBean {
    private int code;
    private String msg;
    private List<SiteMonitorBean> data;

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

    public List<SiteMonitorBean> getData() {
        return data;
    }

    public void setData(List<SiteMonitorBean> data) {
        this.data = data;
    }
}
