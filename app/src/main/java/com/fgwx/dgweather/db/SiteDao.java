package com.fgwx.dgweather.db;

import android.content.Context;

import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.SiteBean;
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

}
