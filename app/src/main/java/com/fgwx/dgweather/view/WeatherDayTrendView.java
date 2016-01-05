package com.fgwx.dgweather.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.utils.DipPixUtil;

import java.util.List;

/**
 * Created by senghor on 2016/1/3.
 */
public class WeatherDayTrendView extends View {

    private List<ForecastForTenDayBean> mbeans;
    private float multipleY =6;//自定义温度偏移量倍数为8，可根据屏幕分辨率设定
    private float multipleX= DipPixUtil.weatherPx2Dp(70);
    private int radius= 16;
    private float[] mPointXs;
    private float mHeight;
    private float highOriginHeight;
    private float lowOriginHeight;
    private Paint mPaint;
    private Paint mTextPaint;
    private Bitmap mbitmap;
    private float beginY=20;//
    private float marginPicText=50;
    float fontHeight;
    private int sumWidth;
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
        mbitmap=((BitmapDrawable)context.getResources().getDrawable(R.drawable.icon_weather_cloudy)).getBitmap();
        mPaint =new Paint();
        mPaint= new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mTextPaint=new Paint(mPaint);
        mTextPaint.setTextSize(36f);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        fontHeight = fontMetrics.bottom - fontMetrics.top;
    }
    public void setDataBean(List<ForecastForTenDayBean> beans){
       mbeans=beans;
        mPointXs=new float[beans.size()];
        for(int i=0;i<beans.size();i++){
            mPointXs[i]=multipleX/2+multipleX*i;

        }
        sumWidth=(int)mPointXs[beans.size()-1]+mbitmap.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbeans==null)return;
        mHeight= getHeight();
        highOriginHeight =mHeight/2-30;
        lowOriginHeight =mHeight/2+30;
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
            canvas.drawBitmap(mbitmap, mPointXs[i] - mbitmap.getWidth() / 2, beginY, mPaint);
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
