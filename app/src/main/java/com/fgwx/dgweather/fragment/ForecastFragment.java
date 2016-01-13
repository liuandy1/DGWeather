package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.ForecastSortAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.fgwx.dgweather.utils.WeatherNetUtils;
import com.fgwx.dgweather.view.ForecastFirstView;
import com.fgwx.dgweather.view.ForecastSecondView;
import com.fgwx.dgweather.view.WeatherVerticalViewPager;
import com.google.gson.Gson;

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
    private ForecastFirstView mForecastFirstView;
    private ForecastSecondView mForecastSecondView;
    private Gson gson;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragnent_forecast,null,false);
        initUI(view);
       // getAreaData();
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext= (BaseActivity) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //先访问缓存数据
        setForecastData(getCacheData());
    }

    private void initUI(View view){
        gson=new Gson();
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

    private void setCacheData(HomeForecastBaseBean data){
        MPreferencesUtil.getInstance().setValue(MPreferencesUtil.FORECASTDATA, gson.toJson(data));
    }
    private HomeForecastBaseBean getCacheData(){
        String string=MPreferencesUtil.getInstance().getValue(MPreferencesUtil.FORECASTDATA,"");
        if(TextUtils.isEmpty(string)){
            return null;
        }
        return  gson.fromJson(string,HomeForecastBaseBean.class);
    }
    public void getForecastNetData(CityBean cityBean,SiteBean.DataEntity siteBean){
        TreeMap<String, String> map = new TreeMap<>();
        //if(!TextUtils.isEmpty(cityBean.getId()))
            map.put("cityId", "441900");//城市Id，必须
        //map.put("streetId", null);//街道Id
        //if(!TextUtils.isEmpty(siteBean.getId()))
            map.put("siteId", "G1991");//站点Id
        //map.put("last10DayTime", null);
        map.put("query10Day", "1");//是否查询10天天气预报（不可空，0否1是）
        map.put("queryExact", "1");//是否查询精确预报 （不可空，0否1是）
        //map.put("lastExactTime", null);//精确预报最后更新时间
        //map.put("lastSiteTime", null);
        map.put("queryLife", "1");
        map.put("lastLifeTime","1");
        map.put("querySun","1");
        //map.put("lastSunTime",null);
        WeatherNetUtils.getHomeForecastData(new Response.Listener<HomeForecastBaseBean>() {
            @Override
            public void onResponse(HomeForecastBaseBean response) {
                LogUtil.e("访问成功了");
                if(response==null){
                    return;
                }
                int code=response.getCode();
                switch (code){
                    case 200:
                        setCacheData(response);
                        setForecastData(response);
                        break;
                    default:
                        break;
                }
                //设置数据
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("访问失败了");
                LogUtil.e(error.toString());
            }
        }, map);
    }


    private void setForecastData(HomeForecastBaseBean homeForecastBaseBean){
        if(homeForecastBaseBean==null)
            return;
        setFirstPageData(homeForecastBaseBean);
        setSecondePageData(homeForecastBaseBean);

    }
    private void setFirstPageData(HomeForecastBaseBean homeForecastBaseBean){
         mForecastFirstView.setFirstForecastData(homeForecastBaseBean);
    }
    private void setSecondePageData(HomeForecastBaseBean homeForecastBaseBean){
         mForecastSecondView.setSecondForecastData(homeForecastBaseBean);
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
