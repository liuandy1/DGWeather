package com.fgwx.dgweather.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fgwx.dgweather.base.WeatherAppContext;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by senghor on 2016/1/18.
 */
public class AppUtil {

    /**
     * 通过图片资源名获取图片
     */
    public static Drawable getWeathericonDrawableById(String id, Context context) {
        try {
            //Log.i(TAG, "id : " + id);
            if (context == null) {
                context = WeatherAppContext.getAppContext();
            }
            int resId = getResId("@drawable/" + "icon_weather_" + id, context);
            if (resId > 0) {
                return context.getResources().getDrawable(resId);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过图片资源名获取图片
     */
    public static Bitmap getWeathericonDrawableById(String id) {
        Context context = null;
        try {
            //Log.i(TAG, "id : " + id);
            if (context == null) {
                context = WeatherAppContext.getAppContext();
            }
            int resId = getResId("@drawable/" + "icon_weather_" + id, context);
            if (resId > 0) {
                return ((BitmapDrawable) context.getResources().getDrawable(resId)).getBitmap();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过图片资源名获取图片
     */
    public static BitmapDrawable getWeatherBgById(String id) {
        Context context = null;
        try {
            //Log.i(TAG, "id : " + id);
            if (context == null) {
                context = WeatherAppContext.getAppContext();
            }
            int resId = getResId("@drawable/" + "bg_weather_" + id, context);
            if (resId > 0) {
                return ((BitmapDrawable) context.getResources().getDrawable(resId));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    //通过资源名拿资源id
    public static int getResId(String value, Context context) {
        if (value == null) {
            return 0;
        }
        if (context == null) {
            context = WeatherAppContext.getAppContext();
        }
        int ret = -1;
        if (value.charAt(0) == '@') {
            int colon = value.indexOf(":");
            String pac = context.getPackageName();
            if (colon != -1) {
                pac = value.substring(1, colon);
            } else {
                colon = 0;
            }
            int split = value.indexOf("/");
            String type = value.substring(colon + 1, split);
            String name = value.substring(split + 1);

            ret = context.getResources().getIdentifier(name, type, pac);
        }
        return ret;
    }

    /**
     * 判断有没有安装某个应用
     *
     * @param packageName
     * @return
     */
    public static boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 导航
     *
     * @param context
     * @param lat
     * @param lon
     * @param name
     */
    public static void nav(Context context, String lat, String lon, String name) {
        //移动APP调起Android百度地图方式举例
        Intent intent = null;
        //调用百度地图导航
        if (AppUtil.isInstallByread("com.baidu.BaiduMap")) {
            try {
                intent = Intent.getIntent("intent://map/marker?location=" + lat + "," + lon + "&title=我的位置&content=" + name +
                        "&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else if (AppUtil.isInstallByread("com.autonavi.minimap")) {   //高德导航
            try {
                intent = Intent.getIntent("androidamap://viewMap?sourceApplication=厦门通&poiname=" + name + "&lat=" + lat + "&lon=" + lon + "&dev=0");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "您未安装百度地图或高德地图", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(intent); //启动调用
    }


    public static void nav(Context context, double lat, double lon, String name) {
        nav(context, lat + "", lon + "", name);
    }

    public static void setBg(View view, String weatherDes) {

    }

    public static String getText(TextView textView) {
        if (textView == null) {
            return "";
        }

        return TextUtils.isEmpty(textView.getText()) ? "" : textView.getText().toString();
//        if (TextUtils.isEmpty(textView.getText())) {
//
//        }
    }
}
