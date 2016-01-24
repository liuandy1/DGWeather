package com.fgwx.dgweather.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by AllenHu on 2016/1/22.
 */
public class ToastUtil {

    private static final String TAG = ToastUtil.class.getSimpleName();

    /**
     * 显示Toast信息
     *
     * @param msg
     */
    public static void show(Context context, String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast R.string.**的信息
     *
     * @param stringId
     */
    public static void show(Context context, int stringId) {
        String msg = context.getString(stringId);
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
