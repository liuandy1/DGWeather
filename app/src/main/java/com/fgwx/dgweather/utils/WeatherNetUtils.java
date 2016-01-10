package com.fgwx.dgweather.utils;

import com.android.volley.Response;
import com.fgwx.dgweather.net.VolleySingleton;
import com.fgwx.dgweather.net.WeatherRequest;
import com.google.gson.JsonObject;

import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherNetUtils {
    private static final String URL_BASE="http://58.252.5.166:10008";
    private static final String URL_HOME_FORECAST=URL_BASE+"/dgweatherweb/webservice/getData";
    private static final String URL_AREA=URL_BASE+"/dgweatherweb/webservice/getAreas";


    private static final String URL_TEST="http://58.252.5.166:10008/dgweatherweb/webservice/getData?cityId=441900&streetId=&last10DayTime=&query10Day=1&siteId=G1901&lastSiteTime=&queryLife=1&lastLifeTime=1&querySun=1&lastSunTime=&queryExact=1";


    //首页接口
   public static void getHomeForecastData(Response.Listener<JsonObject> listener,Response.ErrorListener errorListener,TreeMap<String,String> map){
       WeatherRequest<JsonObject> request=new WeatherRequest<JsonObject>(URL_HOME_FORECAST,JsonObject.class,listener,map,errorListener);
       VolleySingleton.getInstance().addToRequestQueue(request);
   }

/*    //获得区域信息
    public static void getAreaData(Response.Listener<AreaBaseBean> listener,Response.ErrorListener errorListener){
        WeatherRequest<AreaBaseBean> request=new WeatherRequest<AreaBaseBean>(URL_AREA,AreaBaseBean.class,listener,null,errorListener);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }*/
}
