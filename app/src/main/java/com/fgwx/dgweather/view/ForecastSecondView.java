package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.CityManagerActivity;
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
    private float factor;
    private LinearLayout mPerHourWeatherLayout;
    private TextView mTvPerHourWeather;
    private RelativeLayout mRlPerHourWeather;
    private TextView mTvCityName;
    private AdapterScroListView aslv_LivingIndex;//生活指数
    private LivingIndexAdapter livingIndexAdapter ;
     private List<ForecastForTenDayBean> mDaybeans;
    private List<PerDayWeatherView> mPerDayWeatherViews=new ArrayList<>();
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
            mDaybeans=homeForecastBaseBean.getData().getDays();
        if(mDaybeans.size()>10)
            mDaybeans=mDaybeans.subList(0,10);
        mWeathDayTrendView.setDataBean(mDaybeans);
        setPerDayWeatherData();
        mTvCityName.setText(homeForecastBaseBean.getData().getCityName()+"");
        //精确数据
        mWeatherHoursTrendView.setDataBean(homeForecastBaseBean.getData().getExacts());
        livingIndexAdapter = new LivingIndexAdapter(mMainActivity,homeForecastBaseBean.getData().getLife());
        aslv_LivingIndex.setAdapter(livingIndexAdapter);
    }
    private void setPerDayWeatherData(){
        for(int i=0;i<mDaybeans.size();i++){
            mPerDayWeatherViews.get(i).setPerDayData(mDaybeans.get(i));
        }
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
        mTvCityName=(TextView)view.findViewById(R.id.tv_city_name);
        mTvCityName.setOnClickListener(this);
        mHorizontalScrollView.setHorizontalScrollViewListener(this);
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_1));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_2));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_3));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_4));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_5));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_6));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_7));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_8));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_9));
        mPerDayWeatherViews.add((PerDayWeatherView) findViewById(id.wv_per_day_10));
        //mRootScrollView= (RelativeLayout) view.findViewById(R.id.scroll_forecast_more);

     /*   List<ForecastForTenDayBean> beans=new ArrayList<>();
        ForecastForTenDayBean bean;
        for(int i=0;i<48;i++){
            bean=new ForecastForTenDayBean();
            bean.setCurMaxTemp(20 + 1);
            bean.setCurMinTemp(20 - i * 2);
            beans.add(bean);
        }
        mWeatherHoursTrendView.setDataBean(beans);*/
        /*mWeathDayTrendView.setDataBean(beans);
*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void onWeatherScrollChanged(int x, int y, int oldx, int oldy) {
        factor=(float)(mPerHourWeatherLayout.getWidth()-mTvPerHourWeather.getWidth()-padding)/(mRlPerHourWeather.getWidth()-mPerHourWeatherLayout.getWidth());
        int temp = (int)(-x*factor);
        mPerHourWeatherLayout.scrollTo(temp, y);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case id.tv_city_name:
                CityManagerActivity.starCityManagerActivity(mMainActivity);
                break;

        }
    }
}
