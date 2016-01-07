package com.fgwx.dgweather.test;

import android.test.AndroidTestCase;

import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.CityDao;
import com.fgwx.dgweather.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：燕青 on 2016/1/4 19:48
 * 邮箱：359222347@qq.com
 */
public class Test extends AndroidTestCase {
    public void testLocalCity() {
        CityDao cityDao = new CityDao(getContext());
        List<CityBean> citys = cityDao.getLocalCity();
        if (null != citys)
            for (CityBean cityBean : citys) {
                LogUtil.e("城市名字:" + cityBean.getCityName());
            }
    }

    public void testVoice() {
        String voice = "唯衣是一家零售兼批发的服装，全称是广州唯衣网络科技有限公司。公司坐落在广东省广州市，拒绝经营假名牌和仿名牌，只做最主流的，最时尚的，品质最好的品牌女装，产品汇集国内外中高档知名女装品牌、一线女装精品，并与其结成战略联盟，每季提供近千余品种款式供我们的客户和消费者们选择。";
        // 这是语言合成部分 同样需要实例化一个SynthesizerDialog ，并输入appid
    }

    public void testStr() {
        List<String> list = pailie("aaa");
        int length = list.size();
        for (int i = 0; i < length; i++) {
            LogUtil.e(i + ":" + list.get(i));
        }
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
            for (int i = 0; i < temps.size(); i++) {
                // 增一个字符通过插空得到所有的可能清空
                String temp = temps.get(i);
                for (int j = 0; j <= temp.length(); j++) {
                    String head = temp.substring(0, j);
                    String tail = temp.substring(j);
                    result.add(head + thisChar + tail);
                }
            }
        }
        return result;
    }
}
