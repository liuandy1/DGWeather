package com.fgwx.dgweather.bean;

/**
 * Created by senghor on 2016/1/2.
 */
public class ForecastSunBean {

   /* "beginTime":开始时间毫秒数,
            "content":内容,
            "icon":图标,
            "id":编号,
            "isValid":是否有效,
            "publishSugg":发布意见,
            "publishTime":发布时间,
            "publisher":发布人,
            "source":来源,
            "susTime":持续时间,
            "tittle":标题,
            "warnLevel":预警等级,
            "warnStatus":预警状态,
            "warnType":预警类型*/
  /*  private String beginTime;
    private String content;
    private String icon;
    private String isValid;
    private String publishSugg;
    private String publishTime;
    private String publisher;
    private String source;
    private String susTime;
    private String tittle;
    private String warnLevel;
    private String warnStatus;
    private String warnType;*/
    private String area;
    private String curData;
    private String id;
    private long sunriseTime;
    private long sunsetTime;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCurData() {
        return curData;
    }

    public void setCurData(String curData) {
        this.curData = curData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }
}
