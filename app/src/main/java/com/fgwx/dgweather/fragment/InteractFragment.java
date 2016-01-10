package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.view.MultiWarnPopupwindow;

/**
 * Created by senghor on 2015/12/24.
 */
//互动
public class InteractFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_forecast_site_info,null,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView textView = (TextView) getActivity().findViewById(R.id.tv_test);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupwindow();
//            }
//        });
    }

    public void showPopupwindow() {
        MultiWarnPopupwindow popupWindown = new MultiWarnPopupwindow(getActivity());
        popupWindown.showAtLocation(getActivity().findViewById(R.id.ll), Gravity.CENTER, 0, 0);
    }
}
