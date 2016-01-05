package com.fgwx.dgweather.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fgwx.dgweather.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by M on 2016/1/2.
 */
public class MultiWarnPopupwindow extends PopupWindow {
    private View layout;
    private TextView tv_warnNum;
    private GridView gv_warn;
    private Button bt_detailInfo, bt_iKnow;
    LinearLayout ll_layoutAll, ll_layoutWarn;

    private Context context;

    private int[] warnImage = {R.drawable.icon_warn_cold_orange, R.drawable.icon_warn_cold_orange, R.drawable.icon_warn_cold_orange, R.drawable.icon_warn_cold_orange, R.drawable.icon_warn_cold_orange};

    public MultiWarnPopupwindow(Activity context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.popup_multiple_warn, null);
        tv_warnNum = (TextView) layout.findViewById(R.id.tv_multiWarn_num);
        gv_warn = (GridView) layout.findViewById(R.id.gv_multiWarn);
        bt_detailInfo = (Button) layout.findViewById(R.id.bt_multiWarn_detailInfo);
        bt_iKnow = (Button) layout.findViewById(R.id.bt_multiwarn_iKnow);
        ll_layoutAll = (LinearLayout) layout.findViewById(R.id.ll_layout_multiWarnAll);
        ll_layoutWarn = (LinearLayout) layout.findViewById(R.id.ll_layout_multiWarn);

        bt_detailInfo.setOnClickListener(new MyClickListener());
        bt_iKnow.setOnClickListener(new MyClickListener());
        ll_layoutAll.setOnClickListener(new MyClickListener());
        ll_layoutWarn.setOnClickListener(new MyClickListener());

        MyMultiWarnAdapter adaper = new MyMultiWarnAdapter();
        gv_warn.setAdapter(adaper);

        // 设置PopupWindow的View
        this.setContentView(layout);
        // 设置PopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置PopupWindow弹出窗体的高LayoutParams.WRAP_CONTENT
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置PopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xa0000000);
        // 设置PopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.update();
        // layout添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = layout.findViewById(R.id.ll_layout_multiWarn)
                        .getHeight();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_multiWarn_detailInfo:
                    break;
                case R.id.bt_multiwarn_iKnow:
                    dismiss();
                    break;
                case R.id.ll_layout_multiWarn:

                    break;
                case R.id.ll_layout_multiWarnAll:
                    dismiss();
                    break;
            }

        }
    }

    public class MyMultiWarnAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return warnImage.length;
        }

        @Override
        public Object getItem(int position) {
            return warnImage[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.popup_multiple_warn_item, null);
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_multiWarn_img);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.iv_icon.setBackgroundResource(warnImage[position]);

            return convertView;
        }

    }

    private static class ViewHolder {
        ImageView iv_icon;
    }
}
