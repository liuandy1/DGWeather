package com.fgwx.dgweather.base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherAppContext extends Application {

    private static WeatherAppContext sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initImageLoader();
    }


    private void initImageLoader() {
    ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public static Context getAppContext(){
        return sInstance;
    }
}
