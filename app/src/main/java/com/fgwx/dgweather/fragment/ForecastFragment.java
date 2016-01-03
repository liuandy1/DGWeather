package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.MyPagerAdapter;
import com.fgwx.dgweather.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by senghor on 2015/12/24.
 */
//预报
public class ForecastFragment extends Fragment implements View.OnClickListener {
    private MainActivity mMainActivity;
    public static boolean isFull = false;
    private RelativeLayout rvHomeInfo;
    private LinearLayout lyHomeSearch;
    private LinearLayout pointLayout;
    private int lastPoint_position;
    private ViewPager viewPager;

    private static LatLng mCurrentLng;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode = LocationMode.COMPASS;
    private boolean isFirstLoc = true;
    private MapStatusUpdate mMapStatusUpdate;
    private MapStatus mMapStatus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, null, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        view.findViewById(R.id.iv_home_full).setOnClickListener(this);
        view.findViewById(R.id.iv_home_location).setOnClickListener(this);
        view.findViewById(R.id.ly_home_down).setOnClickListener(this);
        rvHomeInfo = (RelativeLayout) view.findViewById(R.id.rv_home_info);
        lyHomeSearch = (LinearLayout) view.findViewById(R.id.ly_home_search);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_home);
        pointLayout = (LinearLayout) view.findViewById(R.id.ly_main_point_group);
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

//        mMapStatus = new MapStatus.Builder().zoom(15f).build();
        mMapStatusUpdate = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        // 定位初始化
        mLocClient = new LocationClient(getActivity().getApplication());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();


    }

    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view1 = inflater.inflate(R.layout.layout_forecast_info, null);
        View view2 = inflater.inflate(R.layout.layout_forecast_info, null);
        TextView tv2 = (TextView) view2.findViewById(R.id.tv_layout_forecast);
        tv2.setText("22222222222222");
        View view3 = inflater.inflate(R.layout.layout_forecast_info, null);
        TextView tv3 = (TextView) view3.findViewById(R.id.tv_layout_forecast);
        tv3.setText("333333333333");
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivity = (MainActivity) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ly_home_down:
                mMainActivity.toggleMoreFragment(true);
                break;
            case R.id.iv_home_full:
                fullWindow();
                break;
            case R.id.iv_home_location:
                LogUtil.e("重新定位");
//                mLocClient.start();
                if (mCurrentLng != null) {
                    mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng);
                    mBaiduMap.setMapStatus(mMapStatusUpdate);

                }


                LogUtil.e("重新定位11111111");
                break;
        }
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
            ImageView point = new ImageView(getActivity());
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
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView = null;
        super.onDestroy();
    }

    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLng = new LatLng(location.getLatitude(), location.getLongitude());
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (mMapStatusUpdate == null)
                mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(mCurrentLng);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }

    }

}
