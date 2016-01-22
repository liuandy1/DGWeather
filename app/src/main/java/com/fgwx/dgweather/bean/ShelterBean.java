package com.fgwx.dgweather.bean;

import java.util.List;

/**
 * Created by AllenHu on 2016/1/22.
 *
 * 避难所
 */
public class ShelterBean {

    private String address;
    private Object areaCode;
    private Object areaName;
    private String belongUnit;
    private String _class;
    private String conDutyPhone;
    private String conMobilePhone;
    private String contactNumber;
    private String contacter;
    private long createTime;
    private String creator;
    private String dataSource;
    private String delFlag;
    private String description;
    private String dutyPhone;
    private Object etd1;
    private Object etd2;
    private Object etd3;
    private String id;
    private double latitude;
    private double longitude;
    private Object modifier;
    private long modifyTime;
    private String name;
    private String pic;
    private String remarks;
    private String sort;
    private String type;
    private int version;
    /**
     * _class : InfoShelterFileModel
     * createTime : 1452916572000
     * creator : null
     * delFlag : 0
     * etd1 : null
     * etd2 : null
     * etd3 : null
     * extName : .jpg
     * filename : ss
     * id : 1
     * main : 1
     * modifier : null
     * modifyTime : 1452916572000
     * phyPath : upfile/ss
     * realFilename : sss
     * relPath : upfile/ss
     * remarks : null
     * size : null
     * sort : 10001
     * type : null
     * uploadTime : null
     * version : 0
     */

    private List<FilesEntity> files;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAreaCode(Object areaCode) {
        this.areaCode = areaCode;
    }

    public void setAreaName(Object areaName) {
        this.areaName = areaName;
    }

    public void setBelongUnit(String belongUnit) {
        this.belongUnit = belongUnit;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void setConDutyPhone(String conDutyPhone) {
        this.conDutyPhone = conDutyPhone;
    }

    public void setConMobilePhone(String conMobilePhone) {
        this.conMobilePhone = conMobilePhone;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
    }

    public void setEtd1(Object etd1) {
        this.etd1 = etd1;
    }

    public void setEtd2(Object etd2) {
        this.etd2 = etd2;
    }

    public void setEtd3(Object etd3) {
        this.etd3 = etd3;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setModifier(Object modifier) {
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

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setFiles(List<FilesEntity> files) {
        this.files = files;
    }

    public String getAddress() {
        return address;
    }

    public Object getAreaCode() {
        return areaCode;
    }

    public Object getAreaName() {
        return areaName;
    }

    public String getBelongUnit() {
        return belongUnit;
    }

    public String get_class() {
        return _class;
    }

    public String getConDutyPhone() {
        return conDutyPhone;
    }

    public String getConMobilePhone() {
        return conMobilePhone;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContacter() {
        return contacter;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getCreator() {
        return creator;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getDescription() {
        return description;
    }

    public String getDutyPhone() {
        return dutyPhone;
    }

    public Object getEtd1() {
        return etd1;
    }

    public Object getEtd2() {
        return etd2;
    }

    public Object getEtd3() {
        return etd3;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Object getModifier() {
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

    public String getSort() {
        return sort;
    }

    public String getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public List<FilesEntity> getFiles() {
        return files;
    }

    public static class FilesEntity {
        private String _class;
        private long createTime;
        private Object creator;
        private String delFlag;
        private Object etd1;
        private Object etd2;
        private Object etd3;
        private String extName;
        private String filename;
        private String id;
        private String main;
        private Object modifier;
        private long modifyTime;
        private String phyPath;
        private String realFilename;
        private String relPath;
        private Object remarks;
        private Object size;
        private String sort;
        private Object type;
        private Object uploadTime;
        private int version;

        public void set_class(String _class) {
            this._class = _class;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setCreator(Object creator) {
            this.creator = creator;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public void setEtd1(Object etd1) {
            this.etd1 = etd1;
        }

        public void setEtd2(Object etd2) {
            this.etd2 = etd2;
        }

        public void setEtd3(Object etd3) {
            this.etd3 = etd3;
        }

        public void setExtName(String extName) {
            this.extName = extName;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setPhyPath(String phyPath) {
            this.phyPath = phyPath;
        }

        public void setRealFilename(String realFilename) {
            this.realFilename = realFilename;
        }

        public void setRelPath(String relPath) {
            this.relPath = relPath;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public void setSize(Object size) {
            this.size = size;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public void setUploadTime(Object uploadTime) {
            this.uploadTime = uploadTime;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String get_class() {
            return _class;
        }

        public long getCreateTime() {
            return createTime;
        }

        public Object getCreator() {
            return creator;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public Object getEtd1() {
            return etd1;
        }

        public Object getEtd2() {
            return etd2;
        }

        public Object getEtd3() {
            return etd3;
        }

        public String getExtName() {
            return extName;
        }

        public String getFilename() {
            return filename;
        }

        public String getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public Object getModifier() {
            return modifier;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public String getPhyPath() {
            return phyPath;
        }

        public String getRealFilename() {
            return realFilename;
        }

        public String getRelPath() {
            return relPath;
        }

        public Object getRemarks() {
            return remarks;
        }

        public Object getSize() {
            return size;
        }

        public String getSort() {
            return sort;
        }

        public Object getType() {
            return type;
        }

        public Object getUploadTime() {
            return uploadTime;
        }

        public int getVersion() {
            return version;
        }
    }
}
