package com.fgwx.dgweather.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.baidu.location.b.m;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.bean.ForecastSunBean;
import com.fgwx.dgweather.utils.TimeUtil;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class WeatherSunChangeView extends View{
    private Paint mPaint;
    private Paint mAlphaPaint;
    private int mWidth;
    private int mHeight;
    private Path mPath;
    private int BitmapWidth;
    private int BitmapHeight;
    private RectF rect;
    private Bitmap mBitmap;
    private PathEffect effects;
    private float[] mCurrentPoint = new float[2];
    private int marginWidth;
    private int marginHeight;
    private float textWidth;
    private float angle=0;
    private ForecastSunBean mSunBean;
    private long currentTime;
    public WeatherSunChangeView(Context context) {
        this(context,null);
    }
    public WeatherSunChangeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public WeatherSunChangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap=((BitmapDrawable)context.getResources().getDrawable(R.drawable.icon_life_sun)).getBitmap();
        marginWidth=context.getResources().getDimensionPixelOffset(R.dimen.px_60);
        marginHeight=context.getResources().getDimensionPixelOffset(R.dimen.px_80);
        initView();
    }
    private void initView(){
        BitmapWidth=mBitmap.getWidth();
        BitmapHeight=mBitmap.getHeight();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(36f);
        effects = new DashPathEffect(new float[] { 6, 6}, 1);
        mPath=new Path();
        mAlphaPaint=new Paint(mPaint);
        mAlphaPaint.setColor(Color.parseColor("#64ffffff"));
        //mPath1=new Path();

    }

    public void changeSunTime(){
        currentTime=System.currentTimeMillis();
        if(currentTime>=mSunBean.getSunsetTime()||currentTime<mSunBean.getSunriseTime()){
            angle=180f;
            postInvalidate();
            return;
        }
        angle=180*((currentTime-mSunBean.getSunriseTime()*1f)/(mSunBean.getSunsetTime()-mSunBean.getSunriseTime()*1f));postInvalidate();
        postInvalidate();

    }
    public void setSunTimeData(ForecastSunBean sunBean){
        if(sunBean==null)return;
        mSunBean=sunBean;
        changeSunTime();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                changeSunTime();
               postDelayed(this,5*1000);
            }
        },1000*5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5.0f);
        textWidth= mPaint.measureText("14:00");
        mWidth=getWidth();
        mHeight=getHeight();
        rect=new RectF(marginWidth,marginHeight,mWidth-marginWidth,mHeight*2f+marginHeight);
        mPaint.setPathEffect(effects);
        canvas.drawArc(rect, 180 + angle, 180 - angle, false, mPaint);
        mAlphaPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rect, 180, angle, true, mAlphaPaint);
        mPaint.setPathEffect(null);
        canvas.drawArc(rect, 180, angle, false, mPaint);
        mPath.addArc(rect, 180, angle);
        PathMeasure pathMeasure=new PathMeasure(mPath, false);
        pathMeasure.getPosTan(pathMeasure.getLength(), mCurrentPoint, null);
        canvas.drawLine(0, mHeight, mWidth, mHeight, mPaint);
        if(currentTime<mSunBean.getSunsetTime()&&currentTime>mSunBean.getSunriseTime()){
        canvas.drawBitmap(mBitmap, mCurrentPoint[0] - BitmapWidth / 2, mCurrentPoint[1] - BitmapHeight / 2, mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
        if(currentTime<mSunBean.getSunsetTime()&&currentTime>mSunBean.getSunriseTime()){
        canvas.drawText(TimeUtil.hourstrToDateStr(currentTime),mCurrentPoint[0]-textWidth/2,mCurrentPoint[1]-BitmapHeight/2-15,mPaint);
        }
    }
}
