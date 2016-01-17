package com.fgwx.dgweather.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.CityManagerActivity;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.MyPagerAdapter;
import com.fgwx.dgweather.base.HomeCallBack;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.bean.ForecastMonitorSiteBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.bean.HomeForecastBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.utils.CityUtil;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.fgwx.dgweather.utils.NetWorkUtil;
import com.fgwx.dgweather.utils.SiteUtil;
import com.fgwx.dgweather.utils.SpeechUtil;
import com.fgwx.dgweather.utils.TimeUtil;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;

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
    private TextView tvShowTime;
    private TextView tvCurrentTemp;
    private TextView tvTempRange;
    private TextView tvHumidy;
    private TextView tvWeatherDes;
    private TextView tvWind;
    private TextView tvFreshTime;
    private RelativeLayout rvLocal;
    private ImageView ivAirQuality;
    private TextView tvFail;
    private View siteView;
    private View weatherView;

    private static LatLng mCurrentLng;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private boolean isFirstLoc = true;
    private boolean isSetLoc = false;
    private MapStatusUpdate mMapStatusUpdate;
    private GeoCoder mSearch;

    /**
     * 信息预报部分的view
     */
    private View pagerView;

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

    public void setFirstForecastData(HomeForecastBaseBean homeForecastBaseBean) {

        tvShowTime = (TextView) pagerView.findViewById(R.id.tv_info_currentTime);
        tvFail = (TextView) pagerView.findViewById(R.id.tv_home_fail);
        tvShowTime.setText(TimeUtil.formatDate1(TimeUtil.longToDate(System.currentTimeMillis())));

        MovingPictureView w1_move1 = new MovingPictureView(getContext(), R.drawable.ic_launcher, -300, 10, 40);
        viewPager.addView(w1_move1);
        new Thread(w1_move1).start();

        if (homeForecastBaseBean != null) {

            try {
                HomeForecastBean data = homeForecastBaseBean.getData();
                ForecastMonitorSiteBean siteInfo = data.getSite();
                String strTime = siteInfo.getDataTime();
                LogUtil.e(strTime);
                tvCurrentTemp = (TextView) pagerView.findViewById(R.id.tv_info_currentTemp);
                tvCurrentTemp.setText(siteInfo.getAirTemp());

                tvTempRange = (TextView) pagerView.findViewById(R.id.tv_info_tempRange);
                tvTempRange.setText(siteInfo.getMinTemp() + "℃~" + siteInfo.getMaxTemp() + "℃");

                tvFreshTime = (TextView) pagerView.findViewById(R.id.tv_info_refresh);
                tvFreshTime.setText(TimeUtil.formatShortDate(TimeUtil.strToDate(strTime)) + " 发布");

                tvHumidy = (TextView) pagerView.findViewById(R.id.tv_info_humidy);
                tvHumidy.setText(siteInfo.getRelativeWet() + "%");

                tvHumidy = (TextView) pagerView.findViewById(R.id.tv_info_humidy);
                tvHumidy.setText("相对湿度 " + siteInfo.getRelativeWet() + "%");


                tvWeatherDes = (TextView) pagerView.findViewById(R.id.tv_info_weather);
                ForecastForTenDayBean firstDay = data.getDays().get(0);
                tvWeatherDes.setText(firstDay.getWeaDesc());

                tvWind = (TextView) pagerView.findViewById(R.id.tv_info_wind);
                tvWind.setText(siteInfo.getSpeedDir());

                ivAirQuality = (ImageView) pagerView.findViewById(R.id.iv_info_airQuality);
                String qua = data.getLife().getAirQuan();
//                String qua = data.getDays().get(0).getAirQua();
                char c = qua.charAt(qua.length() - 2);
                if (c == '优') {
                    LogUtil.e("空气质量是优");
                    ivAirQuality.setImageResource(R.drawable.icon_air_excellent);
                } else if (c == '良') {
                    ivAirQuality.setImageResource(R.drawable.icon_air_good);
                    LogUtil.e("空气质量是良");
                } else if (c == '差') {
                    ivAirQuality.setImageResource(R.drawable.icon_air_bad);
                    LogUtil.e("空气质量是差");
                }
                tvFail.setVisibility(GONE);
                addOverSite(siteInfo);
            } catch (Exception e) {
                LogUtil.e(e.toString());
                tvFail.setVisibility(VISIBLE);
            }
        } else {
            tvFail.setVisibility(VISIBLE);
        }
    }

    private void addOverSite(ForecastMonitorSiteBean siteInfo) {
        SiteBean.DataEntity siteBean = SiteUtil.getSiteBycode(mMainActivity, siteInfo.getSiteCode());
        LatLng point = new LatLng(Double.parseDouble(siteBean.getLatitude()), Double.parseDouble(siteBean.getLongitude()));
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_map_humidity);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    private void initUi(View view) {
        rvLocal = (RelativeLayout) view.findViewById(R.id.rv_top_local);
        rvLocal.setOnClickListener(this);
        view.findViewById(R.id.iv_home_full).setOnClickListener(this);
        view.findViewById(R.id.iv_home_location).setOnClickListener(this);
        view.findViewById(R.id.ly_home_down).setOnClickListener(this);
        view.findViewById(R.id.iv_home_more).setOnClickListener(this);
        view.findViewById(R.id.ib_home_play).setOnClickListener(this);
        tvHomeCity = (TextView) view.findViewById(R.id.tv_home_city);
        view.findViewById(R.id.ib_home_share).setOnClickListener(this);

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
//        mMapView.getLocationOnScreen();

        mMapStatusUpdate = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        // 定位初始化
        mLocClient = new LocationClient(mMainActivity.getApplication());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);        // 打开gps
        option.setCoorType("bd09ll");   // 设置坐标类型
        option.setScanSpan(3000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        mBaiduMap.setOnMapTouchListener(new MyonTouchListener());
        mBaiduMap.setOnMarkerClickListener(new MyMarkerListener());
    }

    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(mMainActivity);
        pagerView = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        siteView = pagerView.findViewById(R.id.layout_site);
        weatherView = pagerView.findViewById(R.id.ly_home_weather);
        changeView(weatherView,siteView);
//        viewPager.setBackgroundResource(R.drawable.bg_qing);
//        pagerView.setBackgroundResource(R.drawable.bg_qing);
//        pagerView.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_qing));
        pagerView.findViewById(R.id.ly_home_weather).setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_qing));
        View pagerView1 = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        View pagerView2 = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        ArrayList<View> views = new ArrayList<>();
        views.add(pagerView);
        views.add(pagerView1);
        views.add(pagerView2);
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

    public void showPopupwindow(HomeCallBack callBack) {
        MapSettingPopupwindow popupWindown = new MapSettingPopupwindow(mMainActivity);
        popupWindown.showAtLocation(mMainActivity.findViewById(R.id.ll), Gravity.CENTER, 0, 0);
        callBack.callBack(1);
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

    /**
     * 首页上面的显示天气和显示站点的切换
     *
     * @param showView 要显示的view
     * @param hideView 要隐藏的view
     */
    private void changeView(View showView,View hideView){
        showView.setVisibility(VISIBLE);
        hideView.setVisibility(GONE);
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
        MPreferencesUtil.getInstance().setValue(Constant.NOWLOCAL, reverseGeoCodeResult.getAddress());
        String city = addressDetail.city;
        String district = addressDetail.district;
        String street = addressDetail.street;
        //请求网络信息
        if (mCurrentLng != null)
            mMainActivity.getForecastData(CityUtil.getCityByName(mMainActivity, city), SiteUtil.getCloseSite(mMainActivity, mCurrentLng));
            mMainActivity.getSiteMonitorData(null);
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


    /**
     * 带参数的分享
     * area   地区
     * weaDesc   天气描述
     * tempRange   温度范围
     * windDirec   风向
     * windSpeed   风速
     */
    private void showShare(String area, String weaDesc, String tempRange, String windDirec, String windSpeed) {
        ShareSDK.initSDK(mMainActivity);
        cn.sharesdk.onekeyshare.OnekeyShare oks = new cn.sharesdk.onekeyshare.OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
        // oks.setNotification(R.drawable.ic_launcher,
        // getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mMainActivity.getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://mob.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("亲！好的天气带来好的心情！东莞天气提醒您，" + area + "今天的天气是" + weaDesc + "，" + tempRange + "，" + windDirec + "，"
                + windSpeed + "。\n");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这是评论");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mMainActivity.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(mMainActivity);
    }

    /**
     * 分享
     */
    private void showShare() {
        cn.sharesdk.onekeyshare.OnekeyShare oks = new cn.sharesdk.onekeyshare.OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
        // oks.setNotification(R.drawable.ic_launcher,
        // getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mMainActivity.getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mMainActivity.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.baidu.com");

        // 启动分享GUI
        oks.show(mMainActivity);
    }


    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                //定位出错
                //访问缓存数据
                return;
            }
//            mCurrentLng = new LatLng(location.getLatitude(), location.getLongitude());
//            113.796164,23.04701
            mCurrentLng = new LatLng(23.04701, 113.796164);

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


    /**
     * 百度地图触摸事件的监听
     */
    private class MyonTouchListener implements BaiduMap.OnMapTouchListener {

        @Override
        public void onTouch(MotionEvent motionEvent) {

        }
    }

    private class MyMarkerListener implements BaiduMap.OnMarkerClickListener{

        @Override
        public boolean onMarkerClick(Marker marker) {

            changeView(siteView,weatherView);

            return true;
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
                showPopupwindow(new HomeCallBack() {
                    @Override
                    public void callBack(int a) {

                    }
                });
                break;
            case R.id.ib_home_play:
                //判断有没有网络，语音播报需要网络合成
                if (NetWorkUtil.isNetworkAvailable(getContext()))
                    broadWeather("你好啊，今天天气不错！");
                else
                    Toast.makeText(getContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rv_top_local:
                Intent intent = new Intent(mMainActivity, CityManagerActivity.class);
                mMainActivity.startActivity(intent);
                break;

            case R.id.ib_home_share:
                showShare("东莞", "多云转晴", "21~30度", "东南风", "3~4级");
                break;

            case R.id.tv_info_refresh:
                Toast.makeText(mMainActivity, "刷新天气", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
