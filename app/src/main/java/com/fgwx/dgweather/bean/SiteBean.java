package com.fgwx.dgweather.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * 作者：燕青 $ on 16/1/10 15:03
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */

public class SiteBean {

    private int code;
    private String msg;

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    @DatabaseTable(tableName = "tb_site")
    public static class DataEntity {
        @DatabaseField(generatedId = true, columnName = "main_id", canBeNull = false)
        private int mainId;

        @DatabaseField(columnName = "address", canBeNull = true)
        private String address;
        @DatabaseField(columnName = "areaCode", canBeNull = true)
        private String areaCode;

        @DatabaseField(columnName = "areaName", canBeNull = true)
        private String areaName;
        @DatabaseField(columnName = "class_name", canBeNull = true)
        private String _class;
        @DatabaseField(columnName = "contactNumber", canBeNull = true)
        private String contactNumber;
        @DatabaseField(columnName = "createTime", canBeNull = true)
        private long createTime;
        @DatabaseField(columnName = "creator", canBeNull = true)
        private String creator;
        @DatabaseField(columnName = "delFlag", canBeNull = true)
        private String delFlag;
        @DatabaseField(columnName = "description", canBeNull = true)
        private String description;
        @DatabaseField(columnName = "etd1", canBeNull = true)
        private String etd1;
        @DatabaseField(columnName = "etd2", canBeNull = true)
        private String etd2;
        @DatabaseField(columnName = "etd3", canBeNull = true)
        private String etd3;

        /**
         * 请求服务器时候需要的id
         */
        @DatabaseField(columnName = "_id", canBeNull = true)
        private String id;
        @DatabaseField(columnName = "isAssess", canBeNull = true)
        private int isAssess;
        @DatabaseField(columnName = "isIndex", canBeNull = true)
        private int isIndex;
        @DatabaseField(columnName = "latitude", canBeNull = false)
        private String latitude;
        @DatabaseField(columnName = "longitude", canBeNull = false)
        private String longitude;
        @DatabaseField(columnName = "modifier", canBeNull = true)
        private String modifier;
        @DatabaseField(columnName = "modifyTime", canBeNull = true)
        private long modifyTime;
        @DatabaseField(columnName = "name", canBeNull = true)
        private String name;
        @DatabaseField(columnName = "pic", canBeNull = true)
        private String pic;
        @DatabaseField(columnName = "remarks", canBeNull = true)
        private String remarks;
        @DatabaseField(columnName = "siteType", canBeNull = true)
        private String siteType;
        @DatabaseField(columnName = "sort", canBeNull = true)
        private String sort;
        @DatabaseField(columnName = "type", canBeNull = true)
        private String type;
        @DatabaseField(columnName = "version", canBeNull = true)
        private int version;

        public int getMainId() {
            return mainId;
        }

        public void setMainId(int mainId) {
            this.mainId = mainId;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public void set_class(String _class) {
            this._class = _class;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
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

        public void setId(String id) {
            this.id = id;
        }

        public void setIsAssess(int isAssess) {
            this.isAssess = isAssess;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
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

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setSiteType(String siteType) {
            this.siteType = siteType;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getAddress() {
            return address;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public String get_class() {
            return _class;
        }

        public String getContactNumber() {
            return contactNumber;
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

        public String getId() {
            return id;
        }

        public int getIsAssess() {
            return isAssess;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
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

        public String getPic() {
            return pic;
        }

        public String getRemarks() {
            return remarks;
        }

        public String getSiteType() {
            return siteType;
        }

        public String getSort() {
            return sort;
        }

        public String getType() {
            return type;
        }

        public int getVersion() {
            return version;
        }
    }
}
