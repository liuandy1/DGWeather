package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.fragment.forecast.LivingIndexAdapter;
import com.fgwx.dgweather.view.AdapterScroListView;

/**
 * Created by senghor on 2015/12/24.
 */
//预报
public class ForecastFragment extends Fragment{
    private AdapterScroListView aslv_lifeIndex;
    private LivingIndexAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_forecast,null,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView(){
        aslv_lifeIndex = (AdapterScroListView) getActivity().findViewById(R.id.aslv_livingIndex);

        initData();
    }

    public void initData(){
        String[] indexInfo = new String[]{"三级，一般，对空气污染物稀释，扩散和清除无明显影响","白天7级，较热，早晚5级，舒适","外出不必带伞","适宜晨练","大自然欢迎您","不易霉变","适宜晾晒","适宜驾驶"};
        adapter = new LivingIndexAdapter(getActivity(),indexInfo);
        aslv_lifeIndex.setAdapter(adapter);

    }
}
