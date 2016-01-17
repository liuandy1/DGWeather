package com.fgwx.dgweather.utils;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.fgwx.dgweather.bean.AddedCityBean;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.AddedCityDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：燕青 $ on 16/1/10 18:20
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class AddedCityUtil {

    private static ReverseGeoCodeResult.AddressComponent address;

    /**
     * 获取城市
     *
     * @param context
     * @param cityName
     * @return
     */
    public static AddedCityBean getCityByName(Context context, String cityName) {
        List<AddedCityBean> citys = new AddedCityDao(context).getCityByName(cityName);
        if (citys != null && citys.size() > 0) {
            return citys.get(0);
        }
        return null;
    }

    public static List<AddedCityBean> getCityByKeyWords(Context context, String keyWords) {
        return new AddedCityDao(context).getCityByKeyWords(keyWords);
    }

    public static void addCity(Context context, CityBean bean) {
        new AddedCityDao(context).add(toAddedCityBean(bean));
    }

    public static AddedCityBean toAddedCityBean(CityBean bean) {
        AddedCityBean city = new AddedCityBean();
        try {
            BeanCopyUtil.copy(city, bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return city;
    }

    public static List<CityBean> getAllCity(Context context) {
        return toCityBeanList(new AddedCityDao(context).getAllCity());
    }

    public static List<CityBean> toCityBeanList(List<AddedCityBean> beans) {
        List<CityBean> list = new ArrayList<>();
        for (AddedCityBean bean : beans) {
            CityBean city = new CityBean();
            try {
                BeanCopyUtil.copy(city, bean);
                list.add(city);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
