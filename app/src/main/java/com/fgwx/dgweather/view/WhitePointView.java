package com.fgwx.dgweather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.fgwx.dgweather.R;

/**
 * Created by senghor on 2016/1/23.
 */
public class WhitePointView extends View {

    private int white=0xaaFFFFFF;
    private int gray=0xff000000;
    private int currentNum=0;
    private int mPointNum=0;
    private Paint mPaint;
    private float[] mPointXs;
    private int multipleX;
    private int radius;
    private float sumWidth;
    private int mheight;
    public WhitePointView(Context context) {
        this(context,null);
    }
    public WhitePointView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public WhitePointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    private void init(){
        radius=getResources().getDimensionPixelOffset(R.dimen.px_4);
        multipleX=getResources().getDimensionPixelOffset(R.dimen.px_10);
        mPaint = new Paint();
        mPaint.setColor(white);
        setPointNum(5,0);

    }

    public void setPointNum(int pointNum,int currentNum){
        mPointNum=pointNum;
        this.currentNum=currentNum;
        mPointXs=new float[pointNum];
        for(int i=0;i<pointNum;i++){
            mPointXs[i]=multipleX/2+(multipleX*1.5f*i);
        }
        sumWidth=(int)(mPointXs[pointNum-1]+multipleX/2);
        postInvalidate();
        forceLayout();
        requestLayout();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<mPointNum;i++){
        //canvas.drawCircle();
            if(i==currentNum){
                mPaint.setColor(gray);
                canvas.drawCircle(mPointXs[i], mheight/2, radius, mPaint);
            }
            mPaint.setColor(white);
            canvas.drawCircle(mPointXs[i], mheight/2, radius, mPaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        mheight =heightSize;
        setMeasuredDimension((int)sumWidth, heightSize);
    }
}
