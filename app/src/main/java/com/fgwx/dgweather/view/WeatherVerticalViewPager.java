package com.fgwx.dgweather.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.*;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.fgwx.dgweather.R;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class WeatherVerticalViewPager extends VerticalViewPager{

    private ScrollView mInnerScrollView;
    private View mRootView;
    private float mLastY;
    public WeatherVerticalViewPager(Context context) {
        this(context, null);
    }
    public WeatherVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if(getCurrentItem()==0)return false;
        super.onInterceptTouchEvent(ev);
        if(getCurrentItem()==1)
        {
            final int action = ev.getAction();
            float y = ev.getY();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    Log.v("weather","down first");
                    mLastY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float dy = y - mLastY;
                    getCurrentScrollView();
                    if (Math.abs(dy) > 20) {
                        if (mInnerScrollView instanceof ScrollView) {
                            // 如果topView没有隐藏
                            // 或sc的scrollY = 0 && topView隐藏 && 下拉，则拦截
                            if ((mInnerScrollView.getScrollY() == 0
                                    && dy >0)) {
                                mLastY = y;
                                return true;
                            }
                        }

                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
        //当拦截触摸事件到达此位置的时候，返回true，
        //说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
          return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.v("weather","down third");
                break;

        }
        return super.onTouchEvent(ev);
    }

    private void getCurrentScrollView() {
        mRootView=  getChildAt(0);
        mInnerScrollView=(ScrollView)mRootView.findViewById(R.id.scr_second_forecast);
    }
}
