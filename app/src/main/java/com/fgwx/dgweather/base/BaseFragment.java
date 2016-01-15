package com.fgwx.dgweather.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

/**
 * Created by AllenHu on 2016/1/15.
 */
public class BaseFragment extends Fragment {


    protected Callbacks mCallbacks;
    private Context context;
    private LayoutInflater mInflater;

    public interface Callbacks {
        void loading(boolean isLoading);

        void initToolbar(int position);

        void replaceFragment(int position);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        context = getActivity();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    /**
     * 转菊花
     *
     * @param isLoading
     */
    public void loading(boolean isLoading) {
        mCallbacks.loading(isLoading);
    }

}
