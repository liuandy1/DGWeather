package com.fgwx.dgweather.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.CityDao;
import com.fgwx.dgweather.net.VolleySingleton;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


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
        preferencesUtil = MPreferencesUtil.getInstance(this);
        if (-1 == preferencesUtil.getValue(Constant.ISFIRST, -1)) {
            saveCity();
        }

    }

    private void saveCity() {
        String[] citys = Constant.CITYNAME.replace("\n", "").split("qq");
        LogUtil.e("citys:"+citys.length);
        String[] cityNos = Constant.CITYNO.replace("\n", "").split("qq");
        LogUtil.e("cityNos:"+cityNos.length);
        CityDao cityDao = new CityDao(this);
        for (int i = 0; i < citys.length; i++) {
            if (cityNos[i].length() == 12) {
                CityBean c = new CityBean(citys[i], true, cityNos[i]);
                cityDao.add(c);
            }else{
                CityBean c = new CityBean(citys[i], false, cityNos[i]);
                cityDao.add(c);
            }
        }
        preferencesUtil.setValue(Constant.ISFIRST, 1);
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
