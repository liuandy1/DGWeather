package com.fgwx.dgweather.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;

import java.util.List;

/**
 * Created by senghor on 2016/1/3.
 */
public class WeatherHoursTrendView extends View {

    private List<ForecastForTenDayBean> mbeans;
    private float multipleY =6;//自定义温度偏移量倍数为8，可根据屏幕分辨率设定
    private float multipleX;
    private int radius;
    private float[] mPointXs;
    private float mHeight;
    private float originHeight;
    private Paint mPaint;
    private Paint mTextPaint;
    private Bitmap mbitmap;
    private float px40;
    float fontHeight;
    private int sumWidth;
    private Resources resources;
    private float px20;//
    private int textPx28;
    public WeatherHoursTrendView(Context context) {
        this(context,null);
    }

    public WeatherHoursTrendView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public WeatherHoursTrendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void initView(Context context){
        resources=context.getResources();
        radius=resources.getDimensionPixelOffset(R.dimen.px_9);
        multipleX=resources.getDimensionPixelOffset(R.dimen.px_140);
        textPx28=resources.getDimensionPixelOffset(R.dimen.textPx_28);
        px20 =resources.getDimensionPixelOffset(R.dimen.px_20);
        px40 =resources.getDimensionPixelOffset(R.dimen.px_40);
        float lineWidth=resources.getDimension(R.dimen.px_2);
        mbitmap=((BitmapDrawable)context.getResources().getDrawable(R.drawable.icon_weather_cloudy)).getBitmap();
        mPaint =new Paint();
        mPaint= new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mTextPaint=new Paint(mPaint);
        mTextPaint.setTextSize(textPx28);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        fontHeight = fontMetrics.bottom - fontMetrics.top;
    }
    public void setDataBean(List<ForecastForTenDayBean> beans){
       mbeans=beans;
        mPointXs=new float[beans.size()];
        for(int i=0;i<beans.size();i++){
            mPointXs[i]=multipleX/2+multipleX*i;
        }
        sumWidth=(int)(mPointXs[beans.size()-1]+multipleX/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbeans==null)return;
        mHeight= getHeight();
        originHeight=mHeight/2+100;
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
            float textWidth = mTextPaint.measureText("多云");
            float textWidth1 = mTextPaint.measureText("22 °C");
            float textWidth2= mTextPaint.measureText("14:00");
            //根据计算的文字高度绘制文字
            canvas.drawText("多云", mPointXs[i]-textWidth/2, px20 + mbitmap.getHeight() + px40, mTextPaint);
            //根据计算的文字高度绘制文字
            canvas.drawText("22 °C", mPointXs[i]-textWidth1/2, px20 + mbitmap.getHeight() + px40 +fontHeight, mTextPaint);
            //在文字上方绘制天气图片，要加上文字高度
            canvas.drawBitmap(mbitmap, mPointXs[i] - mbitmap.getWidth() / 2, px20, mPaint);
            //绘制圆点
            canvas.drawCircle(mPointXs[i], originHeight + pointY, radius, mPaint);

            //根据计算的文字高度绘制文字
            canvas.drawText("14:00", mPointXs[i]-textWidth2/2, mHeight-fontHeight, mTextPaint);
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
