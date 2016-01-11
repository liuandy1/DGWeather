package com.fgwx.dgweather.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.MyPagerAdapter;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.SpeechUtil;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

import java.util.ArrayList;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastFirstView extends RelativeLayout implements View.OnClickListener, OnGetGeoCoderResultListener {

    private MainActivity mMainActivity;
    public static boolean isFull = false;
    private RelativeLayout rvHomeInfo;
    private LinearLayout lyHomeSearch;
    private LinearLayout pointLayout;
    private int lastPoint_position;
    private ViewPager viewPager;
    private TextView tvHomeCity;

    private static LatLng mCurrentLng;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private boolean isFirstLoc = true;
    private boolean isSetLoc = false;
    private MapStatusUpdate mMapStatusUpdate;
    private GeoCoder mSearch;

    public ForecastFirstView(Context context) {
        this(context, null);
    }

    public ForecastFirstView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForecastFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMainActivity = (MainActivity) context;
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_first_forecast, this);
        initUi(view);
    }

    public void setFirstForecastData(HomeForecastBaseBean homeForecastBaseBean){

    }
    private void initUi(View view) {
        view.findViewById(R.id.iv_home_full).setOnClickListener(this);
        view.findViewById(R.id.iv_home_location).setOnClickListener(this);
        view.findViewById(R.id.ly_home_down).setOnClickListener(this);
        view.findViewById(R.id.iv_home_more).setOnClickListener(this);
        view.findViewById(R.id.ib_home_play).setOnClickListener(this);
        tvHomeCity = (TextView) view.findViewById(R.id.tv_home_city);

        rvHomeInfo = (RelativeLayout) view.findViewById(R.id.rv_home_info);
        lyHomeSearch = (LinearLayout) view.findViewById(R.id.ly_home_search);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_home);
        pointLayout = (LinearLayout) view.findViewById(R.id.ll_home_dots);
        initViewPager();
        initMap(view);
    }

    private void initMap(View view) {
        mMapView = (MapView) view.findViewById(R.id.map_view_home);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
        mMapView.removeViewAt(1);

        mMapStatusUpdate = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        // 定位初始化
        mLocClient = new LocationClient(mMainActivity.getApplication());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);        // 打开gps
        option.setCoorType("bd09ll");   // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(mMainActivity);
        View view1 = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        View view2 = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        View view3 = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new MyPagerAdapter(views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pointLayout.getChildAt(position).setEnabled(true);
                pointLayout.getChildAt(lastPoint_position).setEnabled(false);
                lastPoint_position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addImage();
    }

    /**
     *
     */
    private void broadWeather(String str) {
        SpeechUtil.broadcastWeather(mMainActivity, str, new SynthesizerListener() {
            public AlertDialog dialog;

            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {
                dialog.dismiss();
            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }

    public void showPopupwindow() {
        MapSettingPopupwindow popupWindown = new MapSettingPopupwindow(mMainActivity);
        popupWindown.showAtLocation(mMainActivity.findViewById(R.id.ll), Gravity.CENTER, 0, 0);
    }

    /**
     * 开启或者关闭全屏
     */
    private void fullWindow() {
        LogUtil.e("全屏操作");
        if (!isFull) {
            rvHomeInfo.setVisibility(View.GONE);
            lyHomeSearch.setVisibility(View.VISIBLE);
            isFull = true;
        } else {
            rvHomeInfo.setVisibility(View.VISIBLE);
            lyHomeSearch.setVisibility(View.GONE);
            isFull = false;
        }
    }

    private void addImage() {
        for (int i = 0; i < 3; i++) {
            //添加指示点
            ImageView point = new ImageView(mMainActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.rightMargin = 20;
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointLayout.addView(point);
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(mMainActivity, "抱歉,定位失败", Toast.LENGTH_LONG).show();
            return;
        }
        ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
        tvHomeCity.setText(addressDetail.district);
        isSetLoc = true;

        LogUtil.e("位置是:" + reverseGeoCodeResult.getAddress());
        String city = addressDetail.city;
        String district = addressDetail.district;
        String street = addressDetail.street;
        LogUtil.e(city + "  " + district + "  " + street);
        if ("东莞市".equals(city)) {

        }
    }

    public void onDestroy() {
        mMapView.onDestroy();
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView = null;
    }

    public void onResume() {
        mMapView.onResume();
    }

    public void onPause() {
        mMapView.onPause();
    }

    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                //定位出错
                //访问缓存数据
                return;
            }
            mCurrentLng = new LatLng(location.getLatitude(), location.getLongitude());
//            mCurrentLng = new LatLng(25.938985,113.413253);
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    .direction(100).latitude(mCurrentLng.latitude).longitude(mCurrentLng.longitude).build();
            mBaiduMap.setMyLocationData(locData);

            if (mMapStatusUpdate == null)
                mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(mCurrentLng.latitude, mCurrentLng.longitude);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
            if (!isSetLoc) {
                // 反Geo搜索
                mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(mCurrentLng));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ly_home_down:
                mMainActivity.goSecondPage();
                break;
            case R.id.iv_home_full:
                fullWindow();
                break;
            case R.id.iv_home_location:
                if (mCurrentLng != null) {
                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng);
                    mBaiduMap.setMapStatus(mMapStatusUpdate);
                }
                break;
            case R.id.iv_home_more:
                showPopupwindow();
                break;
            case R.id.ib_home_play:
                broadWeather("你好啊，今天天气不错！");
                break;
        }
    }
}
