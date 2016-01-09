package com.fgwx.dgweather.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.SDKInitializer;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.db.CityDao;
import com.fgwx.dgweather.net.VolleySingleton;
import com.fgwx.dgweather.service.WeatherService;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.iflytek.cloud.SpeechUtility;
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
        initSpeech();

        preferencesUtil = MPreferencesUtil.getInstance(this);
        if (-1 == preferencesUtil.getValue(Constant.ISFIRST, -1)) {
            saveCity();
        }
        Intent service = new Intent(this, WeatherService.class);
        startService(service);
    }

    private void initSpeech() {
        // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
        // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
        // 参数间使用半角“,”分隔。
        // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符

        // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误

        SpeechUtility.createUtility(WeatherAppContext.this, "appid=568b8dae");

        // 以下语句用于设置日志开关（默认开启），设置成false时关闭语音云SDK日志打印
        // Setting.setShowLog(false);
    }

    private void saveCity() {
        String[] citys = Constant.CITYNAME.replace("\n", "").split("qq");
        LogUtil.e("citys:" + citys.length);
        String[] cityNos = Constant.CITYNO.replace("\n", "").split("qq");
        LogUtil.e("cityNos:" + cityNos.length);
        CityDao cityDao = new CityDao(this);
        for (int i = 0; i < citys.length; i++) {
            if (cityNos[i].length() == 12) {
                CityBean c = new CityBean(citys[i], true, cityNos[i]);
                cityDao.add(c);
            } else {
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
