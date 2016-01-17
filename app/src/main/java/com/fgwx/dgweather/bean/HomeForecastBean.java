package com.fgwx.dgweather.bean;

import java.util.List;

/**
 * Created by senghor on 2016/1/2.
 */
public class HomeForecastBean {
     private String cityName;
     private List<ForecastForTenDayBean> days;
     private List<ForecastForHourBean> exacts;
     private ForecastEarlyWarnBean warn;
     private ForecastPeopleLifeBean life;
     private ForecastMonitorSiteBean site;

     public String getCityName() {
          return cityName;
     }

     public void setCityName(String cityName) {
          this.cityName = cityName;
     }

     public List<ForecastForHourBean> getExacts() {
          return exacts;
     }

     public void setExacts(List<ForecastForHourBean> exacts) {
          this.exacts = exacts;
     }

     public List<ForecastForTenDayBean> getDays() {
          return days;
     }

     public void setDays(List<ForecastForTenDayBean> days) {
          this.days = days;
     }


     public ForecastEarlyWarnBean getWarn() {
          return warn;
     }

     public void setWarn(ForecastEarlyWarnBean warn) {
          this.warn = warn;
     }

     public ForecastPeopleLifeBean getLife() {
          return life;
     }

     public void setLife(ForecastPeopleLifeBean life) {
          this.life = life;
     }

     public ForecastMonitorSiteBean getSite() {
          return site;
     }

     public void setSite(ForecastMonitorSiteBean site) {
          this.site = site;
     }
}
