package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.ForecastSortAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.view.ForecastFirstView;
import com.fgwx.dgweather.view.ForecastSecondView;
import com.fgwx.dgweather.view.WeatherVerticalViewPager;

import java.util.ArrayList;
import java.util.List;

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

}
