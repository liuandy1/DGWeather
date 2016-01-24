package com.fgwx.dgweather.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.CityAddAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.utils.AddedCityUtil;
import com.fgwx.dgweather.utils.CityUtil;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.view.AdapterScroGridView;

import java.util.ArrayList;
import java.util.List;

public class AddCityActivity extends BaseActivity implements View.OnClickListener {
    private AdapterScroGridView asgv_dongguanCity, asgv_hotCity;
    private TextView tv_openOrClose;
    private CityAddAdapter dgAdapter, hotAdapter;
    private Button bt_openAll;
    private boolean isOpen = false, isOpenAll = false;
    private List<CityBean> dgListData, dgList, hotListData, hotList, dgListAll, hotListAll;
    private ImageView ib_back;
    private EditText et_search;
    private TextView tv_search;
    private TextView tv_local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        initView();
        setCityData();
    }

    private void setCityData() {


        dgList = new ArrayList<CityBean>();
        dgListAll = CityUtil.getLocalCity(AddCityActivity.this);
        dgListData = new ArrayList<CityBean>();
        hotList = new ArrayList<CityBean>();
        hotListAll = CityUtil.getHotCity(AddCityActivity.this);
        hotListData = new ArrayList<CityBean>();
        for (int i = 0; i < 12; i++) {
            dgList.add(dgListAll.get(i));
        }

        for (int j = 0; j < 18; j++) {
            hotList.add(hotListAll.get(j));
        }

        dgListData.addAll(dgList);
        hotListData.addAll(hotList);

        dgAdapter = new CityAddAdapter(this, dgListData);
        hotAdapter = new CityAddAdapter(this, hotListData);

        asgv_dongguanCity.setAdapter(dgAdapter);
        asgv_hotCity.setAdapter(hotAdapter);
    }

    private void initView() {
        asgv_dongguanCity = (AdapterScroGridView) findViewById(R.id.asgv_dongguanCity);
        asgv_hotCity = (AdapterScroGridView) findViewById(R.id.asgv_hotCity);
        tv_openOrClose = (TextView) findViewById(R.id.tv_openOrClose);
        bt_openAll = (Button) findViewById(R.id.bt_openAll);
        ib_back = (ImageView) findViewById(R.id.ib_add_city_back);
        et_search = (EditText) findViewById(R.id.et_addCity_search);
        tv_search = (TextView) findViewById(R.id.tv_search);

        bt_openAll.setOnClickListener(this);
        tv_openOrClose.setOnClickListener(this);
        ib_back.setOnClickListener(this);
        et_search.setOnClickListener(this);
        tv_search.setOnClickListener(this);

        asgv_dongguanCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddedCityUtil.addCity(AddCityActivity.this, dgListData.get(position));
                //activityList.add(AddCityActivity.this);
                //startActivity(new Intent(AddCityActivity.this, MainActivity.class));
                MainActivity.starMainActivity(AddCityActivity.this, dgListData.get(position).getId());
            }
        });
        asgv_hotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddedCityUtil.addCity(AddCityActivity.this, hotListData.get(position));
                MainActivity.starMainActivity(AddCityActivity.this, hotListData.get(position).getId());
                // activityList.add(AddCityActivity.this);
              /*  startActivity(new Intent(AddCityActivity.this, MainActivity.class));*/
                // MainActivity.starMainActivity(AddCityActivity.this,AddedCityUtil.getCityByName(AddCityActivity.this,hotListData.get(position).getName()).getId());
            }
        });

        String local = (String) getIntent().getExtras().get(Constant.LOCAL);
        tv_local = (TextView) findViewById(R.id.tv_local);
        tv_local.setText(local);
    }

    @Override
    public void onClick(View v) {
        Drawable img_open, img_oclose;
        img_open = getResources().getDrawable(R.drawable.icon_open);
        img_oclose = getResources().getDrawable(R.drawable.icon_close);
        img_open.setBounds(0, 0, img_open.getMinimumWidth(), img_open.getMinimumHeight());
        img_oclose.setBounds(0, 0, img_oclose.getMinimumWidth(), img_oclose.getMinimumHeight());

        switch (v.getId()) {
            case R.id.tv_openOrClose:
                if (isOpen) {
                    isOpen = false;
                    dgListData.clear();
                    dgListData.addAll(dgList);
                    tv_openOrClose.setCompoundDrawables(img_open, null, null, null); //设置左图标
                    tv_openOrClose.setText(getResources().getString(R.string.open));
                } else {
                    isOpen = true;
                    dgListData.clear();
                    dgListData.addAll(dgListAll);
                    tv_openOrClose.setCompoundDrawables(img_oclose, null, null, null); //设置左图标
                    tv_openOrClose.setText(getResources().getString(R.string.close));
                }
                dgAdapter.notifyDataSetChanged();
                LogUtil.e("AAA", dgListData.size() + "");
                break;
            case R.id.bt_openAll:
                if (isOpenAll) {
                    isOpenAll = false;
                    hotListData.clear();
                    hotListData.addAll(hotList);
                    bt_openAll.setText(getResources().getString(R.string.open_all));
                } else {
                    isOpenAll = true;
                    hotListData.clear();
                    hotListData.addAll(hotListAll);
                    bt_openAll.setText(getResources().getString(R.string.take_back));
                }
                hotAdapter.notifyDataSetChanged();
                break;
            case R.id.ib_add_city_back:
                finish();
                break;
            case R.id.et_addCity_search:
                //activityList.add(AddCityActivity.this);
                startActivity(new Intent(AddCityActivity.this, AddCitySearchActivity.class));
                break;
            case R.id.tv_search:
                //activityList.add(AddCityActivity.this);
                startActivity(new Intent(AddCityActivity.this, AddCitySearchActivity.class));
                break;
        }
    }

    public static void starAddCityActivity(Context context, String cityName) {

        Intent intent = new Intent(context, AddCityActivity.class);
        intent.putExtra(Constant.LOCAL, cityName);
        context.startActivity(intent);
    }

}
