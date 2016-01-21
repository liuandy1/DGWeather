package com.fgwx.dgweather.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.fragment.EarlyWarnFragment;
import com.fgwx.dgweather.fragment.ForecastFragment;
import com.fgwx.dgweather.fragment.InteractFragment;
import com.fgwx.dgweather.fragment.MineFragment;
import com.fgwx.dgweather.fragment.MonitorFragment;


/**
 * Created by senghor on 2015/12/23.
 */
public class HomePageActivity extends BaseActivity implements View.OnClickListener{

    public static final int FORECAST_TAB = 0;
    public static final int EARLYWARN_TAB = 1;
    public static final int MONITOR_TAB = 2;



    public static final int INTERACT_TAB = 3;
    public static final int MINE_TAB = 4;

    public static final String FORECAST_TAG = "Forecast";
    public static final String EARLYWARN_TAG ="EarlyWarn";
    public static final String MONITOR_TAG = "Monitor";
    public static final String INTERACT_TAG = "Interact";
    public static final String MINE_TAG = "Mine";
    private static final String FLAG = "flag";

    private ForecastFragment mForcastFragment;
    private EarlyWarnFragment mEarlyWarnFragment;
    private MonitorFragment mMonitorFragment;
    private InteractFragment mInteractFragment;
    private MineFragment mMineFragment;
    private int flag = 0;
    /**
     * 底部四个按钮
     */
    private ImageButton mIbForecast;
    private ImageButton mIbEarlyWarn;
    private ImageButton mIbMonitor;
    private ImageButton mIbInteract;
    private ImageButton mIbMine;
    private TextView mTvForecast;
    private TextView mTvEarlyWarn;
    private TextView mTvMonitor;
    private TextView mTvInteract;
    private TextView mTvMine;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        isFragmentSave(savedInstanceState);
        initUI(flag);
    }
    public void isFragmentSave(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mForcastFragment = (ForecastFragment) fragmentManager.findFragmentByTag(FORECAST_TAG);
            mEarlyWarnFragment = (EarlyWarnFragment) fragmentManager.findFragmentByTag(EARLYWARN_TAG);
            mMineFragment = (MineFragment) fragmentManager.findFragmentByTag(MINE_TAG);
            mMonitorFragment = (MonitorFragment) fragmentManager.findFragmentByTag(MONITOR_TAG);
            mInteractFragment = (InteractFragment) fragmentManager.findFragmentByTag(INTERACT_TAG);
        }

    }








    public void initUI(int i) {
        LinearLayout mLlForecastTab;//预报
        LinearLayout mLlEarlyWarnTab;//预警
        LinearLayout mLlMonitorTab;//监测
        LinearLayout mLlMineTab;//我的
        LinearLayout mLlInteractTab;

        mLlForecastTab = (LinearLayout) findViewById(R.id.ll_tab_forecast);
        mLlEarlyWarnTab = (LinearLayout) findViewById(R.id.ll_tab_early_warn);
        mLlMonitorTab = (LinearLayout) findViewById(R.id.ll_tab_monitor);
        mLlMineTab = (LinearLayout) findViewById(R.id.ll_tab_mine);
        mLlInteractTab = (LinearLayout) findViewById(R.id.ll_tab_interact);

        mLlForecastTab.setOnClickListener(this);
        mLlEarlyWarnTab.setOnClickListener(this);
        mLlMonitorTab.setOnClickListener(this);
        mLlMineTab.setOnClickListener(this);
        mLlInteractTab.setOnClickListener(this);

        mIbForecast = (ImageButton) mLlForecastTab.findViewById(R.id.ib_tab_forecast);
        mIbEarlyWarn = (ImageButton) mLlEarlyWarnTab.findViewById(R.id.ib_tab_early_warn);
        mIbInteract = (ImageButton) mLlInteractTab.findViewById(R.id.ib_tab_interact);
        mIbMine = (ImageButton) mLlMineTab.findViewById(R.id.ib_tab_mine);
        mIbMonitor = (ImageButton) mLlMonitorTab.findViewById(R.id.ib_tab_monitor);

        mTvForecast = (TextView) mLlForecastTab.findViewById(R.id.tv_tab_forecast);
        mTvEarlyWarn = (TextView) mLlEarlyWarnTab.findViewById(R.id.tv_tab_early_warn);
        mTvMonitor = (TextView) mLlMonitorTab.findViewById(R.id.tv_tab_monitor);
        mTvMine = (TextView) mLlMineTab.findViewById(R.id.tv_tab_mine);
        mTvInteract = (TextView) mLlInteractTab.findViewById(R.id.tv_tab_interact);
        setTabSelection(i);
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {

        mTvForecast.setTextColor(this.getResources().getColor(R.color.home_tab_text_color));
        mTvEarlyWarn.setTextColor(this.getResources().getColor(R.color.home_tab_text_color));
        mTvInteract.setTextColor(this.getResources().getColor(R.color.home_tab_text_color));
        mTvMine.setTextColor(this.getResources().getColor(R.color.home_tab_text_color));
        mTvMonitor.setTextColor(this.getResources().getColor(R.color.home_tab_text_color));

    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (mForcastFragment != null) {
            transaction.hide(mForcastFragment);
        }
        if (mEarlyWarnFragment != null) {
            transaction.hide(mEarlyWarnFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
        if (mMonitorFragment != null) {
            transaction.hide(mMonitorFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
        if (mInteractFragment != null) {
            transaction.hide(mInteractFragment);
        }

    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    @SuppressLint("NewApi")
    private void setTabSelection(int index) {
        flag = index;
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case FORECAST_TAB:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                mTvForecast.setTextColor(this.getResources().getColor(R.color.home_tab_text_color_checked));
                if (mForcastFragment == null) {
                    // 如果LiveFragment为空，则创建一个并添加到界面上
                    mForcastFragment = new ForecastFragment();
                    transaction.add(R.id.id_content, mForcastFragment, FORECAST_TAG);
                } else {
                    // 如果LiveFragment不为空，则直接将它显示出来
                    transaction.show(mForcastFragment);
                }
                break;
            case EARLYWARN_TAB:
                mTvEarlyWarn.setTextColor(this.getResources().getColor(R.color.home_tab_text_color_checked));
                if (mEarlyWarnFragment == null) {
                    // 如果SquareFragment为空，则创建一个并添加到界面上
                    mEarlyWarnFragment = new EarlyWarnFragment();
                    transaction.add(R.id.id_content, mEarlyWarnFragment, EARLYWARN_TAG);
                } else {
                    // 如果SquareFragment不为空，则直接将它显示出来
                    transaction.show(mEarlyWarnFragment);
                }
                break;

            case MONITOR_TAB:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                mTvMonitor.setTextColor(this.getResources().getColor(R.color.home_tab_text_color_checked));
                if (mMonitorFragment == null) {
                    // 如果SquareFragment为空，则创建一个并添加到界面上
                    mMonitorFragment = new MonitorFragment();
                    transaction.add(R.id.id_content, mMonitorFragment, MONITOR_TAG);
                } else {
                    // 如果SquareFragment不为空，则直接将它显示出来
                    transaction.show(mMonitorFragment);
                }
                break;

            case INTERACT_TAB:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                mTvInteract.setTextColor(this.getResources().getColor(R.color.home_tab_text_color_checked));
                if (mInteractFragment == null) {
                    // 如果FindFragment为空，则创建一个并添加到界面上
                    mInteractFragment = new InteractFragment();
                    transaction.add(R.id.id_content, mInteractFragment, INTERACT_TAG);
                } else {
                    // 如果MallFragment不为空，则直接将它显示出来
                    transaction.show(mInteractFragment);
                }
                break;
            case MINE_TAB:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                mTvMine.setTextColor(this.getResources().getColor(R.color.home_tab_text_color_checked));
                if (mMineFragment == null) {
                    // 如果FindFragment为空，则创建一个并添加到界面上
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.id_content, mMineFragment, MINE_TAG);
                } else {
                    // 如果MallFragment不为空，则直接将它显示出来
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_tab_forecast:
                setTabSelection(FORECAST_TAB);
                break;
            case R.id.ll_tab_early_warn:
                setTabSelection(EARLYWARN_TAB);
                break;
            case R.id.ll_tab_monitor:
                setTabSelection(MONITOR_TAB);
                break;
            case R.id.ll_tab_interact:
                setTabSelection(INTERACT_TAB);
                break;
            case R.id.ll_tab_mine:
                setTabSelection(MINE_TAB);
                break;
        }
    }
}
