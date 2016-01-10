package com.fgwx.dgweather.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * 作者：燕青 $ on 16/1/3 16:47
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
@DatabaseTable(tableName = "tb_city")
public class CityBean {

    @DatabaseField(generatedId = true, columnName = "main_id", canBeNull = false)
    private int mainId;
    @DatabaseField(columnName = "class_name", canBeNull = true)
    private String _class;
    @DatabaseField(columnName = "comment", canBeNull = true)
    private String comment;
    @DatabaseField(columnName = "createTime", canBeNull = true)
    private long createTime;
    @DatabaseField(columnName = "creator", canBeNull = true)
    private String creator;
    @DatabaseField(columnName = "delFlag", canBeNull = true)
    private String delFlag;
    @DatabaseField(columnName = "description", canBeNull = true)
    private String description;
    @DatabaseField(columnName = "edt1", canBeNull = true)
    private String etd1;
    @DatabaseField(columnName = "edt2", canBeNull = true)
    private String etd2;
    @DatabaseField(columnName = "edt3", canBeNull = true)
    private String etd3;
    @DatabaseField(columnName = "hotSort", canBeNull = true)
    private String hotSort;
    @DatabaseField(columnName = "_id", canBeNull = true)
    private String id;
    @DatabaseField(columnName = "isHot", canBeNull = false)
    private int isHot;
    @DatabaseField(columnName = "isLocal", canBeNull = false)
    private int isLocal;
    @DatabaseField(columnName = "keywork", canBeNull = true)
    private String keyword;
    @DatabaseField(columnName = "lat", canBeNull = false)
    private String lat;
    @DatabaseField(columnName = "lng", canBeNull = false)
    private String lng;
    @DatabaseField(columnName = "longCode", canBeNull = true)
    private String longCode;
    @DatabaseField(columnName = "mapLevel", canBeNull = true)
    private int mapLevel;
    @DatabaseField(columnName = "modifier", canBeNull = true)
    private String modifier;
    @DatabaseField(columnName = "modifyTime", canBeNull = true)
    private long modifyTime;
    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;
    @DatabaseField(columnName = "parentCode", canBeNull = true)
    private String parentCode;
    @DatabaseField(columnName = "remarks", canBeNull = true)
    private String remarks;
    @DatabaseField(columnName = "sort", canBeNull = true)
    private String sort;
    @DatabaseField(columnName = "version", canBeNull = true)
    private int version;

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtd1(String etd1) {
        this.etd1 = etd1;
    }

    public void setEtd2(String etd2) {
        this.etd2 = etd2;
    }

    public void setEtd3(String etd3) {
        this.etd3 = etd3;
    }

    public void setHotSort(String hotSort) {
        this.hotSort = hotSort;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public void setIsLocal(int isLocal) {
        this.isLocal = isLocal;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLongCode(String longCode) {
        this.longCode = longCode;
    }

    public void setMapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String get_class() {
        return _class;
    }

    public String getComment() {
        return comment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getCreator() {
        return creator;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getDescription() {
        return description;
    }

    public String getEtd1() {
        return etd1;
    }

    public String getEtd2() {
        return etd2;
    }

    public String getEtd3() {
        return etd3;
    }

    public String getHotSort() {
        return hotSort;
    }

    public String getId() {
        return id;
    }

    public int getIsHot() {
        return isHot;
    }

    public int getIsLocal() {
        return isLocal;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getLongCode() {
        return longCode;
    }

    public int getMapLevel() {
        return mapLevel;
    }

    public String getModifier() {
        return modifier;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public String getName() {
        return name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getSort() {
        return sort;
    }

    public int getVersion() {
        return version;
    }

    public CityBean() {
    }
}
