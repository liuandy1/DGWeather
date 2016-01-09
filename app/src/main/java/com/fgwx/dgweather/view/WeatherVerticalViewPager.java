package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class WeatherVerticalViewPager extends VerticalViewPager{

    public WeatherVerticalViewPager(Context context) {
        this(context, null);
    }

    public WeatherVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if(getCurrentItem()==0)return false;
        //当拦截触摸事件到达此位置的时候，返回true，
        //说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
         return true;
    }


}
