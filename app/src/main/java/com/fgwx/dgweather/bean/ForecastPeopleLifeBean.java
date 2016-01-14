package com.fgwx.dgweather.bean;

/**
 * Created by senghor on 2016/1/2.
 */
public class ForecastPeopleLifeBean {

   /* "airPolDesc":空气污染气象条件,
            "airQuan":空气质量预报,
            "areaCode":区域编码,
            "bodySoft":人体舒适度指数,
            "curDate":日期毫秒数,
            "fire":易燃指数,
            "id":编号,
            "mornExercise":晨练指数,
            "mould":霉变指数,
            "outing":郊游指数,
            "reportTime":发布时间毫秒数,
            "sunDry":晾晒指数,
            "travel":交通指数,
            "umbella":雨伞指数,*/
    private String airPolDesc;
    private String airQuan;
    private String areaCode;
    private String bodySoft;
    private String curDate;
    private String fire;
    private String id;
    private String mornExercise;
    private String mould;
    private String outing;
    private String reportTime;
    private String sunDry;
    private String travel;
    private String umbella;

    public String getAirPolDesc() {
        return airPolDesc;
    }

    public void setAirPolDesc(String airPolDesc) {
        this.airPolDesc = airPolDesc;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAirQuan() {
        return airQuan;
    }

    public void setAirQuan(String airQuan) {
        this.airQuan = airQuan;
    }

    public String getBodySoft() {
        return bodySoft;
    }

    public void setBodySoft(String bodySoft) {
        this.bodySoft = bodySoft;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMornExercise() {
        return mornExercise;
    }

    public void setMornExercise(String mornExercise) {
        this.mornExercise = mornExercise;
    }

    public String getMould() {
        return mould;
    }

    public void setMould(String mould) {
        this.mould = mould;
    }

    public String getOuting() {
        return outing;
    }

    public void setOuting(String outing) {
        this.outing = outing;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getSunDry() {
        return sunDry;
    }

    public void setSunDry(String sunDry) {
        this.sunDry = sunDry;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getUmbella() {
        return umbella;
    }

    public void setUmbella(String umbella) {
        this.umbella = umbella;
    }
}
