package com.fgwx.dgweather.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgwx.dgweather.R;

public class LivingIndexAdapter extends BaseAdapter {
    private Activity context;
    private String[] info;

    private int[] imgIcon = new int[]{R.drawable.icon_life_air_index, R.drawable.icon_life_comfort_index, R.drawable.icon_life_umbrella_index, R.drawable.icon_life_exercise_index, R.drawable.icon_life_outing_index, R.drawable.icon_life_moldew_index, R.drawable.icon_life_dry_index, R.drawable.icon_life_trafic_index};
    private String[] txtIndex;

    public LivingIndexAdapter(Activity context, String[] info) {
        super();
        this.context = context;
        this.info = info;
        txtIndex = new String[]{context.getResources().getString(R.string.air_index), context.getResources().getString(R.string.comfort_of_human_body), context.getResources().getString(R.string.umbrella_index), context.getResources().getString(R.string.exercise_index), context.getResources().getString(R.string.outing_index), context.getResources().getString(R.string.mildew_index), context.getResources().getString(R.string.dry_index), context.getResources().getString(R.string.trafic_index)};
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        int count = 0;
        if (info != null) {
            count = info.length;
        }
        return count;
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        String item = null;
        if (info.length > 0) {
            item = info[position];
        }
        return item;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_forecast_living_index_item, null);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_life_icon);
            viewHolder.tv_index = (TextView) convertView.findViewById(R.id.tv_life_index);
            viewHolder.tv_indexInfo = (TextView) convertView.findViewById(R.id.tv_life_info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // set item values to the viewHolder
        String item = getItem(position);
        if (null != item) {
            viewHolder.iv_icon.setBackgroundResource(imgIcon[position]);
            if (txtIndex[position].length() == 5) {
                viewHolder.tv_index.setPadding(10, 10, 0, 10);
            }
            viewHolder.tv_index.setText(txtIndex[position]);
            viewHolder.tv_indexInfo.setText(item);
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView iv_icon;
        TextView tv_index, tv_indexInfo;
    }

}
