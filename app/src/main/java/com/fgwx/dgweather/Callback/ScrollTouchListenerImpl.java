package com.fgwx.dgweather.Callback;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by senghor on 2016/1/3.
 */
public class ScrollTouchListenerImpl  implements View.OnTouchListener{
    private float beginY;
    private boolean flag;
    private ScrollUp mScollUp;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //加上下滑阻力与实际滑动距离的差（降噪音）
                beginY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - beginY;
                int scrollY=view.getScrollY();
                if(scrollY==0&&dy>30){
                    //拿到滑动的Y轴距离
                        flag=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(flag){
                    flag=false;
                    if(mScollUp!=null)
                    {
                        mScollUp.setOnScrollUpListener();
                    }
                }
                break;

            default:
                break;
        }
        return false;
    }
    public void setScrollUpListener(ScrollUp scrollUpListener){
        mScollUp=scrollUpListener;
    }

    public interface ScrollUp{
        public void setOnScrollUpListener();
    }
}
