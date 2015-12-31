package com.fgwx.dgweather.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ExitAppUtils {
	private List<Activity> mActivityList = new LinkedList<Activity>();
	private static ExitAppUtils instance = new ExitAppUtils();
	private ExitAppUtils(){}
	/**
	 * @return
	 */
	public static ExitAppUtils getInstance(){
		return instance;
	}
	/**
	 * @param activity
	 */
	public void addActivity(Activity activity){
		mActivityList.add(activity);
	}
	/**
	 * @param activity
	 */
	public void delActivity(Activity activity){
		mActivityList.remove(activity);
	}
	
	/**
	 */
	public void exit(){
		for(Activity activity : mActivityList){
			activity.finish();
		}
		System.exit(0);
	}
	

}
