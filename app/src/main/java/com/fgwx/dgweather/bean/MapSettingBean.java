package com.fgwx.dgweather.bean;

import com.fgwx.dgweather.utils.MPreferencesUtil;

/**
 * 作者：燕青 $ on 16/1/14 23:09
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class MapSettingBean {
    private boolean realWeather;
    private boolean realData;
    private boolean disasterPoint;
    private boolean refudge;
    private boolean realPhoto;
    private int siteType;

    public boolean isRealWeather() {
        return realWeather;
    }

    public void setRealWeather(boolean realWeather) {
        this.realWeather = realWeather;
    }

    public boolean isRealData() {
        return realData;
    }

    public void setRealData(boolean realData) {
        this.realData = realData;
    }

    public boolean isDisasterPoint() {
        return disasterPoint;
    }

    public void setDisasterPoint(boolean disasterPoint) {
        this.disasterPoint = disasterPoint;
    }

    public boolean isRefudge() {
        return refudge;
    }

    public void setRefudge(boolean refudge) {
        this.refudge = refudge;
    }

    public boolean isRealPhoto() {
        return realPhoto;
    }

    public void setRealPhoto(boolean realPhoto) {
        this.realPhoto = realPhoto;
    }

    public int getSiteType() {
        return siteType;
    }

    public void setSiteType(int siteType) {
        this.siteType = siteType;
    }
}
