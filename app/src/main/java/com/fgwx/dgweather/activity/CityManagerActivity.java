package com.fgwx.dgweather.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.utils.AddedCityUtil;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.view.DeleteListView;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends BaseActivity {
    private DeleteListView dlv_cityManager;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;
    List<CityBean> list;
    private ImageButton ib_back, ib_add;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        initView();
        setData();
        dlv_cityManager.setDelButtonClickListener(new DeleteListView.DelButtonClickListener() {
            @Override
            public void clickHappend(final int position) {
                mAdapter.remove(mAdapter.getItem(position));
                AddedCityUtil.deleteCity(CityManagerActivity.this, list.get(position));//删除数据
            }
        });

        dlv_cityManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(CityManagerActivity.this, MainActivity.class);
                Intent data = new Intent();
                data.putExtra("position", position);
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }

    private void initView() {
        dlv_cityManager = (DeleteListView) findViewById(R.id.dlv_cityManager);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_add = (ImageButton) findViewById(R.id.ib_right);

        ib_back.setOnClickListener(new MyClickListenr());
        ib_add.setOnClickListener(new MyClickListenr());
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("城市管理");
    }

    private void setData() {
        mDatas = new ArrayList<>();
        list = AddedCityUtil.getAllCity(CityManagerActivity.this);
        for (CityBean bean : list) {
            mDatas.add(bean.getName());
        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        dlv_cityManager.setAdapter(mAdapter);
    }

    public static void starCityManagerActivity(Context context) {
        Intent intent = new Intent(context, CityManagerActivity.class);
        context.startActivity(intent);
    }

    private class MyClickListenr implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ib_back:
                    finish();
                    break;
                case R.id.ib_right:
                    Intent intent = new Intent(CityManagerActivity.this, AddCityActivity.class);
                    String local = getIntent().getStringExtra(Constant.LOCAL);
                    if (!TextUtils.isEmpty(local))
                        intent.putExtra(Constant.LOCAL, local);
                    activityList.add(CityManagerActivity.this);
                    startActivity(intent);
                    break;
            }
        }
    }
}
