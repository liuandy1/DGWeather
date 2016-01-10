package com.fgwx.dgweather.test;

import android.test.AndroidTestCase;

import com.fgwx.dgweather.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者：燕青 on 2016/1/4 19:48
 * 邮箱：359222347@qq.com
 */
public class Test extends AndroidTestCase {
    public void testLocalCity() {
//        CityDao cityDao = new CityDao(getContext());
//        List<BaseCityBean> citys = cityDao.getLocalCity();
//        if (null != citys)
//            for (BaseCityBean cityBean : citys) {
//                LogUtil.e("城市名字:" + cityBean.);
//            }
    }

    public void testVoice() {
//        InputStream in = getResources().getAssets().open(fileName);
    }

    public void testStr() {
        List<String> list = pailie("123");
        int length = list.size();
//        for (int i = 0; i < length; i++) {
//            LogUtil.e(i + ":" + list.get(i));
//        }
    }

    public void testStr1() {
        String a = "abcd";
        char[] chars = a.toCharArray();
//        LogUtil.e(a.substring(1));
        for (int i = 0; i < chars.length; i++) {
            char c = a.charAt(i);
            String b = a.substring(i);
//            for (int j = 0; j < b.length(); j++) {
//                String d = b.substring(0, j);
//                c + d +
//            }
        }
    }

    public void testDate(){
        long a = 1452182400000L;
        Date date = new Date(a);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateStr = dateFormat.format(date);
        LogUtil.e(dateStr);
    }

    public static List<String> pailie(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        if (source.length() == 1) {
            result.add(source);
        } else {
            char thisChar = source.charAt(0);
            List<String> temps = pailie(source.substring(1));
            LogUtil.e("temps:" + temps.size() );
            for (int i = 0; i < temps.size(); i++) {
                // 增一个字符通过插空得到所有的可能清空
                String temp = temps.get(i);
                LogUtil.e("temp:" + temp );
                for (int j = 0; j <= temp.length(); j++) {
                    String head = temp.substring(0, j);
                    String tail = temp.substring(j);
                    LogUtil.e("head:" + head + "  thisChar:" + thisChar+"    tail:" + tail);
                    result.add(head + thisChar + tail);
                }
            }
        }
        return result;
    }
}
