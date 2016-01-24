package com.fgwx.dgweather.utils;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.db.SiteDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：燕青 $ on 16/1/10 17:59
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class SiteUtil {

    /**
     * 获取最近的站点
     *
     * @param context
     * @param p1      当前的位置
     * @return
     */
    public static SiteBean.DataEntity getCloseSite(Context context, LatLng p1) {
        SiteDao siteDao = new SiteDao(context);
        SiteBean.DataEntity closeSite = null;
        List<SiteBean.DataEntity> list = siteDao.getAllSite();
        double distance = DistanceUtil.getDistance(p1, new LatLng(Double.parseDouble(list.get(0).getLatitude()),
                Double.parseDouble(list.get(0).getLatitude())));
        for (SiteBean.DataEntity data : list) {
            Double nowDistance = DistanceUtil.getDistance(p1, new LatLng(Double.parseDouble(data.getLatitude()), Double.parseDouble(data.getLatitude())));
            if (nowDistance < distance) {
                distance = nowDistance;
                closeSite = data;
            }
        }
        return closeSite;
    }

    /**
     * 根据经纬度获取站点
     *
     * @param context
     * @param lat
     * @param lon
     * @return
     */
    public static SiteBean.DataEntity getCloseSite(Context context, float lat, float lon) {
        LatLng p1 = new LatLng(lat, lon);
        return getCloseSite(context, p1);
    }

    /**
     * 根据站点id查询站点
     *
     * @param context
     * @param code
     * @return
     */
    public static SiteBean.DataEntity getSiteBycode(Context context, String code) {
        SiteDao siteDao = new SiteDao(context);
        List<SiteBean.DataEntity> list = siteDao.getSiteBycode(code);
        return list != null ? list.get(0) : null;
    }


    /**
     * 查询所有的站点
     *
     * @param context
     * @return
     */
    public static List<SiteBean.DataEntity> getAllSite(Context context) {
        SiteDao siteDao = new SiteDao(context);
        List<SiteBean.DataEntity> list = siteDao.getAllSite();
        return list != null ? list : null;
    }

    /**
     * 获取radium为半径的圆里面的点
     *
     * @param context
     * @param radius
     * @return
     */
    public static List<SiteBean.DataEntity> getSiteInCircle(Context context, LatLng pCenter, int radius) {
        List<SiteBean.DataEntity> list = getAllSite(context);
        ArrayList list1 = new ArrayList();
        for (SiteBean.DataEntity dataEntity : list) {
            LatLng latLng = new LatLng(Double.parseDouble(dataEntity.getLatitude()), Double.parseDouble(dataEntity.getLongitude()));
            if (SpatialRelationUtil.isCircleContainsPoint(pCenter, radius, latLng)) {
                list1.add(dataEntity);
            }
        }
        return list1;
    }

    public static String setSiteBeanIdToStringList(List<SiteBean.DataEntity> dataEntities) {
        String str = "";
        if (dataEntities == null) return str;
        for (int i = 0; i < dataEntities.size() - 1; i++) {
            str = str + dataEntities.get(i).getId() + ",";
        }
        str = str + dataEntities.get(dataEntities.size() - 1);
        return str;
    }

    /**
     * 根据城市的名字获取站点数据
     * @param context
     * @param name
     * @return
     */
    public static SiteBean.DataEntity getSiteByCityName(Context context, String name) {
        CityBean cityBean = CityUtil.getCityByName(context, name);
        if (cityBean != null) {
            return getCloseSite(context, new LatLng(Double.parseDouble(cityBean.getLat()), Double.parseDouble(cityBean.getLng())));
        }
        return null;
    }

    /**
     * 根据城市id拿到城市名字
     * @param context
     * @param cityId
     * @return
     */
    public static SiteBean.DataEntity getSiteByCityId(Context context,String cityId){
        CityBean city = CityUtil.getCityById(context, cityId);
        if(city!=null){
            return SiteUtil.getCloseSite(context,new LatLng(Double.parseDouble(city.getLat()), Double.parseDouble(city.getLng())));
        }
        return null;
    }

}
