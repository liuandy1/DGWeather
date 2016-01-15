package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
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
}
