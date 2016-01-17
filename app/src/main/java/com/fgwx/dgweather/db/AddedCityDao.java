package com.fgwx.dgweather.db;

import android.content.Context;

import com.fgwx.dgweather.bean.AddedCityBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 作者：燕青 $ on 16/1/3 16:56
 * 邮箱：359222347@qq.com
 * <p/>
 * 城市表的数据库的操作
 */
public class AddedCityDao {
    private Context context;
    private Dao<AddedCityBean, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public AddedCityDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(AddedCityBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个城市
     *
     * @param city
     */
    public void add(AddedCityBean city) {
        try {
            userDaoOpe.createOrUpdate(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取东莞本地的城市
     *
     * @return
     */
    public List<AddedCityBean> getLocalCity() {
        try {
            List<AddedCityBean> citys = userDaoOpe.queryBuilder().where().eq("city_local", true).query();

            return citys;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有的城市
     */
    public List<AddedCityBean> getAllCity() {
        List<AddedCityBean> list = null;
        try {
            list = userDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据城市名获取城市
     *
     * @param name
     * @return
     */
    public List<AddedCityBean> getCityByName(String name) {
        List<AddedCityBean> list = null;
        try {
            list = userDaoOpe.queryBuilder().where().eq("name", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据关键字来找城市
     *
     * @param keyWords
     * @return
     */
    public List<AddedCityBean> getCityByKeyWords(String keyWords) {
        List<AddedCityBean> list = null;
        if (keyWords == null || "".equals(keyWords)) {
            return null;
        }
        try {
            list = userDaoOpe.queryBuilder().where().like("comment", "%" + keyWords + "%").or().like("name", "%" + keyWords + "%").query();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().length() > 6) {
                    list.get(i).setName(list.get(i).getName() + "，东莞市，广东省");
                } else if (!"100000".equals(list.get(i).getParentCode())) {
                    List<AddedCityBean> provinceName = userDaoOpe.queryBuilder().where().eq("_id", list.get(i).getParentCode()).query();
                    list.get(i).setName(list.get(i).getName() + "，" + provinceName.get(0).getName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}