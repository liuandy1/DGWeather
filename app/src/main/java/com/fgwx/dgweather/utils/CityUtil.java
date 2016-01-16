package com.fgwx.dgweather.utils;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.CityDao;
import com.fgwx.dgweather.db.SiteDao;

import java.util.List;

/**
 * 作者：燕青 $ on 16/1/10 18:20
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class CityUtil {

    private static ReverseGeoCodeResult.AddressComponent address;

    /**
     * 获取城市
     *
     * @param context
     * @param cityName
     * @return
     */
    public static CityBean getCityByName(Context context, String cityName) {
        List<CityBean> citys = new CityDao(context).getCityByName(cityName);
        if (citys != null && citys.size() > 0) {
            return citys.get(0);
        }
        return null;
    }

    /**
     * 这个方法还有点问题。先不用
     *
     * 根据经纬度获取城市名
     *
     * @param lat
     * @param lon
     */
    private static String getCityByLocation(float lat, float lon) {
        GeoCoder mSearch = GeoCoder.newInstance();
        final String[] city = new String[1];
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                address = reverseGeoCodeResult.getAddressDetail();
                city[0] = address.city;
            }
        });
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(lat, lon)));
        return city[0];
    }

    public List<CityBean> getCityByKeyWords(Context context, String keyWords){
       return new CityDao(context).getCityByKeyWords(keyWords);
    }
}
