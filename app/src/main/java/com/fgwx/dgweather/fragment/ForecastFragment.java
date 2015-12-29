package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;

/**
 * Created by senghor on 2015/12/24.
 */
//预报
public class ForecastFragment extends Fragment implements View.OnClickListener{
    private MainActivity mMainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_forecast,null,false);
        initUi(view);
        return view;
    }
    private void initUi(View view){
        view.findViewById(R.id.bnt_home_more).setOnClickListener(this);
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
        }
    }
}
