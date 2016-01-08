package com.fgwx.dgweather.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;


/**
 * pixel util
 * 
 *
 */
public class  DipPixUtil {

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static class DisplayRect {
		private int width;

		private int height;

		public DisplayRect(int width, int height) {
			this.width = width;
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

	public static DisplayRect getWindowDiaplay(Context context) {
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		DisplayRect rect = new DisplayRect(display.getWidth(), display.getHeight());
		return rect;
	}

	public static int weatherPx2Dp(Context context,int px) {
	return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,context.getResources().getDisplayMetrics());
}
	public static int weatherPx2Dp(float px){
		return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px, Resources.getSystem().getDisplayMetrics());
	}
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 * @param fontScale（DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(float pxValue, float fontScale) {
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 * @param fontScale（DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(float spValue, float fontScale) {
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取通知栏的高度
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context){
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}
}
