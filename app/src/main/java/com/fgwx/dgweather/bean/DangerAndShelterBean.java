package com.fgwx.dgweather.bean;

import java.util.List;

/**
 * Created by AllenHu on 2016/1/22.
 */
public class DangerAndShelterBean {

    private int code;
    private String msg;
    /**
     * address : 骞夸笢鐪佷笢鑾炲競鏉炬邯璺�130鍙�
     * areaCode : null
     * areaName : null
     * belongUnit : 姣斿崕鍒╁北搴�
     * _class : InfoShelterModel
     * conDutyPhone :
     * conMobilePhone :
     * contactNumber :
     * contacter :
     * createTime : 1452216656000
     * creator :
     * dataSource : 鍏朵粬
     * delFlag :
     * description : 骞夸笢鐪佷笢鑾炲競涓滆帪甯傛澗婧矾126鍙烽檮杩�
     * dutyPhone : 0769-83302919
     * etd1 : null
     * etd2 : null
     * etd3 : null
     * files : [{"_class":"InfoShelterFileModel","createTime":1452916572000,"creator":null,"delFlag":"0","etd1":null,"etd2":null,"etd3":null,"extName":".jpg","filename":"ss","id":"1","main":"1","modifier":null,"modifyTime":1452916572000,"phyPath":"upfile/ss","realFilename":"sss","relPath":"upfile/ss","remarks":null,"size":null,"sort":"10001","type":null,"uploadTime":null,"version":0}]
     * id : 1
     * latitude : 23.026055
     * longitude : 113.827336
     * modifier : null
     * modifyTime : 1452915567000
     * name : 姣斿崕鍒╁北搴勮タ鍖�-鍋滆溅鍦�
     * pic : 23.026055
     * remarks :
     * sort :
     * type : 閬块毦鍦烘墍
     * version : 0
     */

    private List<ShelterBean> shelters;
    /**
     * address :
     * areaCode : null
     * areaName : null
     * aveWaterDepth : 80
     * belongUnit : 闈掔殗姣斿畨杈惧巶
     * _class : InfoDangerModel
     * conDutyPhone : null
     * conMobilePhone : null
     * contactNumber : null
     * contacter : null
     * createTime : 1452159934000
     * creator : null
     * curMaxRainfall : 190
     * danPerson : null
     * dataSource :
     * delFlag : 0
     * desCharacter : null
     * desInfo : null
     * desLinkSet : null
     * desType : null
     * description :
     * detWay : null
     * dutyPhone : 0769-85916406
     * etd1 : null
     * etd2 : null
     * etd3 : null
     * files : [{"_class":"InfoDangerFileModel","createTime":1452849144000,"creator":null,"delFlag":"0","etd1":null,"etd2":null,"etd3":null,"extName":"鐖辩殑","filename":"鏄潪娉�","id":"1","main":"10","modifier":null,"modifyTime":1452849214000,"phyPath":null,"realFilename":"","relPath":null,"remarks":null,"size":null,"sort":"10001","type":"","uploadTime":null,"version":0}]
     * governAction : null
     * id : 10
     * latitude : 22.821466
     * level : null
     * longitude : 113.674594
     * maxWaterDepth : 100
     * modifier : null
     * modifyTime : 1452852716000
     * name : 铏庨棬闀囬緳鐪兼潙
     * pic : 113.674594
     * range : awqeqe
     * remarks : null
     * sort : 10001
     * takeAction : null
     * traffic : null
     * type : 娲按鐏惧椋庨櫓鍖�
     * version : 0
     * waterDischarge : 400
     */

    private List<DangerBean> dangers;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setShelters(List<ShelterBean> shelters) {
        this.shelters = shelters;
    }

    public void setDangers(List<DangerBean> dangers) {
        this.dangers = dangers;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<ShelterBean> getShelters() {
        return shelters;
    }

    public List<DangerBean> getDangers() {
        return dangers;
    }


}
