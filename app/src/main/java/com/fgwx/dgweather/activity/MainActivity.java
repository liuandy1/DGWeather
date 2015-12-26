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
    private RadioButton rb_forecast, rb_warn, rb_monitor, rb_interact,rb_mine;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager manager;
    private ForecastFragment mForecastFragment;
    private EarlyWarnFragment mEarlyWarnFragment;
    private MonitorFragment mMonitorFragment;
    private InteractFragment mInteractFragment;
    private MineFragment mMineFragment;

    /**
     * 用于对Fragment进行管理
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewById();
        manager = getSupportFragmentManager();
        fragments.add(new ForecastFragment());// 预报
        fragments.add(new EarlyWarnFragment());// 预警
        fragments.add(new MonitorFragment());// 监测
        fragments.add(new InteractFragment());// 互动
        fragments.add(new MineFragment());// 我的

        setTabSelection(0);

        rg_tabs.setOnCheckedChangeListener(new onRadioGroupListener());

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
                    // 当点击了互动tab时，选中第4个tab
                    setTabSelection(4);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
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
     * @param index
     *            每个tab页对应的下标。0表示首页，1表示美丽志，2表示美丽惠，3表示美圈，4表示我。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        rg_tabs.clearFocus();
        // 开启一个Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {

            case 0:
                // 当点击了找教练tab时，改变控件的图片和文字颜色
                rb_forecast.setChecked(true);
                if (mForecastFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mForecastFragment = new ForecastFragment();
                    transaction.add(R.id.fl_tab_content, mForecastFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mForecastFragment);
                }
                break;
            case 1:
                // 当点击了找场馆tab时，改变控件的图片和文字颜色
                rb_warn.setChecked(true);
                if (mEarlyWarnFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    mEarlyWarnFragment = new EarlyWarnFragment();
                    transaction.add(R.id.fl_tab_content, mEarlyWarnFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(mEarlyWarnFragment);
                }
                break;

            case 2:
                // 当点击了发现tab时，改变控件的图片和文字颜色
                rb_monitor.setChecked(true);
                if (mMonitorFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mMonitorFragment = new MonitorFragment();
                    transaction.add(R.id.fl_tab_content, mMonitorFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mMonitorFragment);
                }
                break;

            case 3:
                // 当点击了个人中心tab时，改变控件的图片和文字颜色
                    rb_interact.setChecked(true);
                    if (mInteractFragment == null) {
                        // 如果NewsFragment为空，则创建一个并添加到界面上
                        mInteractFragment = new InteractFragment();
                        transaction.add(R.id.fl_tab_content, mInteractFragment);
                    } else {
                        // 如果NewsFragment不为空，则直接将它显示出来
                        transaction.show(mInteractFragment);
                    }
                break;
            case 4:
                // 当点击了个人中心tab时，改变控件的图片和文字颜色
                rb_mine.setChecked(true);
                if (mMineFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.fl_tab_content, mMineFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    public void initViewById() {
        ActivityManager.getInstance().addActivity(this);
        rg_tabs = (RadioGroup) findViewById(R.id.rg_tab);
        rb_forecast = (RadioButton) findViewById(R.id.rb_tab_forecast);
        rb_warn = (RadioButton) findViewById(R.id.rb_tab_warn);
        rb_monitor = (RadioButton) findViewById(R.id.rb_tab_monitor);
        rb_interact = (RadioButton) findViewById(R.id.rb_tab_interact);
        rb_mine = (RadioButton) findViewById(R.id.rb_tab_mine);
    }

}
