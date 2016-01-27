package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.fgwx.dgweather.base.WeatherAppContext;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ObserverHorizontalScrollView extends HorizontalScrollView{
	  
	   private HorizontalScrollViewListener scrollViewListener;
	   public ObserverHorizontalScrollView(Context context) {  
	        this(context,null);  
	    }  
	    public ObserverHorizontalScrollView(Context context, AttributeSet attrs,  
	            int defStyle) {  
	        super(context, attrs, defStyle);
	        
	    }  
	    public ObserverHorizontalScrollView(Context context, AttributeSet attrs) {  
	        this(context, attrs,0);  
	    }
	private OnBottomAndTopListener onBottomAndTopListener;
	private int width;
	private int scrollViewMeasuredWidth;
	private float beginX;
	private int flag=0;

	public void setOnBottomAndTopListener(OnBottomAndTopListener onBottomAndTopListener){
		this.onBottomAndTopListener=onBottomAndTopListener;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width=getWidth();
		scrollViewMeasuredWidth=getChildAt(0).getMeasuredWidth();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		float x = ev.getX();
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.v("weather", "down four" + x);
				//加上下滑阻力与实际滑动距离的差（降噪音）
				beginX = x;
				break;
			case MotionEvent.ACTION_MOVE:
				float dx = x - beginX;
				int scrollX=getScrollX();
				if(scrollX==0&&Math.abs(dx)>30){
					flag=1;
				}
				if((scrollX+width)==scrollViewMeasuredWidth&&Math.abs(dx)>30){
					flag=2;
				}
				break;
			case MotionEvent.ACTION_UP:
				int scx=getScrollX();
				if(scx==0&&flag==1){
					Toast.makeText(WeatherAppContext.getAppContext(), "到达顶部", Toast.LENGTH_SHORT).show();
					if(onBottomAndTopListener!=null){
						onBottomAndTopListener.onTop();
					}
				}
				if((scx+width)==scrollViewMeasuredWidth&&flag==2){
					if(onBottomAndTopListener!=null){
						onBottomAndTopListener.onBottom();
					}
				}
				flag=0;
				break;
			default:
				break;
		}
		return super.onTouchEvent(ev);
	}
	    
	    public void setHorizontalScrollViewListener(HorizontalScrollViewListener scrollViewListener){
	    	this.scrollViewListener=scrollViewListener;
	    }
	    @Override  
	    protected void onScrollChanged(int x, int y, int oldx, int oldy) {  
	        super.onScrollChanged(x, y, oldx, oldy);  
	        if (scrollViewListener != null) {  
	            scrollViewListener.onWeatherScrollChanged(x, y, oldx, oldy);
	        }  
	    }  

	public interface HorizontalScrollViewListener{
		public void onWeatherScrollChanged(int x, int y, int oldx, int oldy);
	}
	public interface OnBottomAndTopListener{
		public void onBottom();
		public void onTop();

	}
}
