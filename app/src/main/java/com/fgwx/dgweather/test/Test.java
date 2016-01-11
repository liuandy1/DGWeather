package com.fgwx.dgweather.test;

import android.test.AndroidTestCase;

import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.utils.CityUtil;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.SiteUtil;

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
        CityBean city = CityUtil.getCityByName(getContext(), "东莞市");
        LogUtil.e(""+city.getName()+":"+city.getId());
//        114.028532,23.079036
        SiteBean.DataEntity site = SiteUtil.getCloseSite(getContext(), 23.079036f, 114.028532f);
        LogUtil.e("地址:"+site.getAddress()+"  id"+site.getId()+"  名字:"+site.getName());
    }

    public void testStr() {
        List<String> list = pailie("123");
        int length = list.size();
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

    public void testDate() {
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
            LogUtil.e("temps:" + temps.size());
            for (int i = 0; i < temps.size(); i++) {
                // 增一个字符通过插空得到所有的可能清空
                String temp = temps.get(i);
                LogUtil.e("temp:" + temp);
                for (int j = 0; j <= temp.length(); j++) {
                    String head = temp.substring(0, j);
                    String tail = temp.substring(j);
                    LogUtil.e("head:" + head + "  thisChar:" + thisChar + "    tail:" + tail);
                    result.add(head + thisChar + tail);
                }
            }
        }
        return result;
    }

    public int maxSubSum1(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                int thisSum = 0;
                for (int k = 0; k <= j; k++) {
                    thisSum += a[k];
                    if (thisSum > maxSum) {
                        maxSum = thisSum;
                    }
                }
            }
        }
        return maxSum;
    }
}
