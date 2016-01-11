package com.fgwx.dgweather.bean;

import java.util.List;

/**
 * Created by senghor on 2016/1/2.
 */
public class HomeForecastBean {
     private List<ForecastForTenDayBean> days;
     private ForecastForHourBean hours;
     private ForecastEarlyWarnBean warn;
     private ForecastPeopleLifeBean life;
     private ForecastMonitorSiteBean site;

     public List<ForecastForTenDayBean> getDays() {
          return days;
     }

     public void setDays(List<ForecastForTenDayBean> days) {
          this.days = days;
     }

     public ForecastForHourBean getHours() {
          return hours;
     }

     public void setHours(ForecastForHourBean hours) {
          this.hours = hours;
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