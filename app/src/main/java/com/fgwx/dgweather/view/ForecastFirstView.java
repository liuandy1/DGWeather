package com.fgwx.dgweather.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.CityManagerActivity;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.MyPagerAdapter;
import com.fgwx.dgweather.base.HomeCallBack;
import com.fgwx.dgweather.base.WeatherAppContext;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.DangerBean;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.bean.ForecastMonitorSiteBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.bean.HomeForecastBean;
import com.fgwx.dgweather.bean.MapSettingBean;
import com.fgwx.dgweather.bean.ShelterBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.bean.SiteMonitorBaseBean;
import com.fgwx.dgweather.bean.SiteMonitorBean;
import com.fgwx.dgweather.utils.AddedCityUtil;
import com.fgwx.dgweather.utils.AppUtil;
import com.fgwx.dgweather.utils.CityUtil;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.fgwx.dgweather.utils.NetWorkUtil;
import com.fgwx.dgweather.utils.SiteUtil;
import com.fgwx.dgweather.utils.SpeechUtil;
import com.fgwx.dgweather.utils.TimeUtil;
import com.fgwx.dgweather.utils.ToastUtil;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.util.ShareSDKUtil;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastFirstView extends RelativeLayout implements View.OnClickListener, OnGetGeoCoderResultListener {

    private MainActivity mMainActivity;
    public static boolean isFull = false;
    public static int current;
    private int nowPage = 0;
    private RelativeLayout rvHomeInfo;
    private LinearLayout lyHomeSearch;
    private LinearLayout pointLayout;
    private int lastPoint_position = 0;
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
    private View stationView;

    private static LatLng[] mCurrentLng = new LatLng[10];
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private boolean isFirstLoc = true;
    private boolean isSetLoc = false;
    private MapStatusUpdate mMapStatusUpdate;
    private GeoCoder mSearch;
    private HomeForecastBean baseBean;
    private BitmapDescriptor temDescriptor;
    private BitmapDescriptor winDescriptor;
    private BitmapDescriptor humDescriptor;
    private BitmapDescriptor rainDescriptor;

    /**
     * 信息预报部分的view
     */
    private View pagerView;
    private List<SiteMonitorBean> sites;
    private List<DangerBean> dangers;
    private List<ShelterBean> shelters;
    private ArrayList<View> views;
    private String city = "东莞市";
    private String cityId = "0";
    private BitmapDescriptor dangerDescriptor;
    private BitmapDescriptor shelterDescriptor;
    private String nowCity;
    private WeatherAppContext application;

    public ForecastFirstView(Context context) {
        this(context, null);
    }

    public ForecastFirstView(Context context, int nowPage) {
        this(context, null);
        this.nowPage = nowPage;
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

    public void setDangerData(List<DangerBean> dangers) {
        this.dangers = dangers;
        String set = MPreferencesUtil.getInstance().getValue(Constant.MAPSETTING, null);
        if (dangers != null && dangers.size() > 0) {
            if (!TextUtils.isEmpty(set)) {
                MapSettingBean bean = new Gson().fromJson(set, MapSettingBean.class);
                if (bean.isDisasterPoint()) {
                    addOverDanger(dangers);
                }
            }
        }
    }

    public void setShelterData(List<ShelterBean> shelterData) {
        this.shelters = shelterData;

        String set = MPreferencesUtil.getInstance().getValue(Constant.MAPSETTING, null);
        if (shelters != null && shelters.size() > 0) {
            if (!TextUtils.isEmpty(set)) {
                MapSettingBean bean = new Gson().fromJson(set, MapSettingBean.class);
                if (bean.isRefudge()) {
                    addOverShelter(shelters);
                }
            }
        }
    }


    public void setSiteMonitorData(SiteMonitorBaseBean siteMonitorBaseBean) {

        sites = siteMonitorBaseBean.getData();
        String set = MPreferencesUtil.getInstance().getValue(Constant.MAPSETTING, null);
        if (sites != null && sites.size() > 0) {
            if (!TextUtils.isEmpty(set)) {
                MapSettingBean bean = new Gson().fromJson(set, MapSettingBean.class);
                addOverSite(sites, bean.getSiteType());
            } else {
                addOverSite(sites, Constant.TEM);
            }
        }
        mMainActivity.getDangerAndShelterData(CityUtil.getCityByName(mMainActivity, city).getId(), mCurrentLng[0], 0, 0);
    }

    public void setFirstForecastData(HomeForecastBaseBean homeForecastBaseBean) {

        pagerView = views.get(nowPage);
        if (nowPage > 0) {
            CityBean added = AddedCityUtil.getAllCity(mMainActivity).get(nowPage - 1);
            cityId = added.getId();
            city = added.getName();
            mCurrentLng[nowPage] = new LatLng(Double.parseDouble(added.getLat()), Double.parseDouble(added.getLng()));
        } else {
            city = nowCity;
            cityId = "0";
            application = (WeatherAppContext) mMainActivity.getApplication();
            application.setHomeForecastBaseBean(homeForecastBaseBean);
        }
        tvShowTime = (TextView) pagerView.findViewById(R.id.tv_info_currentTime);
        tvShowTime.setText(TimeUtil.formatDate1(TimeUtil.longToDate(System.currentTimeMillis())));

        tvFreshTime = (TextView) pagerView.findViewById(R.id.tv_info_refresh);
        tvFreshTime.setOnClickListener(this);

        siteView = pagerView.findViewById(R.id.layout_site);
        weatherView = pagerView.findViewById(R.id.ly_home_weather);
        stationView = pagerView.findViewById(R.id.layout_station);
        changeView(weatherView, siteView, stationView);
        WeatherAppContext.isWeather = true;
        pagerView.findViewById(R.id.ib_info_warn1).setOnClickListener(this);
        if (homeForecastBaseBean != null) {
            baseBean = homeForecastBaseBean.getData();
            try {
                HomeForecastBean data = homeForecastBaseBean.getData();
                ForecastMonitorSiteBean siteInfo = data.getSite();
                String strTime = siteInfo.getDataTime();
                tvCurrentTemp = (TextView) pagerView.findViewById(R.id.tv_info_currentTemp);
                tvCurrentTemp.setText(siteInfo.getAirTemp());

                tvTempRange = (TextView) pagerView.findViewById(R.id.tv_info_tempRange);
                tvTempRange.setText(siteInfo.getMinTemp() + "℃~" + siteInfo.getMaxTemp() + "℃");

                tvFreshTime.setText(TimeUtil.formatShortDate(TimeUtil.strToDate(strTime)) + " 发布");

                tvHumidy = (TextView) pagerView.findViewById(R.id.tv_info_humidy);
                tvHumidy.setText(siteInfo.getRelativeWet() + "%");

                tvHumidy = (TextView) pagerView.findViewById(R.id.tv_info_humidy);
                tvHumidy.setText("相对湿度 " + siteInfo.getRelativeWet() + "%");


                tvWeatherDes = (TextView) pagerView.findViewById(R.id.tv_info_weather);
                ForecastForTenDayBean firstDay = data.getDays().get(0);
                tvWeatherDes.setText(firstDay.getWeaDesc());
                LogUtil.e("天气图标:"+firstDay.getWeaIcon());
                pagerView.findViewById(R.id.ly_home_weather).setBackgroundDrawable(AppUtil.getWeatherBgById(firstDay.getWeaIcon()));
//                pagerView.findViewById(R.id.ly_home_weather).setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_weather_1));

                tvWind = (TextView) pagerView.findViewById(R.id.tv_info_wind);
                tvWind.setText(siteInfo.getSpeedDir());

                ivAirQuality = (ImageView) pagerView.findViewById(R.id.iv_info_airQuality);
                String qua = data.getLife().getAirQuan();
//                String qua = data.getDays().get(0).getAirQua();
                char c = qua.charAt(qua.length() - 2);
                if (c == '优') {
                    ivAirQuality.setImageResource(R.drawable.icon_air_excellent);
                } else if (c == '良') {
                    ivAirQuality.setImageResource(R.drawable.icon_air_good);
                } else if (c == '差') {
                    ivAirQuality.setImageResource(R.drawable.icon_air_bad);
                }
                tvFail.setVisibility(GONE);
            } catch (Exception e) {
                LogUtil.e(e.toString());
                tvFail.setVisibility(VISIBLE);
            }
        } else {
            tvFail.setVisibility(VISIBLE);
        }
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


        //构建Marker图标
        humDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_humidity);
        temDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_temprature);
        winDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_wind);
        rainDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_rainfall);
        dangerDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_disaster);
        shelterDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_refuge);
        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        mBaiduMap.setOnMapTouchListener(new MyOnTouchListener());
        mBaiduMap.setOnMarkerClickListener(new MyMarkerListener());

    }


    private void addOverSite(List<SiteMonitorBean> list, int i) {
        if (list != null && list.size() > 0) {
            for (SiteMonitorBean siteInfo : list) {
                SiteBean.DataEntity siteBean = SiteUtil.getSiteBycode(mMainActivity, siteInfo.getSiteCode());
                LatLng point = new LatLng(Double.parseDouble(siteBean.getLatitude()), Double.parseDouble(siteBean.getLongitude()));
                MarkerOptions option = null;
                switch (i) {
                    case Constant.TEM:
                        //构建MarkerOption，用于在地图上添加Marker
                        option = new MarkerOptions().position(point).icon(temDescriptor);
                        current = Constant.TEM;
                        break;

                    case Constant.HUM:
                        option = new MarkerOptions().position(point).icon(humDescriptor);
                        current = Constant.HUM;
                        break;
                    case Constant.WIN:
                        option = new MarkerOptions().position(point).icon(winDescriptor);
                        current = Constant.WIN;
                        break;
                    case Constant.RAIN:
                        option = new MarkerOptions().position(point).icon(rainDescriptor);
                        current = Constant.RAIN;
                        break;
                    default:

                        break;
                }
                //在地图上添加Marker，并显示
                Overlay marker = mBaiduMap.addOverlay(option);
                Bundle bundle = new Bundle();
                bundle.putSerializable("site", siteInfo);
                marker.setExtraInfo(bundle);
            }
        }
    }

    /**
     * 添加易灾点
     *
     * @param list
     */
    private void addOverDanger(List<DangerBean> list) {
        if (null != list && list.size() > 0) {
            for (DangerBean bean : list) {
                LatLng point = new LatLng(bean.getLatitude(), bean.getLongitude());
                MarkerOptions option = new MarkerOptions().position(point).icon(dangerDescriptor);
                Overlay marker = mBaiduMap.addOverlay(option);
                Bundle bundle = new Bundle();
                bundle.putSerializable("site", bean);
                marker.setExtraInfo(bundle);
            }
        } else {
            ToastUtil.show(mMainActivity, "附近没有易灾点");
        }
    }


    /**
     * 添加避难所
     *
     * @param list
     */
    private void addOverShelter(List<ShelterBean> list) {
        if (null != list && list.size() > 0) {
            for (ShelterBean bean : list) {
                LatLng point = new LatLng(bean.getLatitude(), bean.getLongitude());
                MarkerOptions option = new MarkerOptions().position(point).icon(shelterDescriptor);
                Overlay marker = mBaiduMap.addOverlay(option);
                Bundle bundle = new Bundle();
                bundle.putSerializable("site", bean);
                marker.setExtraInfo(bundle);
            }
        } else {
            ToastUtil.show(mMainActivity, "附近没有避难所");
        }
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
        initViewPager(nowPage);
        initMap(view);
    }


    public void initViewPager(int i) {
        LayoutInflater inflater = LayoutInflater.from(mMainActivity);
//        pagerView = inflater.inflate(R.layout.layout_forecast_weather_info, null);
        views = new ArrayList<>();
        final List<CityBean> addList = AddedCityUtil.getAllCity(mMainActivity);
        for (int j = 0; j < addList.size() + 1; j++) {
            View pagerView1 = inflater.inflate(R.layout.layout_forecast_weather_info, null);
            views.add(pagerView1);
        }

        pagerView = views.get(i);
        viewPager.setAdapter(new MyPagerAdapter(views));
        tvFail = (TextView) pagerView.findViewById(R.id.tv_home_fail);
        tvFreshTime = (TextView) pagerView.findViewById(R.id.tv_info_refresh);
        tvFreshTime.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                LogUtil.e("position:"+position);
                pointLayout.getChildAt(position).setEnabled(true);
                pointLayout.getChildAt(lastPoint_position).setEnabled(false);
                if(position>lastPoint_position){
                    mMainActivity.rightMove();
                }else {
                    mMainActivity.leftMove();
                }

                if(position==0){
                    mBaiduMap.clear();
                    tvHomeCity.setText(nowCity);
                    List<SiteBean.DataEntity> closeSite = SiteUtil.getSiteInCircle(mMainActivity, mCurrentLng[0], 8000);
                    if (closeSite != null && closeSite.size() > 0) {
                        mBaiduMap.clear();
                        mMainActivity.getSiteMonitorData(closeSite);
                    }
                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng[0]);
                    mBaiduMap.setMapStatus(mMapStatusUpdate);
                }else {
                    CityBean bean = AddedCityUtil.getAllCity(mMainActivity).get(position - 1);
                    tvHomeCity.setText(bean.getName());
                    mCurrentLng[1] = new LatLng(Double.parseDouble(bean.getLat()), Double.parseDouble(bean.getLng()));
                    List<SiteBean.DataEntity> closeSite = SiteUtil.getSiteInCircle(mMainActivity, mCurrentLng[1], 8000);
                    if (closeSite != null && closeSite.size() > 0) {
                        mBaiduMap.clear();
                        mMainActivity.getSiteMonitorData(closeSite);
                    }
                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng[1]);
                    mBaiduMap.setMapStatus(mMapStatusUpdate);
                }
                lastPoint_position = position;
//                if (position == 0) {
//                    tvHomeCity.setText(nowCity);
//                    mBaiduMap.clear();
//                    List<SiteBean.DataEntity> closeSite = SiteUtil.getSiteInCircle(mMainActivity, mCurrentLng[0], 8000);
//                    if (closeSite != null && closeSite.size() > 0) {
//                        mBaiduMap.clear();
//                        mMainActivity.getSiteMonitorData(closeSite);
//                    }
//                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng[0]);
//                    mBaiduMap.setMapStatus(mMapStatusUpdate);
//                } else {
//                    CityBean bean = AddedCityUtil.getAllCity(mMainActivity).get(position - 1);
//                    tvHomeCity.setText(bean.getName());
//                    mCurrentLng[1] = new LatLng(Double.parseDouble(bean.getLat()), Double.parseDouble(bean.getLng()));
//                    mMainActivity.getForecastData(bean, SiteUtil.getCloseSite(mMainActivity, mCurrentLng[1]), bean.getId());
//
//                    List<SiteBean.DataEntity> closeSite = SiteUtil.getSiteInCircle(mMainActivity, mCurrentLng[1], 8000);
//                    if (closeSite != null && closeSite.size() > 0) {
//                        mBaiduMap.clear();
//                        mMainActivity.getSiteMonitorData(closeSite);
//                    }
//                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng[1]);
//                    mBaiduMap.setMapStatus(mMapStatusUpdate);
//                }
                nowPage = position;
//                mMainActivity.getForecastData(bean, SiteUtil.getCloseSite(mMainActivity,
//                        new LatLng(Double.parseDouble(bean.getLat()),Double.parseDouble(bean.getLng()))));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addImage(addList.size() + 1);
    }

    public void selectPosition(int i) {
        LogUtil.e("selection:i:" + i);
        LogUtil.e(AddedCityUtil.getAllCity(mMainActivity).size() + "");
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
        MapSettingPopupwindow popupWindown = new MapSettingPopupwindow(mMainActivity, callBack);
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

    private void addImage(int size) {
        pointLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            //添加指示点
            ImageView point = new ImageView(mMainActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.rightMargin = 10;
            params.leftMargin = 10;
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointLayout.addView(point);
            lastPoint_position = 0;
        }
    }

    /**
     * 首页上面的显示天气和显示站点的切换
     *
     * @param showView  要显示的view
     * @param hideView1 要隐藏的view
     */
    private void changeView(View showView, View hideView1, View hideView2) {
        showView.setVisibility(VISIBLE);
        hideView1.setVisibility(GONE);
        hideView2.setVisibility(GONE);
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
        isSetLoc = true;
        LogUtil.e("位置是:" + reverseGeoCodeResult.getAddress());
        MPreferencesUtil.getInstance().setValue(Constant.NOWLOCAL, reverseGeoCodeResult.getAddress());
        city = addressDetail.city;
        nowCity = city;
        tvHomeCity.setText(nowCity);
        String district = addressDetail.district;
        String street = addressDetail.street;
        //请求网络信息
        if (mCurrentLng != null) {
            if (NetWorkUtil.isNetworkAvailable(mMainActivity)) {
                CityBean cityBean = CityUtil.getCityByName(mMainActivity, city);
                mMainActivity.homeCity = cityBean;
                application = (WeatherAppContext) mMainActivity.getApplication();
                application.setCurrentCityId(cityBean.getId());
                mMainActivity.getForecastData(cityBean, SiteUtil.getCloseSite(mMainActivity, mCurrentLng[0]));
                mMainActivity.getDangerAndShelterData(CityUtil.getCityByName(mMainActivity, city).getId(), mCurrentLng[0], 0, 0);
            } else {
                tvFail.setVisibility(VISIBLE);
            }
        }
        List<SiteBean.DataEntity> closeSite = SiteUtil.getSiteInCircle(mMainActivity, mCurrentLng[0], 8000);
        if (closeSite != null && closeSite.size() > 0) {
            mMainActivity.getSiteMonitorData(closeSite);
        }
        LogUtil.e(city + "  " + district + "  " + street);
        if ("东莞市".equals(city)) {
            nowCity = SiteUtil.getCloseSite(mMainActivity, mCurrentLng[0]).getAreaName();
            tvHomeCity.setText(nowCity);
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
                Toast.makeText(mMainActivity, "定位失败", Toast.LENGTH_SHORT).show();
                return;
            }
            int[] locations = new int[2];
            mMapView.getLocationOnScreen(locations);
//            Point mBaiduMapStartPosition = new Point(locations[0], locations[1]);
//            Point mBaiduMapCenterPosition = new Point((locations[0] + mMapView.getWidth()) / 2, locations[1]
//                    + mMapView.getHeight() / 2);
//            LatLng cent = mBaiduMap.getProjection().fromScreenLocation(mBaiduMapCenterPosition);
            mCurrentLng[0] = new LatLng(location.getLatitude(), location.getLongitude());
//            113.796164,23.04701
//            mCurrentLng[0] = new LatLng(23.04701, 113.796164);

            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    .direction(100).latitude(mCurrentLng[0].latitude).longitude(mCurrentLng[0].longitude).build();
            mBaiduMap.setMyLocationData(locData);

            if (mMapStatusUpdate == null)
                mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng[0]);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(mCurrentLng[0].latitude, mCurrentLng[0].longitude);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);

            }
            if (!NetWorkUtil.isNetworkAvailable(mMainActivity)) {
                tvFail.setVisibility(VISIBLE);
            } else {
                tvFail.setVisibility(GONE);
            }
            if (!isSetLoc) {
                // 反Geo搜索
                mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(mCurrentLng[0]));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    /**
     * 百度地图触摸事件的监听
     */
    private class MyOnTouchListener implements BaiduMap.OnMapTouchListener {

        @Override
        public void onTouch(MotionEvent motionEvent) {

        }
    }

    private class MyMarkerListener implements BaiduMap.OnMarkerClickListener {
        private TextView tv_siteInfo_siteName;
        private TextView tv_siteInfo_siteAddr;
        private TextView tv_siteInfo_siteDistance;


        private TextView tv_siteInfo_type;
        private TextView tv_siteInfo_value1;
        private TextView tv_dgree;
        private TextView tv_siteInfo_value2;
        private TextView tv_siteInfo_value3;
        private TextView tv_siteInfo_value4;

        @Override
        public boolean onMarkerClick(Marker marker) {

            changeView(siteView, weatherView, stationView);
            WeatherAppContext.isWeather = false;
            if (isFull) {
                rvHomeInfo.setVisibility(View.VISIBLE);
                lyHomeSearch.setVisibility(View.GONE);
                isFull = false;
            }
            if (marker.getExtraInfo().get("site") instanceof SiteMonitorBean) {
                SiteMonitorBean siteBean = (SiteMonitorBean) marker.getExtraInfo().get("site");
                tv_siteInfo_type = (TextView) siteView.findViewById(R.id.tv_siteInfo_type);
                tv_siteInfo_value1 = (TextView) siteView.findViewById(R.id.tv_siteInfo_value1);
                tv_dgree = (TextView) siteView.findViewById(R.id.tv_dgree);
                tv_siteInfo_value2 = (TextView) siteView.findViewById(R.id.tv_siteInfo_value2);
                tv_siteInfo_value4 = (TextView) siteView.findViewById(R.id.tv_siteInfo_value4);
                tv_siteInfo_value3 = (TextView) siteView.findViewById(R.id.tv_siteInfo_value3);
                tv_siteInfo_siteName = (TextView) siteView.findViewById(R.id.tv_siteInfo_siteName);
                tv_siteInfo_siteAddr = (TextView) siteView.findViewById(R.id.tv_siteInfo_siteAddr);
                tv_siteInfo_siteDistance = (TextView) siteView.findViewById(R.id.tv_siteInfo_siteDistance);

                String code = siteBean.getSiteCode();
                final SiteBean.DataEntity local = SiteUtil.getSiteBycode(mMainActivity, code);
                String disStr;
                int distance = (int) DistanceUtil.getDistance(mCurrentLng[0],
                        new LatLng(Double.parseDouble(local.getLatitude()), Double.parseDouble(local.getLongitude())));
                if (distance > 1000) {
                    distance = distance / 1000;
                    disStr = distance + "千米";
                } else {
                    disStr = distance + "";
                }
                tv_siteInfo_siteDistance.setText("距离" + disStr);
                tv_siteInfo_siteName.setText(local.getName());
                tv_siteInfo_siteAddr.setText(local.getAreaName());

                switch (current) {
                    case Constant.RAIN:

                        tv_siteInfo_value1.setVisibility(VISIBLE);
                        tv_siteInfo_type.setText("实时雨量");
                        tv_siteInfo_value1.setText(siteBean.getHourRain() + "");
                        tv_dgree.setVisibility(VISIBLE);
                        tv_dgree.setText("ml");
                        tv_siteInfo_value2.setText("实时温度  " + siteBean.getAirTemp() + "℃");
                        tv_siteInfo_value3.setText("风力风向  " + siteBean.getSpeedDir());
                        tv_siteInfo_value4.setText("相对湿度  " + siteBean.getRelativeWet());
                        break;
                    case Constant.HUM:

                        tv_siteInfo_value1.setVisibility(VISIBLE);
                        tv_siteInfo_type.setText("相对湿度");
                        tv_siteInfo_value1.setText(siteBean.getRelativeWet());
                        tv_dgree.setVisibility(VISIBLE);
                        tv_dgree.setText("%");
                        tv_siteInfo_value2.setText("实时雨量  " + siteBean.getRelativeWet());
                        tv_siteInfo_value3.setText("实时温度  " + siteBean.getAirTemp() + "℃");
                        tv_siteInfo_value4.setText("风力风向  " + siteBean.getSpeedDir());

                        break;
                    case Constant.WIN:


                        tv_siteInfo_type.setText("风力风向");
                        tv_dgree.setText(siteBean.getSpeedDir());
                        tv_siteInfo_value1.setVisibility(GONE);
                        tv_siteInfo_value2.setText("实时雨量  " + siteBean.getRelativeWet());
                        tv_siteInfo_value3.setText("实时温度  " + siteBean.getAirTemp() + "℃");
                        tv_siteInfo_value4.setText("相对湿度  " + siteBean.getRelativeWet());
                        break;
                    case Constant.TEM:

                        tv_siteInfo_value1.setVisibility(VISIBLE);
                        tv_siteInfo_type.setText("实时温度");
                        tv_siteInfo_value1.setText(siteBean.getAirTemp());
                        tv_dgree.setVisibility(VISIBLE);
                        tv_siteInfo_value2.setText("实时雨量  " + siteBean.getHourRain());
                        tv_siteInfo_value3.setText("风力风向  " + siteBean.getSpeedDir());
                        tv_siteInfo_value4.setText("相对湿度  " + siteBean.getRelativeWet());

                        break;
                }
                siteView.findViewById(R.id.iv_nav).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtil.nav(mMainActivity, local.getLatitude(), local.getLongitude(), local.getName());
                    }
                });
            } else if (marker.getExtraInfo().get("site") instanceof DangerBean) {
                changeView(stationView, weatherView, siteView);
                WeatherAppContext.isWeather = false;
                //避难所
                final DangerBean dangerBean = (DangerBean) marker.getExtraInfo().get("site");
                TextView tvStationName = (TextView) stationView.findViewById(R.id.tv_siteInfo_siteName);
                tvStationName.setText(dangerBean.getName());
                TextView tvAddr = (TextView) stationView.findViewById(R.id.tv_siteInfo_siteAddr);
                tvAddr.setText(dangerBean.getAreaName());
                int distance = (int) DistanceUtil.getDistance(mCurrentLng[0], new LatLng(dangerBean.getLatitude(), dangerBean.getLongitude()));
                final String disStr;
                if (distance > 1000) {
                    distance = distance / 1000;
                    disStr = distance + "千米";
                } else {
                    disStr = distance + "";
                }
                TextView tvDis = (TextView) stationView.findViewById(R.id.tv_siteInfo_siteDistance);
                tvDis.setText(disStr);
                findViewById(R.id.tv_nav).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtil.nav(mMainActivity, dangerBean.getLatitude(), dangerBean.getLongitude(), dangerBean.getAreaName());
                    }
                });
                TextView tvValue1 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value1);
                tvValue1.setText(dangerBean.getType());

                TextView tvValue2 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value2);
                tvValue2.setVisibility(GONE);
                tvValue2.setText("影响范围:" + dangerBean.getRange());
                TextView tvValue3 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value3);
                TextView tvType = (TextView) stationView.findViewById(R.id.tv_siteInfo_type);
                tvType.setText("可能灾害形式:");
                tvValue3.setText("所属部门:" + dangerBean.getBelongUnit());
                TextView tvValue4 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value4);
                tvValue4.setText(dangerBean.getDutyPhone());
                LogUtil.e("易灾点");
            } else if (marker.getExtraInfo().get("site") instanceof ShelterBean) {
                changeView(stationView, weatherView, siteView);
                WeatherAppContext.isWeather = false;
                final ShelterBean shelterBean = (ShelterBean) marker.getExtraInfo().get("site");
                ImageView ivShelter = (ImageView) stationView.findViewById(R.id.iv_siteInfo_icon);
                ivShelter.setImageResource(R.drawable.icon_map_refuge);
                TextView tvStationName = (TextView) stationView.findViewById(R.id.tv_siteInfo_siteName);
                tvStationName.setText(shelterBean.getName());
                TextView tvAddr = (TextView) stationView.findViewById(R.id.tv_siteInfo_siteAddr);
                tvAddr.setText(shelterBean.getAreaName());
                int distance = (int) DistanceUtil.getDistance(mCurrentLng[0], new LatLng(shelterBean.getLatitude(), shelterBean.getLongitude()));
                final String disStr;
                if (distance > 1000) {
                    distance = distance / 1000;
                    disStr = distance + "千米";
                } else {
                    disStr = distance + "";
                }
                TextView tvDis = (TextView) stationView.findViewById(R.id.tv_siteInfo_siteDistance);
                tvDis.setText(disStr);
                findViewById(R.id.tv_nav).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtil.nav(mMainActivity, shelterBean.getLatitude(), shelterBean.getLongitude(), shelterBean.getName());
                    }
                });
                TextView tvValue1 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value1);
                tvValue1.setText(shelterBean.getType());

                TextView tvValue2 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value2);
                tvValue2.setVisibility(GONE);
                TextView tvType = (TextView) stationView.findViewById(R.id.tv_siteInfo_type);
                tvType.setText("类型:");
                TextView tvValue3 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value3);
                tvValue3.setText("所属部门:" + shelterBean.getBelongUnit());
                TextView tvValue4 = (TextView) stationView.findViewById(R.id.tv_siteInfo_value4);
                tvValue4.setText(shelterBean.getDutyPhone());
                LogUtil.e("避难所");
            }
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
                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng[0]);
                    mBaiduMap.setMapStatus(mMapStatusUpdate);
                }
                break;
            case R.id.iv_home_more:
                showPopupwindow(new HomeCallBack() {
                    @Override
                    public void callBack(int a, boolean danger, boolean shelter) {
                        LogUtil.e("避难所:" + shelter + "    易灾点:" + danger);
                        refreshOver(a, danger, shelter);
                    }
                });
                break;
            case R.id.ib_home_play:
                /**
                 * 亲！好的天气带来好的心情！东莞天气为您播报，东莞今天的天气是晴间多云，温度12到15度，西北风2到3级。
                 */
                if (NetWorkUtil.isNetworkAvailable(getContext())) {
                    try {
                        String weatherStr = "亲！好的天气带来好的心情！东莞天气为您播报，" + tvHomeCity.getText().toString() +
                                "今天的天气是" + baseBean.getDays().get(0).getWeaDesc() + ","
                                + baseBean.getSite().getMinTemp() + "℃到" + baseBean.getSite().getMaxTemp() + "℃,"
                                + baseBean.getSite().getSpeedDir();
                        broadWeather(weatherStr);
                    } catch (Exception e) {
                        ToastUtil.show(mMainActivity, "数据获取失败");
                    }

                } else
                    Toast.makeText(getContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rv_top_local:
                Intent intent = new Intent(mMainActivity, CityManagerActivity.class);
                intent.putExtra(Constant.LOCAL, tvHomeCity.getText().toString());
                mMainActivity.startActivityForResult(intent, Constant.CHANGE);
                break;

            case R.id.ib_home_share:
                ShareSDKUtil.showShare(mMainActivity, viewPager,"东莞", "多云转晴", "21~30度", "东南风", "3~4级");
                break;

            case R.id.tv_info_refresh:
                Toast.makeText(mMainActivity, "刷新天气", Toast.LENGTH_SHORT).show();
                if (nowPage == 0) {
                    mMainActivity.getForecastData(CityUtil.getCityByName(mMainActivity, nowCity),
                            SiteUtil.getCloseSite(mMainActivity, mCurrentLng[0]), cityId);
                } else {
                    CityBean nowCityBean = AddedCityUtil.getAllCity(mMainActivity).get(nowPage - 1);
                    LatLng lng = new LatLng(Double.parseDouble(nowCityBean.getLat()), Double.parseDouble(nowCityBean.getLng()));
                    mMainActivity.getForecastData(nowCityBean, SiteUtil.getCloseSite(mMainActivity, lng), cityId);
                }
                break;
            case R.id.ib_info_warn1:
                mMainActivity.setTabSelection(1);
                break;
        }
    }

    private void refreshOver(int a, boolean danger, boolean shelter) {
        if (a != -1) {
            mBaiduMap.clear();
            addOverSite(sites, a);
            if (danger) {
                addOverDanger(dangers);
            }
            if (shelter) {
                addOverShelter(shelters);
            }
        }
    }

    /**
     * 注销地图
     */
    public void recycle() {
        mBaiduMap.setMyLocationEnabled(false);
        temDescriptor.recycle();
        humDescriptor.recycle();
        winDescriptor.recycle();
        rainDescriptor.recycle();
    }

    public void toHome() {
        changeView(weatherView, siteView, stationView);
        WeatherAppContext.isWeather = true;
    }

}
