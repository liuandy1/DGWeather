package com.fgwx.dgweather.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.fgwx.dgweather.bean.BaseCityBean;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.db.CityDao;
import com.fgwx.dgweather.db.SiteDao;
import com.fgwx.dgweather.net.VolleySingleton;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechUtility;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.apache.http.util.EncodingUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;


/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherAppContext extends Application {

    private static WeatherAppContext sInstance;
    private MPreferencesUtil preferencesUtil;
    public String LocalStr;
    public static boolean isWeather = true;
    public String currentCityId;
    public HomeForecastBaseBean homeForecastBaseBean;
    public static int nowPager = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        VolleySingleton.init(this);
        SDKInitializer.initialize(this);
        ShareSDK.initSDK(this);
        initImageLoader();
        initSpeech();
        preferencesUtil = MPreferencesUtil.getInstance();

        if (-1 == preferencesUtil.getValue(Constant.SITESVAED, -1)) {
            saveSite();
            LogUtil.e("站点数据保存成功");
        }
        if (-1 == preferencesUtil.getValue(Constant.CITYSAVED, -1)) {
            saveCity();
        }
    }

    private void saveSite() {
        String siteFile = "getSites.json";
        SiteDao dao = new SiteDao(this);
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/" + siteFile);
            int length = 0;
            length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            String res = EncodingUtils.getString(buffer, "UTF-8");
            Gson gson = new Gson();
            SiteBean site = gson.fromJson(res, SiteBean.class);
            List<SiteBean.DataEntity> siteList = site.getData();
            for (SiteBean.DataEntity dataEntity : siteList) {
                dao.add(dataEntity);
            }
            preferencesUtil.setValue(Constant.SITESVAED, 1);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e(e.toString());
        }
    }

    private void initSpeech() {
        SpeechUtility.createUtility(WeatherAppContext.this, "appid=568b8dae");
    }

    private void saveCity() {
        String siteFile = "getAreas.json";
        CityDao dao = new CityDao(this);
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/" + siteFile);
            int length = 0;
            length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            String res = EncodingUtils.getString(buffer, "UTF-8");
            Gson gson = new Gson();
            BaseCityBean city = gson.fromJson(res, BaseCityBean.class);
            List<CityBean> cityBeans = city.getData();
            for (CityBean city1 : cityBeans) {
                dao.add(city1);
            }
            preferencesUtil.setValue(Constant.CITYSAVED, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initImageLoader() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public static Context getAppContext() {
        return sInstance;
    }

    public String getLocalStr() {
        return LocalStr;
    }

    public void setLocalStr(String localStr) {
        LocalStr = localStr;
    }

    public String getCurrentCityId() {
        return currentCityId;
    }

    public void setCurrentCityId(String currentCityId) {
        this.currentCityId = currentCityId;
    }

    public HomeForecastBaseBean getHomeForecastBaseBean() {
        return homeForecastBaseBean;
    }

    public void setHomeForecastBaseBean(HomeForecastBaseBean homeForecastBaseBean) {
        this.homeForecastBaseBean = homeForecastBaseBean;
    }
}
