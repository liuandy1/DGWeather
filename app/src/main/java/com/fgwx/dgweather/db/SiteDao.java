package com.fgwx.dgweather.db;

import android.content.Context;

import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.utils.LogUtil;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 作者：燕青 $ on 16/1/10 15:19
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class SiteDao {
    private Context context;
    private Dao<SiteBean.DataEntity, Integer> siteDaoOpe;
    private DatabaseHelper helper;

    public SiteDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            siteDaoOpe = helper.getDao(SiteBean.DataEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个站点
     *
     * @param dataEntity
     */
    public void add(SiteBean.DataEntity dataEntity) {
        try {
            siteDaoOpe.create(dataEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有站点
     */
    public List<SiteBean.DataEntity> getAllSite() {
        List<SiteBean.DataEntity> list = null;
        try {
            list = siteDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtil.e("查询所有的站点数据失败-->getAllSite" + e.toString());
        }
        return list;
    }

    /**
     * 根据站点id获取站点
     *
     * @param code 站点id
     * @return
     */
    public List<SiteBean.DataEntity> getSiteBycode(String code) {
        List<SiteBean.DataEntity> list = null;
        try {
            list = siteDaoOpe.queryForEq("_id",code);
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtil.e("根据站点id获取站点失败-->getSiteBycode" + e.toString());
        }
        return list;
    }


}
