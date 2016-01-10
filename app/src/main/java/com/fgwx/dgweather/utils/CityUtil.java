package com.fgwx.dgweather.utils;

import android.content.Context;

import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.CityDao;

import java.util.List;

/**
 * 作者：燕青 $ on 16/1/10 18:20
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class CityUtil {

    /**
     * 获取城市
     * @param context
     * @param cityName
     * @return
     */
    public static CityBean getCurrentCity(Context context, String cityName){
        CityDao cityDao = new CityDao(context);
        List<CityBean> citys = cityDao.getCityByName(cityName);
        if(citys!=null&&citys.size()>0){
            return citys.get(0);
        }
        return null;
    }
}
