package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.fgwx.dgweather.Callback.ScrollTouchListenerImpl;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.view.WeatherTrendView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastMoreFragment extends Fragment implements View.OnClickListener,ScrollTouchListenerImpl.ScrollUp{

    private MainActivity mMainActivity;
    private WeatherTrendView mDayWeatherTrendView;
    private ScrollView mRootScrollView;
    private ScrollTouchListenerImpl mScollScrollTouchListener;
   /* private AdapterScroListView aslv_lifeIndex;
    private LivingIndexAdapter adapter;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_more,null,false);
        initView(view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivity= (MainActivity) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void initView(View view){
        mScollScrollTouchListener=new ScrollTouchListenerImpl();
        mScollScrollTouchListener.setScrollUpListener(this);
        mDayWeatherTrendView= (WeatherTrendView) view.findViewById(R.id.wtv_day_trend_view);
        mRootScrollView= (ScrollView) view.findViewById(R.id.scroll_forecast_more);
        mRootScrollView.setOnTouchListener(mScollScrollTouchListener);
        List<ForecastForTenDayBean> beans=new ArrayList<>();
        ForecastForTenDayBean bean;
        for(int i=0;i<6;i++){
            bean=new ForecastForTenDayBean();
            bean.setCurMaxTemp(20 + i * 2);
            beans.add(bean);
        }
        mDayWeatherTrendView.setDataBean(beans);
    }
    @Override
    public void onClick(View v) {
      /*  switch (v.getId()){
            case R.id.bnt_back_home:

                break;

        }*/
    }

    @Override
    public void setOnScrollUpListener() {
        mMainActivity.toggleMoreFragment(false);
    }
}
