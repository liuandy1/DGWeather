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

import com.fgwx.dgweather.R;

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
        mPaint.setStrokeWidth((float) 4.0);
        mPaint.setStyle(Paint.Style.STROKE);
        effects = new DashPathEffect(new float[] { 6, 6}, 1);
        mPaint.setPathEffect(effects);
        mPath=new Path();
        mAlphaPaint=new Paint(mPaint);
        mAlphaPaint.setColor(Color.parseColor("#64ffffff"));
        //mPath1=new Path();

    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        mWidth=getWidth();
        mHeight=getHeight();
        rect=new RectF(marginWidth,marginHeight,mWidth-marginWidth,mHeight*2f+marginHeight);
        canvas.drawArc(rect, 180+60, 180-60, false, mPaint);
        mAlphaPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rect, 180, 60, true, mAlphaPaint);
        mPath.addArc(rect, 180, 60);
        PathMeasure pathMeasure=new PathMeasure(mPath, false);
        pathMeasure.getPosTan(pathMeasure.getLength(), mCurrentPoint, null);
        canvas.drawLine(0, mHeight, mWidth, mHeight, mPaint);
        canvas.drawBitmap(mBitmap, mCurrentPoint[0]-BitmapWidth/2,mCurrentPoint[1]-BitmapHeight/2,mPaint);
    }
}
