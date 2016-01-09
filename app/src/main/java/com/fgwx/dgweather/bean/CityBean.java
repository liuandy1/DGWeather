package com.fgwx.dgweather.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 作者：燕青 $ on 16/1/3 16:47
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
@DatabaseTable(tableName = "tb_city")
public class CityBean {

    @DatabaseField(generatedId = true, columnName = "main_id")
    private int id;

    //canBeNull = true
    @DatabaseField(columnName = "city_name")
    private String cityName;

    @DatabaseField(columnName = "city_local")
    private boolean isLocal;
    /**
     * 行政编号
     */
    @DatabaseField(columnName = "city_no")
    private String cityNo;

    public CityBean() {
    }

    public CityBean(String cityName, boolean isLocal, String cityNo) {
        this.cityName = cityName;
        this.isLocal = isLocal;
        this.cityNo = cityNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }
}
