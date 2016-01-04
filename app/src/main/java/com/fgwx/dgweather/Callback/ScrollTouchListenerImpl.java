package com.fgwx.dgweather.Callback;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by senghor on 2016/1/3.
 */
public class ScrollTouchListenerImpl  implements View.OnTouchListener{
    private float beginY;
    private ScrollUp mScollUp;
    private final static int MAJOR_MOVE=20;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //加上下滑阻力与实际滑动距离的差（降噪音）
                beginY =  motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                /* int scrollY=view.getScrollY();
                 int height=view.getHeight();
                 int scrollViewMeasuredHeight=scrollView1.getChildAt(0).getMeasuredHeight();
                 if(scrollY==0){
                        System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
                    }
                 if((scrollY+height)==scrollViewMeasuredHeight){
                        System.out.println("滑动到了底部 scrollY="+scrollY);
                        System.out.println("滑动到了底部 height="+height);
                        System.out.println("滑动到了底部 scrollViewMeasuredHeight="+scrollViewMeasuredHeight);
                    }*/
               /* int scrollY=view.getScrollY();
                if(scrollY==0){

                    //拿到滑动的Y轴距离
                    //int interval = (int) (motionEvent.getY() - beginY);
                    //if(interval>0){
                        flag=true;
                }*/

                break;
            case MotionEvent.ACTION_UP:
                float dy = (motionEvent.getY() - beginY);
                //降噪处理，必须有较大的动作才识别
                if(Math.abs(dy)>MAJOR_MOVE&&dy<0&&view.getScrollY()==0){
                    mScollUp.setOnScrollUpListener();
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
