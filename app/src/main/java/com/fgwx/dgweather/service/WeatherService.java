package com.fgwx.dgweather.service;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.WeatherNetUtils;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

/**
 * 作者：燕青 on 2016/1/8 14:00
 * 邮箱：359222347@qq.com
 */
public class WeatherService extends Service {

    private AlarmManager alarmMgr;

    @Override
    public void onCreate() {
        super.onCreate();
//        timer.schedule(task, 1000, 500); // 1s后执行task,经过1s再次执行
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getInfo(){
        /**
         * cityId=441900
         * &streetId=&l
         * ast10DayTime=
         * &siteId=G1901
         * &lastSiteTime=
         * &queryLife=1
         * &lastLifeTime=1
         * &querySun=1
         * &lastSunTime=
         */
//        WeatherRequest.
        TreeMap<String, String> map = new TreeMap<>();
        map.put("cityId", "441900");
        map.put("streetId", null);
        map.put("siteId", "G1901");
        map.put("last10DayTime", null);
        map.put("query10Day", "1");
        map.put("queryExact ", "1");
        map.put("lastExactTime ", null);
        map.put("lastSiteTime", null);
        map.put("queryLife", "1");
        map.put("lastLifeTime","1");
        map.put("querySun","1");
        map.put("lastSunTime",null);
        WeatherNetUtils.getHomeForecastData(new Response.Listener<HomeForecastBaseBean>() {
            @Override
            public void onResponse(HomeForecastBaseBean response) {
                LogUtil.e("访问成功了");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("访问失败了");
                LogUtil.e(error.toString());
            }
        }, map);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                tvShow.setText(Integer.toString(i++));
                getInfo();
            }
            super.handleMessage(msg);
        };
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
}
