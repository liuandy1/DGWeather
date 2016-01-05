package com.fgwx.dgweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by  on 15/10/18 23:05.
 */
public class MPreferencesUtil {

    private SharedPreferences preferences;
    private static MPreferencesUtil mInstance;

    public static MPreferencesUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MPreferencesUtil.class) {
                if (mInstance == null)
                    mInstance = new MPreferencesUtil(context);
            }
        }
        return mInstance;
    }

    private MPreferencesUtil(Context context) {
        String name = "dg_weather";
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 写入数据
     *
     * @param key
     * @param value
     */
    public void setValue(String key, Object value) {
        Editor editor = preferences.edit();
        if (value instanceof Integer) {
            editor.putInt(key, Integer.parseInt(String.valueOf(value)));
        } else if (value instanceof String) {
            editor.putString(key, String.valueOf(value));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, Boolean.parseBoolean(String.valueOf(value)));
        } else if (value instanceof Long) {
            editor.putLong(key, Long.parseLong(String.valueOf(value)));
        }
        editor.commit();
    }

    /**
     * 根据key读取数据
     *
     * @param key
     * @return
     */
    public String getValue(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    /**
     * 根据key读取数据
     *
     * @param key
     * @return
     */
    public int getValue(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    /**
     * 根据key读取数据
     *
     * @param key
     * @return
     */
    public boolean getValue(String key, boolean defValue) {

        return preferences.getBoolean(key, defValue);
    }

    /**
     * 根据key读取数据
     *
     * @param key
     * @return
     */
    public long getValue(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

}
