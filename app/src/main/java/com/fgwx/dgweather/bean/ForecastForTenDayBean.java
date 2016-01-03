package com.fgwx.dgweather.bean;

/**
 * Created by senghor on 2016/1/2.
 */
public class ForecastForTenDayBean {

  /*  "airQua":空气质量,
            "areaCode":区域编码,
            "bodyTemp":体感,
            "curDate":日期毫秒数,
            "curMaxTemp":当天最高温度,
            "curMinTemp":当天最低温度,
            "id":编号,
            "liveDesc":生活指数,
            "pressure":气压,
            "reportTime": 数据上报时间毫秒数,
            "sunriseTime":日出时间毫秒数,
            "sunsetTime":日落时间毫秒数,
            "visibility":能见度,
            "weaDesc":天气描述,
            "weaIcon":天气图标,
            "wet":湿度,
            "wind":风力风向,
            "windDirection":风向*/
    private String airQua;
    private String areaCode;
    private String bodyTemp;
    private String curDate;
    private int curMaxTemp;
    private int curMinTemp;
    private String id;
    private String liveDesc;
    private String pressure;
    private String reportTime;
    private String sunriseTime;
    private String sunsetTime;
    private String visibility;
    private String weaDesc;
    private String weaIcon;
    private String wet;
    private String wind;
    private String windDirection;


    public String getAirQua() {
        return airQua;
    }

    public void setAirQua(String airQua) {
        this.airQua = airQua;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(String bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public int getCurMaxTemp() {
        return curMaxTemp;
    }

    public void setCurMaxTemp(int curMaxTemp) {
        this.curMaxTemp = curMaxTemp;
    }

    public int getCurMinTemp() {
        return curMinTemp;
    }

    public void setCurMinTemp(int curMinTemp) {
        this.curMinTemp = curMinTemp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiveDesc() {
        return liveDesc;
    }

    public void setLiveDesc(String liveDesc) {
        this.liveDesc = liveDesc;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
}
