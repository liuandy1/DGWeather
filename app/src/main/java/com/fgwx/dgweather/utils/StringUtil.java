package com.fgwx.dgweather.utils;

/**
 * Created by M on 2016/1/17.
 */
public class StringUtil {
    /**
     * 是否为null或空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return null == str || str.length() == 0;
    }

    /**
     * 根据 任意字符拆分字符串
     *
     * @param str
     * @return
     */
    public static String split(String str, String expr) {
        if (!isEmpty(str)) {
            if(str.contains(expr)){
                return str.split(expr)[0];
            }else{
                return str;
            }
        }
        return null;
    }

}
