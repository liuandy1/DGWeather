package com.fgwx.dgweather.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.utils.ExitAppUtils;
import com.fgwx.dgweather.view.ProgressWheel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by senghor on 2015/12/23.
 */
public class BaseActivity extends AppCompatActivity implements BaseFragment.Callbacks{



    //public static List<Activity> activityList = new ArrayList<Activity>();
    private static RelativeLayout rlLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitAppUtils.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtils.getInstance().delActivity(this);
    }


    /**
     * @param isLoading
     *            是否加载
     */
    public void loading(boolean isLoading) {
        if (rlLoading == null) {
            rlLoading = (RelativeLayout) LayoutInflater.from(this).inflate(
                    R.layout.layout_loading, null);
            rlLoading.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        ProgressWheel mProgressWheel = (ProgressWheel) rlLoading
                .findViewById(R.id.progress_bar_one);
        if (isLoading && rlLoading.getParent() == null) {
            getContentView().addView(rlLoading);
            mProgressWheel.spin();
        } else {
            mProgressWheel.stopSpinning();
            getContentView().removeView(rlLoading);
        }
    }

    @Override
    public void initToolbar(int position) {

    }

    @Override
    public void replaceFragment(int position) {

    }

    /**
     * @return 返回ContentView的父布局FrameLayout
     */
    private ViewGroup getContentView() {
        return (ViewGroup) getWindow().getDecorView().findViewById(
                android.R.id.content);
    }

    public void finish(View view){
        this.finish();
    }
}
