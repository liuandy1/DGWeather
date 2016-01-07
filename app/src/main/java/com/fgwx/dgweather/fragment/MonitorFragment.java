package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechSynthesizer;

/**
 * Created by senghor on 2015/12/24.
 */
//监测
public class MonitorFragment extends Fragment {

    //    private static final String APPID = "appid=519328ab";
    // 语音合成对象
    private SpeechSynthesizer mTts;
    private InitListener mTtsInitListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, null, false);
        initView(view);
        return view;

    }

    private void initView(View view) {

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
