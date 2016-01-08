package com.fgwx.dgweather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.utils.DipPixUtil;
import com.fgwx.dgweather.utils.LogUtil;

public class TestActivity extends AppCompatActivity {

    private RelativeLayout rvRed;
    private RelativeLayout rvGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        rvRed = (RelativeLayout) findViewById(R.id.rv_red);
        rvGreen = (RelativeLayout) findViewById(R.id.rv_green);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        int mScreenHeight = dm.heightPixels;
        LogUtil.e("屏幕高:" + mScreenHeight);
        int redHeight = rvRed.getHeight();


        LogUtil.e("红色部分高:" + redHeight);
        TextView textView = new TextView(this);

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        rvRed.measure(w, h);

        int height = rvRed.getMeasuredHeight();
        LogUtil.e("红色部分高111:" + redHeight);

        int width = rvRed.getMeasuredWidth();
        LogUtil.e("红色部分宽111:" + width);

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mScreenHeight - height- DipPixUtil.getStatusBarHeight(this));
        textView.setText("我想要的效果实现了吗、");
        textView.setLayoutParams(params1);
        rvGreen.addView(textView);

    }
}
