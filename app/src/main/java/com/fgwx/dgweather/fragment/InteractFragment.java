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
import com.fgwx.dgweather.view.MapSettingPopupwindow;

/**
 * Created by senghor on 2015/12/24.
 */
//互动
public class InteractFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_interact,null,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv_open = (TextView) getActivity().findViewById(R.id.tv_open);
        tv_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupwindow();
            }
        });
    }

    public void showPopupwindow(){
        MapSettingPopupwindow popupWindown = new MapSettingPopupwindow(getActivity());
        popupWindown.showAtLocation(getActivity().findViewById(R.id.ll), Gravity.CENTER,0, 0);
    }
}
