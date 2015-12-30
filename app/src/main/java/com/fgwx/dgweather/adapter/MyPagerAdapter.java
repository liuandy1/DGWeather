package com.fgwx.dgweather.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 主页轮播的适配器
 */
public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<View> list;
    public MyPagerAdapter(ArrayList<View> list){
        this.list = list;
    }

    @Override
    /**
     * 获取view的数量
     */
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

    @Override
    /**
     *  获取相应位置上的view
     *  container view容器，其实就是viewpager本身
     */
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
//		super.destroyItem(container, position, object);
        container.removeViewInLayout((View) object);
        object = null;
    }


}
