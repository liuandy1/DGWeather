package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.utils.LogUtil;

/**
 * Created by senghor on 2015/12/24.
 */
//预报
public class ForecastFragment extends Fragment implements View.OnClickListener{
    private MainActivity mMainActivity;
    public static boolean isFull = false;
    private RelativeLayout rvHomeInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_forecast,null,false);
        initUi(view);
        return view;
    }
    private void initUi(View view){
        view.findViewById(R.id.bnt_home_more).setOnClickListener(this);
        view.findViewById(R.id.iv_home_full).setOnClickListener(this);
        rvHomeInfo = (RelativeLayout) view.findViewById(R.id.rv_home_info);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivity= (MainActivity) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnt_home_more:
               mMainActivity.toggleMoreFragment(true);
                break;

            case R.id.iv_home_full:
                fullWindow();
                break;
        }
    }

    /**
     * 开启或者关闭全屏
     */
    private void fullWindow() {
        LogUtil.e("全屏操作");
        if(!isFull){
            rvHomeInfo.setVisibility(View.GONE);
            isFull = true;
        }else {
            rvHomeInfo.setVisibility(View.VISIBLE);
            isFull = false;
        }
    }
}
