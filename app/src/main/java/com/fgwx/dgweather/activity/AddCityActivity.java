package com.fgwx.dgweather.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.CityAddAdapter;
import com.fgwx.dgweather.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCityActivity extends BaseActivity {

    private LinearLayout lyAddDg;
    private List list;
    private String[] dgName = {"莞城", "石龙", "虎门", "东城", "万江", "南城", "中堂", "望牛墩", "麻涌", "石碣", "高埗", "洪梅" +
            "道滘", "厚街", "沙田", "长安", "寮步", "大岭山", "大朗", "黄江", "樟木头", "凤岗", "塘厦", "谢岗", "清溪", "常平", "桥头", "横沥" +
            "东坑", "企石", "石排", "茶山", "松山湖", "虎门港", "生态园"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        addData();
        initView();
    }

    private void addData() {
        list = new ArrayList();
        for (int i = 0; i < dgName.length; i++) {
           list.add(dgName[i]);
        }
    }

    private void initView() {
        lyAddDg = (LinearLayout) findViewById(R.id.ly_add_dg);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        GridView dgGrid = (GridView) findViewById(R.id.gridView);
        CityAddAdapter addAdapter = new CityAddAdapter(list, this);
//        dgGrid.setLayoutParams(param1);
        dgGrid.setAdapter(addAdapter);  //配置适配器
//        lyAddDg.addView(dgGrid);
    }
}
