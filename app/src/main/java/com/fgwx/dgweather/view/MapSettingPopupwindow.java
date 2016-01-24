package com.fgwx.dgweather.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnTouchListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseFragment;
import com.fgwx.dgweather.base.HomeCallBack;
import com.fgwx.dgweather.bean.MapSettingBean;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.google.gson.Gson;

/**
 * Created by M on 2016/1/2.
 */
public class MapSettingPopupwindow extends PopupWindow {
    private final Gson gson;
    private View layout;
    private CheckBox cb_showrealTimeData, cb_realTimeWeather, cb_disasterPoint, cb_refudge, cb_realPhotos;
    private RadioGroup rg_showType;
    private RadioButton rb_temprature, rb_humidity, rb_rainfall, rb_wind;
    private Button bt_sure;
    private LinearLayout ll_layout, ll_layout_content;

    private MapSettingBean bean;
    private HomeCallBack callBack;

    public MapSettingPopupwindow(Activity context,HomeCallBack callBack) {
        super(context);
        this.callBack = callBack;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        gson = new Gson();

        layout = inflater.inflate(R.layout.popup_map_setting, null);


        //显示实时数据
        cb_showrealTimeData = (CheckBox) layout.findViewById(R.id.cb_show_realtime_data);
        cb_showrealTimeData.setChecked(false);
        //实况天气
        cb_realTimeWeather = (CheckBox) layout.findViewById(R.id.cb_switch_weather);
        cb_realTimeWeather.setChecked(false);

        //易灾点
        cb_disasterPoint = (CheckBox) layout.findViewById(R.id.cb_switch_disaster);
        cb_disasterPoint.setChecked(false);
        //避难所
        cb_refudge = (CheckBox) layout.findViewById(R.id.cb_switch_refudge);
        cb_refudge.setChecked(false);
        //实景照片
        cb_realPhotos = (CheckBox) layout.findViewById(R.id.cb_switch_photo);
        cb_realPhotos.setChecked(false);
        //
        rg_showType = (RadioGroup) layout.findViewById(R.id.rg_mapsetting_type);
        //实时温度
        rb_temprature = (RadioButton) layout.findViewById(R.id.rb_type_temperature);
        //相对湿度
        rb_humidity = (RadioButton) layout.findViewById(R.id.rb_type_humidity);
        //实时雨量
        rb_rainfall = (RadioButton) layout.findViewById(R.id.rb_type_rainfall);
        //风力风向
        rb_wind = (RadioButton) layout.findViewById(R.id.rb_type_wind);
        bt_sure = (Button) layout.findViewById(R.id.bt_mapsetting_sure);
        ll_layout = (LinearLayout) layout.findViewById(R.id.ll_layout_mapsetting);
        ll_layout_content = (LinearLayout) layout.findViewById(R.id.ll_layout_mapsetting_content);

        String set = MPreferencesUtil.getInstance().getValue(Constant.MAPSETTING, "");
        LogUtil.e("set:"+set);
        if (set.length() != 0) {
            bean = gson.fromJson(set, MapSettingBean.class);
            cb_showrealTimeData.setChecked(bean.isRealData());
            cb_realTimeWeather.setChecked(bean.isRealWeather());
            cb_realPhotos.setChecked(bean.isRealPhoto());
            cb_refudge.setChecked(bean.isRefudge());
            cb_disasterPoint.setChecked(bean.isDisasterPoint());
            switch (bean.getSiteType()) {
                case Constant.TEM:
                    rb_temprature.setChecked(true);
                    break;
                case Constant.HUM:
                    rb_humidity.setChecked(true);
                    break;
                case Constant.RAIN:
                    rb_rainfall.setChecked(true);
                    break;
                case Constant.WIN:
                    rb_wind.setChecked(true);
                    break;
            }
//            rb_humidity
            setBean(bean);
        }else{
            bean = new MapSettingBean();
            bean.setSiteType(Constant.TEM);
            bean.setDisasterPoint(false);
            bean.setRefudge(false);
        }


        rg_showType.setOnCheckedChangeListener(new MyRidioDroupCheckedChangeListener());
        cb_showrealTimeData.setOnCheckedChangeListener(new MyCheckBoxCheckedChangeListener());
        cb_realTimeWeather.setOnCheckedChangeListener(new MyCheckBoxCheckedChangeListener());
        cb_disasterPoint.setOnCheckedChangeListener(new MyCheckBoxCheckedChangeListener());
        cb_refudge.setOnCheckedChangeListener(new MyCheckBoxCheckedChangeListener());
        cb_realPhotos.setOnCheckedChangeListener(new MyCheckBoxCheckedChangeListener());
        bt_sure.setOnClickListener(new MyClickListener());
        ll_layout.setOnClickListener(new MyClickListener());
        ll_layout_content.setOnClickListener(new MyClickListener());

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

    }

    private class MyRidioDroupCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.cb_show_realtime_data:
//                    bean.setRealData();
                    break;
                case R.id.rb_type_temperature:
                    bean.setSiteType(Constant.TEM);
                    break;
                case R.id.rb_type_humidity:
                    bean.setSiteType(Constant.HUM);
                    break;
                case R.id.rb_type_rainfall:
                    bean.setSiteType(Constant.RAIN);
                    break;
                case R.id.rb_type_wind:
                    bean.setSiteType(Constant.WIN);
                    break;
            }
        }
    }

    private class MyCheckBoxCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.cb_switch_weather:

                    break;
                case R.id.cb_switch_disaster:
                    bean.setDisasterPoint(isChecked);
                    break;
                case R.id.cb_switch_refudge:
                    bean.setDisasterPoint(isChecked);

                    break;
                case R.id.cb_switch_photo:

                    break;
            }
        }
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_mapsetting_sure:
                    bean.setRealData(cb_showrealTimeData.isChecked());
                    bean.setDisasterPoint(cb_disasterPoint.isChecked());
                    bean.setRealPhoto(cb_realPhotos.isChecked());
                    bean.setRealWeather(cb_realTimeWeather.isChecked());
                    bean.setRefudge(cb_refudge.isChecked());
                    MPreferencesUtil.getInstance().setValue(Constant.MAPSETTING, gson.toJson(bean));
                    LogUtil.e("mapsettingwindow返回值:"+bean.getSiteType());
                    callBack.callBack(bean.getSiteType(),bean.isDisasterPoint(),bean.isRefudge());
                    dismiss();
                    break;
                case R.id.ll_layout_mapsetting:
                    dismiss();
                    break;
                case R.id.ll_layout_mapsetting_content:

                    break;
            }

        }
    }

    public MapSettingBean getBean() {
        return bean;
    }

    public void setBean(MapSettingBean bean) {
        this.bean = bean;
    }
}
