package com.fgwx.dgweather.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.fgwx.dgweather.base.WeatherAppContext;

import java.io.File;

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
        Context context=null;
        try {
            //Log.i(TAG, "id : " + id);
            if (context == null) {
                context = WeatherAppContext.getAppContext();
            }
            int resId = getResId("@drawable/" + "icon_weather_" + id, context);
            if (resId > 0) {
                return ((BitmapDrawable)context.getResources().getDrawable(resId)).getBitmap();
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
     * @param packageName
     * @return
     */
    public static boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
}
