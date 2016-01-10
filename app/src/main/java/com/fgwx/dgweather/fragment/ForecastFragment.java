package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.ForecastSortAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.WeatherNetUtils;
import com.fgwx.dgweather.view.ForecastFirstView;
import com.fgwx.dgweather.view.ForecastSecondView;
import com.fgwx.dgweather.view.WeatherVerticalViewPager;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastFragment extends Fragment{

    private List<View> mViews;
    private BaseActivity mContext;
    private WeatherVerticalViewPager mViewPager;
    private ForecastSortAdapter adapter;
    private View mForecastFirstView,mForecastSecondView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragnent_forecast,null,false);
        initUI(view);
        //getForecastNetData();
       // getAreaData();
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext= (BaseActivity) activity;
    }

    private void initUI(View view){
        mViews=new ArrayList<>();
        mViewPager= (WeatherVerticalViewPager) view.findViewById(R.id.vp_home_page_fragment);
        mForecastFirstView = new ForecastFirstView(mContext);
        mForecastSecondView =new ForecastSecondView(mContext);
        mViews.add(mForecastFirstView);
        mViews.add(mForecastSecondView);
        adapter=new ForecastSortAdapter(mViews);
        mViewPager.setAdapter(adapter);
    }
    public void setSecondPage(){
        mViewPager.setCurrentItem(1);
    }

    private void getForecastNetData(){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("cityId", "441900");
        //map.put("streetId", null);
        map.put("siteId", "G1901");
        //map.put("last10DayTime", null);
        map.put("query10Day", "1");
        map.put("queryExact", "1");
        //map.put("lastExactTime", null);
        //map.put("lastSiteTime", null);
        map.put("queryLife", "1");
        map.put("lastLifeTime","1");
        map.put("querySun","1");
        //map.put("lastSunTime",null);
        WeatherNetUtils.getHomeForecastData(new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {
                LogUtil.e("访问成功了");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("访问失败了");
                LogUtil.e(error.toString());
            }
        }, map);
    }
   /* private void getAreaData(){
        WeatherNetUtils.getAreaData(new Response.Listener<AreaBaseBean>() {
            @Override
            public void onResponse(AreaBaseBean response) {
                LogUtil.e("访问成功了");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("访问失败了");
                LogUtil.e(error.toString());
            }
        });
    }*/
}
