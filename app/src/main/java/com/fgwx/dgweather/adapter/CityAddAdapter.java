package com.fgwx.dgweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AllenHu on 2016/1/13.
 */
public class CityAddAdapter extends BaseAdapter {
    private List<CityBean> list;
    private Context context;

    public CityAddAdapter(Context context, List<CityBean> beans) {
        LogUtil.e("bbb", beans.size() + "");
        this.list = beans;
        this.context = context;
    }

    @Override
    public int getCount() {
        LogUtil.e("aaa", list.size() + "");
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_add_city_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_city);
        String city = list.get(position).getName();
        textView.setText(city);
        return convertView;
    }
}
