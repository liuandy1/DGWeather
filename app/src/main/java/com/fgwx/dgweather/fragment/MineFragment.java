package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseFragment;

/**
 * Created by senghor on 2015/12/24.
 */
public class MineFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,null,false);
        return view;
    }
    public static MineFragment createFragment(){
        MineFragment fragment=new MineFragment();
        return  fragment;
    }
}
