package com.fgwx.dgweather.test;

import android.test.AndroidTestCase;

import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.CityDao;
import com.fgwx.dgweather.utils.LogUtil;

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

    public void testVoice(){
        String voice = "唯衣是一家零售兼批发的服装，全称是广州唯衣网络科技有限公司。公司坐落在广东省广州市，拒绝经营假名牌和仿名牌，只做最主流的，最时尚的，品质最好的品牌女装，产品汇集国内外中高档知名女装品牌、一线女装精品，并与其结成战略联盟，每季提供近千余品种款式供我们的客户和消费者们选择。";
        // 这是语言合成部分 同样需要实例化一个SynthesizerDialog ，并输入appid


    }
}
