package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.LivingIndexAdapter;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;

import java.util.ArrayList;
import java.util.List;

import static com.fgwx.dgweather.R.id;
import static com.fgwx.dgweather.R.layout;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastSecondView extends RelativeLayout implements View.OnClickListener,ObserverHorizontalScrollView.HorizontalScrollViewListener{

    private MainActivity mMainActivity;
    private WeatherHoursTrendView mWeatherHoursTrendView;
    private WeatherDayTrendView mWeathDayTrendView;
    private RelativeLayout mRootScrollView;
    private ObserverHorizontalScrollView mHorizontalScrollView;
    private int padding;
    private LinearLayout mPerHourWeatherLayout;
    private TextView mTvPerHourWeather;
    private RelativeLayout mRlPerHourWeather;
    private AdapterScroListView aslv_LivingIndex;//生活指数
    private LivingIndexAdapter livingIndexAdapter ;

    public ForecastSecondView(Context context) {
        this(context, null);
    }
    public ForecastSecondView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ForecastSecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMainActivity= (MainActivity) context;
        init();
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
    private void init() {
        View view=LayoutInflater.from(getContext()).inflate(layout.fragment_second_forecast, this);
        initView(view);
    }

    public void setSecondForecastData(HomeForecastBaseBean homeForecastBaseBean){
        if(homeForecastBaseBean.getData()!=null&&homeForecastBaseBean.getData().getDays()!=null)
        mWeathDayTrendView.setDataBean(homeForecastBaseBean.getData().getDays());

        livingIndexAdapter = new LivingIndexAdapter(mMainActivity,homeForecastBaseBean.getData().getLife());
        aslv_LivingIndex.setAdapter(livingIndexAdapter);
    }
    public void initView(View view){
        padding=getResources().getDimensionPixelOffset(R.dimen.px_20);
        mWeatherHoursTrendView= (WeatherHoursTrendView) view.findViewById(id.wtv_hour_trend_view);
        mWeathDayTrendView= (WeatherDayTrendView) view.findViewById(id.wtv_day_trend_view);
        mHorizontalScrollView= (ObserverHorizontalScrollView) view.findViewById(id.scr_observer_scroll_view);
        mPerHourWeatherLayout= (LinearLayout) view.findViewById(id.ll_scroll_text);
        aslv_LivingIndex = (AdapterScroListView) view.findViewById(id.aslv_livingIndex);
        mTvPerHourWeather= (TextView) view.findViewById(id.tv_per_day_text_show);
        mRlPerHourWeather = (RelativeLayout) view.findViewById(id.rl_per_hour_weather);
        mHorizontalScrollView.setHorizontalScrollViewListener(this);
        //mRootScrollView= (RelativeLayout) view.findViewById(R.id.scroll_forecast_more);

        List<ForecastForTenDayBean> beans=new ArrayList<>();
        ForecastForTenDayBean bean;
        for(int i=0;i<48;i++){
            bean=new ForecastForTenDayBean();
            bean.setCurMaxTemp(20 + i);
            bean.setCurMinTemp(20 - i * 2);
            beans.add(bean);
        }
        mWeatherHoursTrendView.setDataBean(beans);
        mWeathDayTrendView.setDataBean(beans);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void onWeatherScrollChanged(int x, int y, int oldx, int oldy) {
        factor=(float)(mPerHourWeatherLayout.getWidth()-mTvPerHourWeather.getWidth()-padding)/(mRlPerHourWeather.getWidth()-mPerHourWeatherLayout.getWidth());
        int temp=(int)(-x*factor);
        mPerHourWeatherLayout.scrollTo(temp, y);
    }

    @Override
    public void onClick(View v) {
      /*  switch (v.getId()){
            case R.id.bnt_back_home:

                break;

        }*/
    }
}
