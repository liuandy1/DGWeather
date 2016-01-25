package com.fgwx.dgweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.AddCityActivity;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.LivingIndexAdapter;
import com.fgwx.dgweather.base.WeatherAppContext;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.ForecastForHourBean;
import com.fgwx.dgweather.bean.ForecastForTenDayBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.utils.AddedCityUtil;
import com.fgwx.dgweather.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import static com.fgwx.dgweather.R.id;
import static com.fgwx.dgweather.R.layout;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastSecondView extends RelativeLayout implements View.OnClickListener, ObserverHorizontalScrollView.HorizontalScrollViewListener ,ObserverHorizontalScrollView.OnBottomAndTopListener
,WeatherHorizatalScrollView.OnBottomAndTopListener{

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
    private TextView mTvSunRise;
    private TextView mTvSunSet;
    private TextView mTvCursor;
    private WhitePointView mWhitePointView;
    private int perHourWidth;
    private WeatherSunChangeView mWeatherSunChangeView;
    private AdapterScroListView aslv_LivingIndex;//生活指数
    private LivingIndexAdapter livingIndexAdapter;
    private List<ForecastForTenDayBean> mDaybeans;
    private List<ForecastForHourBean> mHourBeans;
    private String currentCityName="";
    private List<PerDayWeatherView> mPerDayWeatherViews = new ArrayList<>();

    private List<CityBean> cityBeans;
    private int cursor=-1;

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
        perHourWidth = getResources().getDimensionPixelOffset(R.dimen.px_140);
        cityBeans=AddedCityUtil.getAllCity(getContext());
        if(mMainActivity.currentCityId.equals("0")){
            cursor=-1;
        }else {
          for(int i=0;i<cityBeans.size();i++){
              if(mMainActivity.currentCityId.equals(cityBeans.get(i).getId())){
                  cursor=i;
                  break;
              }
          }
        }
        View view = LayoutInflater.from(getContext()).inflate(layout.fragment_second_forecast, this);
        initView(view);
    }

    public void setSecondForecastData(HomeForecastBaseBean homeForecastBaseBean) {
        if (homeForecastBaseBean.getData() != null && homeForecastBaseBean.getData().getDays() != null)
            mDaybeans = homeForecastBaseBean.getData().getDays();
        if (mDaybeans.size() > 10)
            mDaybeans = mDaybeans.subList(0, 10);
        mWeathDayTrendView.setDataBean(mDaybeans);
        mHourBeans = homeForecastBaseBean.getData().getExacts();
        setPerDayWeatherData();
        currentCityName=homeForecastBaseBean.getData().getCityName() + "";
        mTvCityName.setText(currentCityName);
        //精确数据
        mWeatherHoursTrendView.setDataBean(mHourBeans);
        if (mHourBeans != null) {
            mTvCursor.setText(mHourBeans.get(0).getWeaDesc() + " " + mHourBeans.get(0).getTempDesc() + "℃");
        }
        livingIndexAdapter = new LivingIndexAdapter(mMainActivity, homeForecastBaseBean.getData().getLife());
        aslv_LivingIndex.setAdapter(livingIndexAdapter);
        if (homeForecastBaseBean.getData().getSun() != null) {
            mTvSunRise.setText("日出" + TimeUtil.hourstrToDateStr(homeForecastBaseBean.getData().getSun().getSunriseTime()));
            mTvSunSet.setText("日落" + TimeUtil.hourstrToDateStr(homeForecastBaseBean.getData().getSun().getSunsetTime()));
            mWeatherSunChangeView.setSunTimeData(homeForecastBaseBean.getData().getSun());
        }
    }

    private void setPerDayWeatherData() {
        for (int i = 0; i < mDaybeans.size(); i++) {
            mPerDayWeatherViews.get(i).setPerDayData(mDaybeans.get(i));
        }
    }

    public void initView(View view) {
        padding = getResources().getDimensionPixelOffset(R.dimen.px_20);
        mWeatherHoursTrendView = (WeatherHoursTrendView) view.findViewById(id.wtv_hour_trend_view);
        mWeathDayTrendView = (WeatherDayTrendView) view.findViewById(id.wtv_day_trend_view);
        mHorizontalScrollView = (ObserverHorizontalScrollView) view.findViewById(id.scr_observer_scroll_view);
        mPerHourWeatherLayout = (LinearLayout) view.findViewById(id.ll_scroll_text);
        aslv_LivingIndex = (AdapterScroListView) view.findViewById(id.aslv_livingIndex);
        mTvPerHourWeather = (TextView) view.findViewById(id.tv_per_day_text_show);
        mRlPerHourWeather = (RelativeLayout) view.findViewById(id.rl_per_hour_weather);
        mTvCityName = (TextView) view.findViewById(R.id.tv_city_name);
        mTvCityName.setOnClickListener(this);
        mHorizontalScrollView.setHorizontalScrollViewListener(this);
        mHorizontalScrollView.setOnBottomAndTopListener(this);
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
        mTvSunRise = (TextView) findViewById(id.tv_life_sunriseTime);
        mTvSunSet = (TextView) findViewById(id.tv_life_sunsetTime);
        mTvCursor = (TextView) findViewById(id.tv_per_day_text_show);
        mWhitePointView= (WhitePointView) findViewById(id.white_point_view);
        mWeatherSunChangeView = (WeatherSunChangeView) findViewById(id.sv_weather_sun_change);
        mWhitePointView.setPointNum(cityBeans.size()+1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void onWeatherScrollChanged(int x, int y, int oldx, int oldy) {
        factor = (float) (mPerHourWeatherLayout.getWidth() - mTvPerHourWeather.getWidth() - padding) / (mRlPerHourWeather.getWidth() - mPerHourWeatherLayout.getWidth());
        int temp = (int) (-x * factor);
        int cursor = (Math.abs(x) + Math.abs(temp) + padding / 2) / perHourWidth;
        if(mHourBeans!=null&&mHourBeans.get(cursor)!=null){
        mTvCursor.setText(mHourBeans.get(cursor).getWeaDesc() + " " + mHourBeans.get(cursor).getTempDesc() + "℃");
        }
        mPerHourWeatherLayout.scrollTo(temp, y);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case id.tv_city_name:
                AddCityActivity.starAddCityActivity(mMainActivity,currentCityName);
                break;

        }
    }

    @Override
    public void onBottom() {

    }

    @Override
    public void onTop() {

    }

    @Override
    public void onWeatherBottom() {
        Toast.makeText(WeatherAppContext.getAppContext(), "到达底部", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeatherTop() {
        Toast.makeText(WeatherAppContext.getAppContext(), "到达顶部", Toast.LENGTH_SHORT).show();
    }
}
