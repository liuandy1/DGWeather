package com.fgwx.dgweather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.fragment.EarlyWarnFragment;
import com.fgwx.dgweather.fragment.ForecastFragment;
import com.fgwx.dgweather.fragment.ForecastMoreFragment;
import com.fgwx.dgweather.fragment.InteractFragment;
import com.fgwx.dgweather.fragment.MineFragment;
import com.fgwx.dgweather.fragment.MonitorFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by senghor on 2015/12/23.
 */
public class MainActivity extends FragmentActivity {

    private RadioGroup rg_tabs;
    private RadioButton rb_forecast, rb_warn, rb_monitor, rb_interact, rb_mine;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager manager;
    private ForecastFragment mForecastFragment;
    private EarlyWarnFragment mEarlyWarnFragment;
    private MonitorFragment mMonitorFragment;
    private InteractFragment mInteractFragment;
    private MineFragment mMineFragment;
    private ForecastMoreFragment mForecastMoreFragment;

    private boolean isMore;
    public static final String FORECAST_TAG = "Forecast";
    public static final String EARLYWARN_TAG = "EarlyWarn";
    public static final String MONITOR_TAG = "Monitor";
    public static final String INTERACT_TAG = "Interact";
    public static final String MINE_TAG = "Mine";
    public static final String FORECASTMORE_TAG = "ForecastMore";

    /**
     * 用于对Fragment进行管理
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFragmentSave(savedInstanceState);
        init();
        setTabSelection(0);
        rg_tabs.setOnCheckedChangeListener(new onRadioGroupListener());

    }

    public void isFragmentSave(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mForecastFragment = (ForecastFragment) manager.findFragmentByTag(FORECAST_TAG);
            mEarlyWarnFragment = (EarlyWarnFragment) manager.findFragmentByTag(EARLYWARN_TAG);
            mMineFragment = (MineFragment) manager.findFragmentByTag(MINE_TAG);
            mMonitorFragment = (MonitorFragment) manager.findFragmentByTag(MONITOR_TAG);
            mInteractFragment = (InteractFragment) manager.findFragmentByTag(INTERACT_TAG);
            mForecastMoreFragment = (ForecastMoreFragment) manager.findFragmentByTag(FORECASTMORE_TAG);
        }
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

    ;

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
        if (mForecastMoreFragment != null) {
            transaction.hide(mForecastMoreFragment);
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示预报，1表示预警，2表示监测，3表示互动，4表示我的。
     */
    private void setTabSelection(int index) {
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
                if (!isMore) {
                    if (mForecastFragment == null) {
                        // 如果mForecastFragment为空，则创建一个并添加到界面上
                        mForecastFragment = new ForecastFragment();
                        transaction.add(R.id.fl_tab_content, mForecastFragment, FORECAST_TAG);
                    } else {
                        // 如果mForecastFragment不为空，则直接将它显示出来
                        transaction.show(mForecastFragment);
                    }
                } else {
                    if (mForecastMoreFragment == null) {
                        mForecastMoreFragment = new ForecastMoreFragment();
                        transaction.add(R.id.fl_tab_content, mForecastMoreFragment, FORECASTMORE_TAG);
                    } else {
                        transaction.show(mForecastMoreFragment);
                    }
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

    //    初始化控件
    public void init() {
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
    }

    public void toggleMoreFragment(boolean ismore) {
        isMore = ismore;
        setTabSelection(0);
    }
}
