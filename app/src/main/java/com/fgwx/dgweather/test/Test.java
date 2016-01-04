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
}
