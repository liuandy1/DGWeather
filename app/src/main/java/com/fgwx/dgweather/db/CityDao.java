package com.fgwx.dgweather.db;

import android.content.Context;

import com.fgwx.dgweather.bean.City;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * 作者：燕青 $ on 16/1/3 16:56
 * 邮箱：359222347@qq.com
 * <p/>
 * 城市表的数据库的操作
 */
public class CityDao {
    private Context context;
    private Dao<City, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public CityDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(City.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个城市
     *
     * @param city
     */
    public void add(City city) {
        try {
            userDaoOpe.create(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//...other operations

}