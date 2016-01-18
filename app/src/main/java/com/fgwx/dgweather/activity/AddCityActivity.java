package com.fgwx.dgweather.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.CityAddAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.utils.CityUtil;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.view.AdapterScroGridView;
import com.fgwx.dgweather.view.AdapterScroListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCityActivity extends BaseActivity implements View.OnClickListener {
    private AdapterScroGridView asgv_dongguanCity, asgv_hotCity;
    private TextView tv_openOrClose;
    private CityAddAdapter dgAdapter, hotAdapter;
    private Button bt_openAll;
    private boolean isOpen = false, isOpenAll = false;
    private List<CityBean> dgListData, dgList,  hotListData, hotList, dgListAll, hotListAll;

    private LinearLayout lyAddDg;
//    private String[] dgName = {"莞城", "石龙", "虎门", "东城", "万江", "南城", "中堂", "望牛墩", "麻涌", "石碣", "高埗", "洪梅" +
//            "道滘", "厚街", "沙田", "长安", "寮步", "大岭山", "大朗", "黄江", "樟木头", "凤岗", "塘厦", "谢岗", "清溪", "常平", "桥头", "横沥" +
//            "东坑", "企石", "石排", "茶山", "松山湖", "虎门港", "生态园"};
//    private String[] hotName = {"大朗", "黄江", "樟木头", "凤岗", "塘厦", "谢岗", "清溪", "常平", "桥头", "横沥" +
//            "东坑", "企石", "石排", "茶山", "松山湖", "虎门港", "生态园", "莞城", "石龙", "虎门", "东城", "万江", "南城", "中堂", "望牛墩", "麻涌", "石碣", "高埗", "洪梅" +
//            "道滘", "厚街", "沙田", "长安", "寮步", "大岭山", "大朗", "黄江", "樟木头", "凤岗", "塘厦", "谢岗", "清溪", "常平", "桥头", "横沥" +
//            "东坑", "企石", "石排", "茶山", "松山湖", "虎门港", "生态园"};

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

        dgListData.addAll(dgListAll);
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

        bt_openAll.setOnClickListener(this);
        tv_openOrClose.setOnClickListener(this);
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
                LogUtil.e("AAA",dgListData.size()+"");
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
        }
    }

    public static void starAddCityActivity(Context context){
        Intent intent=new Intent(context,AddCityActivity.class);
        context.startActivity(intent);
    }
}
