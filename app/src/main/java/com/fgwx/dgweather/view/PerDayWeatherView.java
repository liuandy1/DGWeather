package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.utils.TimeUtil;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class PerDayWeatherView extends RelativeLayout{

    private TextView mTvWeatherDescrip;
    private ImageView mIvWeatherIcon;
    private TextView mTvWindDescrip;
    private TextView mTvMinTemDescrip;
    private TextView mTvMaxTemDescrip;
    private ForecastForTenDayBean mBean;
    public PerDayWeatherView(Context context) {
        this(context, null);
    }
    public PerDayWeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PerDayWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_per_day_weather_item, this);
        mTvWeatherDescrip=(TextView)findViewById(R.id.tv_per_day_weather_description);
        mIvWeatherIcon= (ImageView) findViewById(R.id.iv_icon_per_day_weather);
        mTvMinTemDescrip = (TextView) findViewById(R.id.iv_per_day_weather_min_temp);
        mTvMaxTemDescrip = (TextView) findViewById(R.id.iv_per_day_weather_max_temp);
        mTvWindDescrip=(TextView)findViewById(R.id.tv_per_day_weather_wind_description);
    }
    public void setPerDayData(ForecastForTenDayBean bean){
        if(bean==null)return;
        mBean=bean;
        mTvMinTemDescrip.setText(mBean.getCurMinTemp()+"°C");
        mTvMaxTemDescrip.setText(mBean.getCurMaxTemp()+"°C");
        mTvWindDescrip.setText(mBean.getWindDirName()+mBean.getWindSpeedName()+"\n"+mBean.getWindSpeedLevel());
        mTvWeatherDescrip.setText(TimeUtil.perDaystrToDateStr(mBean.getCurDate())+"\n"+mBean.getWeaDesc());

    }

}
