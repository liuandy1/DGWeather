package com.fgwx.dgweather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.fgwx.dgweather.bean.ForecastForTenDayBean;

import java.util.List;

/**
 * Created by senghor on 2016/1/3.
 */
public class WeatherTrendView extends View {

    private List<ForecastForTenDayBean> mbeans;
    private float multipleY =6;//自定义温度偏移量倍数为8，可根据屏幕分辨率设定
    private float multipleX=250;
    private float originWidth=80;
    private int radius= 16;
    private float[] mPointXs;
    private float mHeight;
    private float originHeight;
    private Paint mPaint;
    public WeatherTrendView(Context context) {
        this(context,null);
    }

    public WeatherTrendView(Context context, AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public WeatherTrendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void initView(){
        mPaint =new Paint();
        mPaint= new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
    }
    public void setDataBean(List<ForecastForTenDayBean> beans){
       mbeans=beans;
        mPointXs=new float[beans.size()];
        for(int i=0;i<beans.size();i++){
            mPointXs[i]=originWidth+multipleX*i;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbeans==null)return;
        mHeight= getHeight();
        originHeight=mHeight/2;
        float originPoinY= (- mbeans.get(0).getCurMaxTemp()) * multipleY;
        //以第一个点位中点
        originHeight=originHeight-originPoinY;
        //绘制最高温度曲线
        for (int i = 0; i < mbeans.size(); i++) {
            //温度值对应点在图中的偏移量，即扩以相应倍数，使得温度差明显
            float pointY = ( - mbeans.get(i).getCurMaxTemp()) * multipleY;
            //绘制两点间直线
            if (i != mbeans.size() - 1) {
                //下一个温度值，用以描绘直线
                float pointNextY = ( -mbeans.get(i+1).getCurMaxTemp()) * multipleY;
                canvas.drawLine(mPointXs[i], originHeight + pointY, mPointXs[i+1], originHeight + pointNextY, mPaint);
            }
            //绘制圆点
            canvas.drawCircle(mPointXs[i], originHeight + pointY, radius, mPaint);
        }

    }
}
