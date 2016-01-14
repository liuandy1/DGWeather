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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AllenHu on 2016/1/13.
 */
public class CityAddAdapter extends BaseAdapter {
    private List list;
    private Context context;

    public CityAddAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    public CityAddAdapter(String[] citys, Context context) {
        this.list = new ArrayList();
        for (String s:citys){
            this.list.add(s);
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_add_city_dg, null);

        }
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        String city = (String) list.get(position);
        textView.setText(city);
        textView.setLayoutParams(new GridView.LayoutParams(75, 75));//设置ImageView对象布局
        textView.setPadding(8, 8, 8, 8);//设置间距
//        imageView.setImageResource(imgs[position]);//为ImageView设置图片资源
        return convertView;
    }
}
