package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastSecondView extends RelativeLayout implements View.OnClickListener {

    private MainActivity mMainActivity;
    private WeatherHoursTrendView mWeatherHoursTrendView;
    private WeatherDayTrendView mWeathDayTrendView;
    private RelativeLayout mRootScrollView;

    public ForecastSecondView(Context context) {
        this(context, null);
    }

    public ForecastSecondView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForecastSecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMainActivity = (MainActivity) context;
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_second_forecast, this);
        initView(view);
    }

    public void setSecondForecastData(HomeForecastBaseBean homeForecastBaseBean) {
        if (homeForecastBaseBean.getData() != null && homeForecastBaseBean.getData().getDays() != null)
            mWeathDayTrendView.setDataBean(homeForecastBaseBean.getData().getDays());
    }

    public void initView(View view) {
        mWeatherHoursTrendView = (WeatherHoursTrendView) view.findViewById(R.id.wtv_hour_trend_view);
        mWeathDayTrendView = (WeatherDayTrendView) view.findViewById(R.id.wtv_day_trend_view);
        //mRootScrollView= (RelativeLayout) view.findViewById(R.id.scroll_forecast_more);
        List<ForecastForTenDayBean> beans = new ArrayList<>();
        ForecastForTenDayBean bean;
        for (int i = 0; i < 6; i++) {
            bean = new ForecastForTenDayBean();
            bean.setCurMaxTemp(20 + i * 2);
            bean.setCurMinTemp(20 - i * 2);
            beans.add(bean);
        }
        mWeatherHoursTrendView.setDataBean(beans);
        mWeathDayTrendView.setDataBean(beans);
    }

    @Override
    public void onClick(View v) {
      /*  switch (v.getId()){
            case R.id.bnt_back_home:

                break;

        }*/
    }
}
