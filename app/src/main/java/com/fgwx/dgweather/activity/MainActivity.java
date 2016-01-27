package com.fgwx.dgweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.base.WeatherAppContext;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.fragment.EarlyWarnFragment;
import com.fgwx.dgweather.fragment.ForecastFragment;
import com.fgwx.dgweather.fragment.InteractFragment;
import com.fgwx.dgweather.fragment.MineFragment;
import com.fgwx.dgweather.fragment.MonitorFragment;
import com.fgwx.dgweather.utils.AddedCityUtil;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.ExitAppUtils;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.ScreenShootUtil;
import com.fgwx.dgweather.utils.SiteUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by senghor on 2015/12/23.
 */
public class MainActivity extends BaseActivity {

    private RadioGroup rg_tabs;

    private RadioButton rb_forecast, rb_warn, rb_monitor, rb_interact, rb_mine;

    private List<Fragment> fragments = new ArrayList<Fragment>();

    private FragmentManager manager;

    private ForecastFragment mForecastFragment;

    private EarlyWarnFragment mEarlyWarnFragment;

    private MonitorFragment mMonitorFragment;

    private InteractFragment mInteractFragment;

    private MineFragment mMineFragment;

    private Gson gson;

    public static int nowPager = 0;
    //定位的城市
    public CityBean homeCity;
    //当前的城市
    public CityBean nowCity;
    //当前的站点
    public SiteBean.DataEntity nowSite;

    private static boolean isRefresh;

    public static final String FORECAST_TAG = "Forecast";

    public static final String EARLYWARN_TAG = "EarlyWarn";

    public static final String MONITOR_TAG = "Monitor";

    public static final String INTERACT_TAG = "Interact";

    public static final String MINE_TAG = "Mine";

    /**
     * 用于对Fragment进行管理
     */

    public String currentCityId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFragmentSave(savedInstanceState);
        init();
        setTabSelection(0);
        currentCityId = getIntent().getStringExtra(Constant.CITYID);
        if (TextUtils.isEmpty(currentCityId)) {
            currentCityId = "0";
        }
        rg_tabs.setOnCheckedChangeListener(new onRadioGroupListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh) {
            mForecastFragment.initViewPager(AddedCityUtil.getAllCity(this).size());
            isRefresh = false;
        }
    }

    public void isFragmentSave(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mForecastFragment = (ForecastFragment) manager.findFragmentByTag(FORECAST_TAG);
            mEarlyWarnFragment = (EarlyWarnFragment) manager.findFragmentByTag(EARLYWARN_TAG);
            mMineFragment = (MineFragment) manager.findFragmentByTag(MINE_TAG);
            mMonitorFragment = (MonitorFragment) manager.findFragmentByTag(MONITOR_TAG);
            mInteractFragment = (InteractFragment) manager.findFragmentByTag(INTERACT_TAG);
        }
    }

    public static void starMainActivity(BaseActivity context, String cityId) {
        isRefresh = true;
        nowPager = 0;
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constant.CITYID, cityId);
        context.startActivity(intent);
        context.finish();
    }

    private class onRadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkId) {
            // TODO Auto-generated method stub
            switch (group.getCheckedRadioButtonId()) {
                case R.id.rb_tab_forecast:
                    // 当点击了预报时，选中第1个tab
                    setTabSelection(0);
                    break;
                case R.id.rb_tab_warn:
                    // 当点击了预警tab时，选中第2个tab
                    setTabSelection(1);
                    break;
                case R.id.rb_tab_monitor:
                    // 当点击了找监测tab时，选中第3个tab
                    setTabSelection(2);
                    break;
                case R.id.rb_tab_interact:
                    // 当点击了互动tab时，选中第4个tab
                    setTabSelection(3);
                    break;
                case R.id.rb_tab_mine:
                    // 当点击了我的tab时，选中第5个tab
                    setTabSelection(4);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mForecastFragment != null) {
            transaction.hide(mForecastFragment);
        }
        if (mEarlyWarnFragment != null) {
            transaction.hide(mEarlyWarnFragment);
        }
        if (mMonitorFragment != null) {
            transaction.hide(mMonitorFragment);
        }
        if (mInteractFragment != null) {
            transaction.hide(mInteractFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示预报，1表示预警，2表示监测，3表示互动，4表示我的。
     */
    public void setTabSelection(int index) {
        // 每次选中之前先清除掉上次的选中状态
        rg_tabs.clearFocus();
        // 开启一个Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了预报tab时，改变控件的图片和文字颜色
                rb_forecast.setChecked(true);
                if (mForecastFragment == null) {
                    // 如果mForecastFragment为空，则创建一个并添加到界面上
                    mForecastFragment = new ForecastFragment();
                    transaction.add(R.id.fl_tab_content, mForecastFragment, FORECAST_TAG);
                } else {
                    // 如果mForecastFragment不为空，则直接将它显示出来
                    transaction.show(mForecastFragment);
                }
                break;
            case 1:
                // 当点击了预警tab时，改变控件的图片和文字颜色
                rb_warn.setChecked(true);
                if (mEarlyWarnFragment == null) {
                    // 如果mEarlyWarnFragment为空，则创建一个并添加到界面上
                    mEarlyWarnFragment = new EarlyWarnFragment();
                    transaction.add(R.id.fl_tab_content, mEarlyWarnFragment, EARLYWARN_TAG);
                } else {
                    // 如果mEarlyWarnFragment不为空，则直接将它显示出来
                    transaction.show(mEarlyWarnFragment);
                }
                break;

            case 2:
                // 当点击了监测tab时，改变控件的图片和文字颜色
                rb_monitor.setChecked(true);
                if (mMonitorFragment == null) {
                    // 如果mMonitorFragment为空，则创建一个并添加到界面上
                    mMonitorFragment = new MonitorFragment();
                    transaction.add(R.id.fl_tab_content, mMonitorFragment, MONITOR_TAG);
                } else {
                    // 如果mMonitorFragment不为空，则直接将它显示出来
                    transaction.show(mMonitorFragment);
                }
                break;

            case 3:
                // 当点击了互动tab时，改变控件的图片和文字颜色
                rb_interact.setChecked(true);
                if (mInteractFragment == null) {
                    // 如果mInteractFragment为空，则创建一个并添加到界面上
                    mInteractFragment = new InteractFragment();
                    transaction.add(R.id.fl_tab_content, mInteractFragment, INTERACT_TAG);
                } else {
                    // 如果mInteractFragment不为空，则直接将它显示出来
                    transaction.show(mInteractFragment);
                }
                break;
            case 4:
                // 当点击了我的tab时，改变控件的图片和文字颜色
                rb_mine.setChecked(true);
                if (mMineFragment == null) {
                    // 如果mMineFragment为空，则创建一个并添加到界面上
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.fl_tab_content, mMineFragment, MINE_TAG);
                } else {
                    // 如果mMineFragment不为空，则直接将它显示出来
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    // 初始化控件
    public void init() {
        gson = new Gson();
        rg_tabs = (RadioGroup) findViewById(R.id.rg_tab);
        rb_forecast = (RadioButton) findViewById(R.id.rb_tab_forecast);
        rb_warn = (RadioButton) findViewById(R.id.rb_tab_warn);
        rb_monitor = (RadioButton) findViewById(R.id.rb_tab_monitor);
        rb_interact = (RadioButton) findViewById(R.id.rb_tab_interact);
        rb_mine = (RadioButton) findViewById(R.id.rb_tab_mine);

        manager = getSupportFragmentManager();
        fragments.add(new ForecastFragment());// 预报
        fragments.add(new EarlyWarnFragment());// 预警
        fragments.add(new MonitorFragment());// 监测
        fragments.add(new InteractFragment());// 互动
        fragments.add(new MineFragment());// 我的
        loading(true);
    }


    public void goSecondPage() {
        if (mForecastFragment != null)
            mForecastFragment.setSecondPage();
    }

    public void getForecastData(CityBean cityBean, SiteBean.DataEntity siteBean) {
        if (mForecastFragment != null)
            mForecastFragment.getForecastNetData(cityBean, siteBean, currentCityId);
    }

    public void getForecastData(CityBean cityBean, SiteBean.DataEntity siteBean, String cityId) {
        if (mForecastFragment != null)
            mForecastFragment.getForecastNetData(cityBean, siteBean, cityId);
    }

    public void getSiteMonitorData(List<SiteBean.DataEntity> dataEntities) {
        mForecastFragment.getSiteMonitorData(dataEntities);
    }

    public void getDangerAndShelterData(String cityId, LatLng lng1, LatLng lng2, int page, int pageSie) {

    }

    public void getDangerAndShelterData(String cityId, LatLng lng1, int page, int pageSie) {
        mForecastFragment.getDanAndSheData(cityId, lng1, page, pageSie);
    }

    /**
     * 城市的点向左移动的
     */
    public void leftMove() {
        if (nowPager == 1) {
            nowPager--;
            LatLng lng = new LatLng(Double.parseDouble(homeCity.getLat()), Double.parseDouble(homeCity.getLng()));
            nowSite = SiteUtil.getCloseSite(this, lng);
            getForecastData(homeCity, nowSite, "0");
        } else {
            nowPager--;
            move();
        }
    }

    /**
     * 城市的点向右移动的
     */
    public void rightMove() {
        nowPager++;
        move();
    }

    private void move() {
        try {
            LogUtil.e("nowPager:" + nowPager);
            List<CityBean> citys = AddedCityUtil.getAllCity(this);
            nowCity = citys.get(nowPager - 1);
            LatLng lng = new LatLng(Double.parseDouble(nowCity.getLat()), Double.parseDouble(nowCity.getLng()));
            nowSite = SiteUtil.getCloseSite(this, lng);
            getForecastData(nowCity, nowSite, nowCity.getId());

            mForecastFragment.changeSecondPoint(citys.size() + 1, nowPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (!WeatherAppContext.isWeather) {
            mForecastFragment.changeView();
            return;
        }
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ExitAppUtils.getInstance().exit();

//            for (Activity activity : activityList) {
//                activity.finish();
//            }
            finish();
            mForecastFragment.recycle();
            ScreenShootUtil.deleteScreenShootImage();
            System.exit(0);
        }
    }


}
