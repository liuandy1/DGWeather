package com.fgwx.dgweather.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.fgwx.dgweather.bean.BaseCityBean;
import com.fgwx.dgweather.bean.CityBean;
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


/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherAppContext extends Application {

    private static WeatherAppContext sInstance;
    private MPreferencesUtil preferencesUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        VolleySingleton.init(this);
        SDKInitializer.initialize(this);
        initImageLoader();
        initSpeech();
        preferencesUtil = MPreferencesUtil.getInstance();

        if (-1 == preferencesUtil.getValue(Constant.SITESVAED, -1)) {
            saveSite();
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

}
