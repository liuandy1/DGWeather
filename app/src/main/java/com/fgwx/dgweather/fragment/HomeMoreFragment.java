package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.LivingIndexAdapter;
import com.fgwx.dgweather.view.AdapterScroListView;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class HomeMoreFragment extends Fragment implements View.OnClickListener{

    private MainActivity mMainActivity;
    private AdapterScroListView aslv_lifeIndex;
    private LivingIndexAdapter adapter;
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
        aslv_lifeIndex = (AdapterScroListView) view.findViewById(R.id.aslv_livingIndex);
        view.findViewById(R.id.bnt_back_home).setOnClickListener(this);
        initData();
    }

    public void initData(){
        String[] indexInfo = new String[]{"三级，一般，对空气污染物稀释，扩散和清除无明显影响","白天7级，较热，早晚5级，舒适","外出不必带伞","适宜晨练","大自然欢迎您","不易霉变","适宜晾晒","适宜驾驶"};
        adapter = new LivingIndexAdapter(getActivity(),indexInfo);
        aslv_lifeIndex.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnt_back_home:
                mMainActivity.toggleMoreFragment(false);
                break;

        }
    }
}
