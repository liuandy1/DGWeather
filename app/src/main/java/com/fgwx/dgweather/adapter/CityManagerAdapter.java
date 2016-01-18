package com.fgwx.dgweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgwx.dgweather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AllenHu on 2016/1/13.
 */
public class CityManagerAdapter extends BaseAdapter {
    private Context context;
    private int[] imageIcon = new int[]{R.drawable.icon_weather_1, R.drawable.icon_weather_2, R.drawable.icon_weather_3, R.drawable.icon_weather_4, R.drawable.icon_weather_5};
    private String[] area = new String[]{"东莞市", "长沙市", "益阳市", "黑龙江市", "吉林省"};
    private int[] temp = new int[]{14, 21, 14, 22, 16};

    public CityManagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return temp.length;
    }

    @Override
    public Object getItem(int position) {
        return temp[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_location = null, iv_weatherIcon = null;
        TextView tv_area = null, tv_temp = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_city_manager_item, null);
            iv_location = (ImageView) convertView.findViewById(R.id.iv_location);
            iv_weatherIcon = (ImageView) convertView.findViewById(R.id.iv_weatherIcon);
            tv_area = (TextView) convertView.findViewById(R.id.tv_area);
            tv_temp = (TextView) convertView.findViewById(R.id.tv_temp);
            iv_weatherIcon.setBackgroundResource(imageIcon[position]);
        }

        tv_area.setText(area[position]);
        tv_temp.setText(temp[position] + "℃");
        return convertView;
    }
}
