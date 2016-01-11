package com.fgwx.dgweather.bean;

import java.util.List;

/**
 * 作者：燕青 $ on 16/1/3 16:47
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class BaseCityBean {


    private int code;
    private String msg;
    /**
     * _class : SysAreaManageModel
     * comment : 中国
     * createTime : 1452072710000
     * creator : null
     * delFlag : 0
     * description :
     * etd1 : null
     * etd2 : null
     * etd3 : null
     * hotSort : 10001
     * id : 100000
     * isHot : 0
     * isLocal : 0
     * keyword :
     * lat : 39.924626
     * lng : 116.403349
     * longCode :
     * mapLevel : 5
     * modifier : null
     * modifyTime : 1452072710000
     * name : 中 国
     * parentCode :
     * remarks : null
     * sort : 10001
     * version : 0
     */

    private List<CityBean> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<CityBean> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<CityBean> getData() {
        return data;
    }

}
