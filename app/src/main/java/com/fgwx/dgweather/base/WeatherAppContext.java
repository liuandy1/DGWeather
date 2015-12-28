package com.fgwx.dgweather.base;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
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
        SDKInitializer.initialize(this);
        initImageLoader();


        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        SDKReceiver mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
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

    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {

            Log.d("wuzi", "收到了广播");

            String s = intent.getAction();
            Log.d("wuzi", "action: " + s);
//        TextView text = (TextView) findViewById(R.id.text_Info);
//        text.setTextColor(Color.RED);
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                Log.i("wuzi", "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                Log.i("wuzi", "key 验证成功! 功能可以正常使用");
//            text.setTextColor(Color.YELLOW);
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                Log.i("wuzi", "网络出错");
            }
        }
    }
}
