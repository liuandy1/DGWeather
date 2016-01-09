package com.fgwx.dgweather.utils;

import com.android.volley.Response;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.net.VolleySingleton;
import com.fgwx.dgweather.net.WeatherRequest;

import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherNetUtils {
    private static final String URL_BASE="http://58.252.5.166:10008";
    private static final String URL_HOME_FORECAST=URL_BASE+"/dgweatherweb/webservice/getData";



    //首页接口
   public static void getHomeForecastData(Response.Listener<HomeForecastBaseBean> listener,Response.ErrorListener errorListener,TreeMap<String,String> map){
       WeatherRequest<HomeForecastBaseBean> request=new WeatherRequest<HomeForecastBaseBean>(URL_HOME_FORECAST,HomeForecastBaseBean.class,listener,map,errorListener);
       VolleySingleton.getInstance().addToRequestQueue(request);
   }
}
