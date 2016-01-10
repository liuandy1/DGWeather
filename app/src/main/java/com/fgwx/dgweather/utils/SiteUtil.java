package com.fgwx.dgweather.utils;

import android.content.Context;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.db.SiteDao;
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
     * @param context
     * @param p1  当前的位置
     * @return
     */
    public static SiteBean.DataEntity getCloseSite(Context context, LatLng p1) {
        SiteDao siteDao = new SiteDao(context);
        SiteBean.DataEntity closeSite = null;
        List<SiteBean.DataEntity> list = siteDao.getAddSite();
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
     *
     * @param context
     * @param lat
     * @param lon
     * @return
     */
    public static SiteBean.DataEntity getCloseSite(Context context, float lat,float lon) {
        SiteDao siteDao = new SiteDao(context);
        LatLng p1 = new LatLng(lat, lon);
        return  getCloseSite(context,p1);
    }
}
