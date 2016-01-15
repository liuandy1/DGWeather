package com.fgwx.dgweather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.TestActivity;
import com.fgwx.dgweather.base.BaseFragment;

/**
 * Created by senghor on 2015/12/24.
 */
//监测
public class MonitorFragment extends BaseFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, null, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        view.findViewById(R.id.btn_play).setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                startActivity(new Intent(getActivity(),TestActivity.class));
                break;
            default:
                break;
        }
    }
}
