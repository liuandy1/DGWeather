package com.fgwx.dgweather.bean;

/**
 * Created by senghor on 2016/1/2.
 */
public class ForecastForHourBean {
/*
          "areaCode":区域编码,
                "curDate":生效时间毫秒数,
                "hourVal":小时,
                "id":编号,
                "maxTemp":最高温度,
                "minTemp":最低温度,
                "nigWeaDesc":夜间天气描述,
                "nigWeaIcon":夜间天气图标,
                "reportTime":发布时间毫秒数,
                "tempDesc":温度,
                "weaDesc":白天天气描述,
                "weaIcon":白天天气图标,
                "wet":相对湿度,
                "wind":风力风向*/
    private String areaCode;
    private long curDate;
    private String hourVal;
    private String id;
    private String maxTemp;
    private String minTemp;
    private String nigWeaDesc;
    private String nigWeaIcon;
    private String reportTime;
    private int tempDesc;
    private String weaDesc;
    private String weaIcon;
    private String wet;
    private String wind;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public long getCurDate() {
        return curDate;
    }

    public void setCurDate(long curDate) {
        this.curDate = curDate;
    }

    public String getHourVal() {
        return hourVal;
    }

    public void setHourVal(String hourVal) {
        this.hourVal = hourVal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getNigWeaDesc() {
        return nigWeaDesc;
    }

    public void setNigWeaDesc(String nigWeaDesc) {
        this.nigWeaDesc = nigWeaDesc;
    }

    public String getNigWeaIcon() {
        return nigWeaIcon;
    }

    public void setNigWeaIcon(String nigWeaIcon) {
        this.nigWeaIcon = nigWeaIcon;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public int getTempDesc() {
        return tempDesc;
    }

    public void setTempDesc(int tempDesc) {
        this.tempDesc = tempDesc;
    }

    public String getWeaDesc() {
        return weaDesc;
    }

    public void setWeaDesc(String weaDesc) {
        this.weaDesc = weaDesc;
    }

    public String getWeaIcon() {
        return weaIcon;
    }

    public void setWeaIcon(String weaIcon) {
        this.weaIcon = weaIcon;
    }

    public String getWet() {
        return wet;
    }

    public void setWet(String wet) {
        this.wet = wet;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
