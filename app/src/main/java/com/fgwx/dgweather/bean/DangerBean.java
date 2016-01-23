package com.fgwx.dgweather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AllenHu on 2016/1/22.
 *
 * 易灾点
 */
public class DangerBean implements Serializable{
    private String address;
    private String areaCode;
    private String areaName;
    private int aveWaterDepth;
    private String belongUnit;
    private String _class;
    private String conDutyPhone;
    private String conMobilePhone;
    private String contactNumber;
    private String contacter;
    private long createTime;
    private String creator;
    private int curMaxRainfall;
    private String danPerson;
    private String dataSource;
    private String delFlag;
    private String desCharacter;
    private String desInfo;
    private String desLinkSet;
    private String desType;
    private String description;
    private String detWay;
    private String dutyPhone;
    private String etd1;
    private String etd2;
    private String etd3;
    private String governAction;
    private String id;
    private double latitude;
    private String level;
    private double longitude;
    private int maxWaterDepth;
    private String modifier;
    private long modifyTime;
    private String name;
    private String pic;
    private String range;
    private String remarks;
    private String sort;
    private String takeAction;
    private String traffic;
    private String type;
    private int version;
    private int waterDischarge;
    /**
     * _class : InfoDangerFileModel
     * createTime : 1452849144000
     * creator : null
     * delFlag : 0
     * etd1 : null
     * etd2 : null
     * etd3 : null
     * extName : 鐖辩殑
     * filename : 鏄潪娉�
     * id : 1
     * main : 10
     * modifier : null
     * modifyTime : 1452849214000
     * phyPath : null
     * realFilename :
     * relPath : null
     * remarks : null
     * size : null
     * sort : 10001
     * type :
     * uploadTime : null
     * version : 0
     */

    private List<FilesEntity> files;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setAveWaterDepth(int aveWaterDepth) {
        this.aveWaterDepth = aveWaterDepth;
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

    public void setCurMaxRainfall(int curMaxRainfall) {
        this.curMaxRainfall = curMaxRainfall;
    }

    public void setDanPerson(String danPerson) {
        this.danPerson = danPerson;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setDesCharacter(String desCharacter) {
        this.desCharacter = desCharacter;
    }

    public void setDesInfo(String desInfo) {
        this.desInfo = desInfo;
    }

    public void setDesLinkSet(String desLinkSet) {
        this.desLinkSet = desLinkSet;
    }

    public void setDesType(String desType) {
        this.desType = desType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetWay(String detWay) {
        this.detWay = detWay;
    }

    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
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

    public void setGovernAction(String governAction) {
        this.governAction = governAction;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setMaxWaterDepth(int maxWaterDepth) {
        this.maxWaterDepth = maxWaterDepth;
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

    public void setRange(String range) {
        this.range = range;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setTakeAction(String takeAction) {
        this.takeAction = takeAction;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setWaterDischarge(int waterDischarge) {
        this.waterDischarge = waterDischarge;
    }

    public void setFiles(List<FilesEntity> files) {
        this.files = files;
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

    public int getAveWaterDepth() {
        return aveWaterDepth;
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

    public int getCurMaxRainfall() {
        return curMaxRainfall;
    }

    public String getDanPerson() {
        return danPerson;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getDesCharacter() {
        return desCharacter;
    }

    public String getDesInfo() {
        return desInfo;
    }

    public String getDesLinkSet() {
        return desLinkSet;
    }

    public String getDesType() {
        return desType;
    }

    public String getDescription() {
        return description;
    }

    public String getDetWay() {
        return detWay;
    }

    public String getDutyPhone() {
        return dutyPhone;
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

    public String getGovernAction() {
        return governAction;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLevel() {
        return level;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getMaxWaterDepth() {
        return maxWaterDepth;
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

    public String getRange() {
        return range;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getSort() {
        return sort;
    }

    public String getTakeAction() {
        return takeAction;
    }

    public String getTraffic() {
        return traffic;
    }

    public String getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public int getWaterDischarge() {
        return waterDischarge;
    }

    public List<FilesEntity> getFiles() {
        return files;
    }

    public static class FilesEntity {
        private String _class;
        private long createTime;
        private String creator;
        private String delFlag;
        private String etd1;
        private String etd2;
        private String etd3;
        private String extName;
        private String filename;
        private String id;
        private String main;
        private String modifier;
        private long modifyTime;
        private String phyPath;
        private String realFilename;
        private String relPath;
        private String remarks;
        private String size;
        private String sort;
        private String type;
        private String uploadTime;
        private int version;

        public void set_class(String _class) {
            this._class = _class;
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

        public void setEtd1(String etd1) {
            this.etd1 = etd1;
        }

        public void setEtd2(String etd2) {
            this.etd2 = etd2;
        }

        public void setEtd3(String etd3) {
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

        public void setModifier(String modifier) {
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

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUploadTime(String uploadTime) {
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

        public String getCreator() {
            return creator;
        }

        public String getDelFlag() {
            return delFlag;
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

        public String getModifier() {
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

        public String getRemarks() {
            return remarks;
        }

        public String getSize() {
            return size;
        }

        public String getSort() {
            return sort;
        }

        public String getType() {
            return type;
        }

        public String getUploadTime() {
            return uploadTime;
        }

        public int getVersion() {
            return version;
        }
    }
}
