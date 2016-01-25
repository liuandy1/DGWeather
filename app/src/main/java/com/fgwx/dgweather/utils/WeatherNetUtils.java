package com.fgwx.dgweather.utils;

import com.android.volley.Response;
import com.fgwx.dgweather.bean.DangerAndShelterBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.bean.SiteMonitorBaseBean;
import com.fgwx.dgweather.net.VolleySingleton;
import com.fgwx.dgweather.net.WeatherRequest;

import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherNetUtils {
    private static final String URL_BASE="http://58.252.5.166:10008";
 //   private static final String URL_BASE="http://120.86.191.99";
    private static final String URL_HOME_FORECAST=URL_BASE+"/dgweatherweb/webservice/getData";
    private static final String URL_MONITOR=URL_BASE+"/dgweatherweb/webservice/getSiteDatas";
    private static final String URL_AREA=URL_BASE+"/dgweatherweb/webservice/getAreas";
    private static final String URL_DANGERANDSHELTER = URL_BASE+"/dgweatherweb/webservice/getDngShts";


    private static final String URL_TEST="http://58.252.5.166:10008/dgweatherweb/webservice/getData?cityId=441900&streetId=&last10DayTime=&query10Day=1&siteId=G1901&lastSiteTime=&queryLife=1&lastLifeTime=1&querySun=1&lastSunTime=&queryExact=1";


    //首页接口
   public static void getHomeForecastData(Response.Listener<HomeForecastBaseBean> listener,Response.ErrorListener errorListener,TreeMap<String,String> map){
       WeatherRequest<HomeForecastBaseBean> request=new WeatherRequest<HomeForecastBaseBean>(URL_HOME_FORECAST,HomeForecastBaseBean.class,listener,map,errorListener);
       VolleySingleton.getInstance().addToRequestQueue(request);
   }
    //站点监测数据
    public static void getSiteMonitorData(Response.Listener<SiteMonitorBaseBean> listener,Response.ErrorListener errorListener,TreeMap<String,String> map){
        WeatherRequest<SiteMonitorBaseBean> request=new WeatherRequest<SiteMonitorBaseBean>(URL_MONITOR,SiteMonitorBaseBean.class,listener,map,errorListener);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    public static void getDangerAndShelter(Response.Listener<DangerAndShelterBean> listener,Response.ErrorListener errorListener,TreeMap<String,String> map){
        WeatherRequest<DangerAndShelterBean> request=new WeatherRequest<DangerAndShelterBean>(URL_DANGERANDSHELTER,DangerAndShelterBean.class,listener,map,errorListener);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }


/*    //获得区域信息
    public static void getAreaData(Response.Listener<AreaBaseBean> listener,Response.ErrorListener errorListener){
        WeatherRequest<AreaBaseBean> request=new WeatherRequest<AreaBaseBean>(URL_AREA,AreaBaseBean.class,listener,null,errorListener);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }*/
}
