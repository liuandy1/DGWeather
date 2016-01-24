package com.fgwx.dgweather.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;

import java.util.List;

/**
 * Created by senghor on 2016/1/3.
 */
public class WeatherDayTrendView extends View {

    private List<ForecastForTenDayBean> mbeans;
    private float multipleY =6;//自定义温度偏移量倍数为8，可根据屏幕分辨率设定
    private float multipleX;
    private float radius;
    private float[] mPointXs;
    private float mHeight;
    private float highOriginHeight;
    private float lowOriginHeight;
    private Paint mPaint;
    private Paint mTextPaint;
    float fontHeight;
    private int sumWidth;
    private int px80;
    private int px40;
    private Resources resources;
    public WeatherDayTrendView(Context context) {
            this(context,null);
        }

        public WeatherDayTrendView(Context context, AttributeSet attrs,
        int defStyle) {
            super(context, attrs, defStyle);
            initView(context);
    }

    public WeatherDayTrendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void initView(Context context){
        resources=context.getResources();
        multipleX=resources.getDimensionPixelOffset(R.dimen.px_140);
        radius=resources.getDimensionPixelOffset(R.dimen.px_9);
        px80 =resources.getDimensionPixelOffset(R.dimen.px_80);
        px40 =resources.getDimensionPixelOffset(R.dimen.px_40);
        float lineWidth=resources.getDimension(R.dimen.px_2);
        mPaint =new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mTextPaint=new Paint(mPaint);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        fontHeight = fontMetrics.bottom - fontMetrics.top;
    }
    public void setDataBean(List<ForecastForTenDayBean> beans){
        if(beans==null)return;
        mbeans=beans;
        mPointXs=new float[mbeans.size()];
        for(int i=0;i<mbeans.size();i++){
            mPointXs[i]=multipleX/2+multipleX*i;
        }
        sumWidth=(int)(mPointXs[mbeans.size()-1]+multipleX/2);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHeight= getHeight();
        highOriginHeight =mHeight/2+ px40;
        lowOriginHeight =mHeight/2+ px80;
        float originPoinY= (- mbeans.get(0).getCurMaxTemp()) * multipleY;
        highOriginHeight=highOriginHeight-originPoinY;
        originPoinY=(- mbeans.get(0).getCurMinTemp()) * multipleY;
        lowOriginHeight=lowOriginHeight-originPoinY;
        //绘制最高温度曲线
        for (int i = 0; i < mbeans.size(); i++) {
            //温度值对应点在图中的偏移量，即扩以相应倍数，使得温度差明显
            float pointHighY = ( - mbeans.get(i).getCurMaxTemp()) * multipleY;
            float pointLowY = ( - mbeans.get(i).getCurMinTemp()) * multipleY;
            //绘制两点间直线
            if (i != mbeans.size() - 1) {
                //下一个温度值，用以描绘直线
                float pointHighNextY = ( -mbeans.get(i+1).getCurMaxTemp()) * multipleY;
                float pointLowNextY = ( -mbeans.get(i+1).getCurMinTemp()) * multipleY;
                canvas.drawLine(mPointXs[i], highOriginHeight + pointHighY, mPointXs[i+1], highOriginHeight + pointHighNextY, mPaint);
                canvas.drawLine(mPointXs[i], lowOriginHeight + pointLowY, mPointXs[i+1], lowOriginHeight + pointLowNextY, mPaint);

            }
            //绘制圆点
            canvas.drawCircle(mPointXs[i], highOriginHeight + pointHighY, radius, mPaint);

            canvas.drawCircle(mPointXs[i], lowOriginHeight + pointLowY, radius, mPaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(sumWidth, heightSize);
    }
}
