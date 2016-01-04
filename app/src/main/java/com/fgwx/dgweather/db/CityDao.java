package com.fgwx.dgweather.db;

import android.content.Context;

import com.fgwx.dgweather.bean.CityBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 作者：燕青 $ on 16/1/3 16:56
 * 邮箱：359222347@qq.com
 * <p/>
 * 城市表的数据库的操作
 */
public class CityDao {
    private Context context;
    private Dao<CityBean, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public CityDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(CityBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个城市
     *
     * @param city
     */
    public void add(CityBean city) {
        try {
            userDaoOpe.create(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取东莞本地的城市
     * @return
     */
    public List<CityBean> getLocalCity() {
        try {
            List<CityBean> citys = userDaoOpe.queryBuilder().where().eq("city_local", true).query();
//            List<CityBean> citys = userDaoOpe.queryForAll();

            return citys;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}