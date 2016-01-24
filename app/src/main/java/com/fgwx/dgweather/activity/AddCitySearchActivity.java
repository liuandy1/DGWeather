package com.fgwx.dgweather.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.CityAddAdapter;
import com.fgwx.dgweather.adapter.CitySearchAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.bean.AddedCityBean;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.utils.AddedCityUtil;
import com.fgwx.dgweather.utils.CityUtil;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.fgwx.dgweather.utils.StringUtil;
import com.fgwx.dgweather.view.AdapterScroGridView;

import java.util.ArrayList;
import java.util.List;

public class AddCitySearchActivity extends BaseActivity {
    private EditText et_search;
    private ListView lv_searchResult;

    List<CityBean> searchResults = new ArrayList<CityBean>();
    private CitySearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city_search);
        initView();
    }

    private void initView() {
        et_search = (EditText) findViewById(R.id.et_search);
        lv_searchResult = (ListView) findViewById(R.id.lv_searchResult);
        adapter = new CitySearchAdapter(this, searchResults);
        lv_searchResult.setAdapter(adapter);
        et_search.addTextChangedListener(new EditChangedListener());

        lv_searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //添加城市
                searchResults.get(position).setName(StringUtil.split(searchResults.get(position).getName(), "，"));
                AddedCityUtil.addCity(AddCitySearchActivity.this, searchResults.get(position));
                MainActivity.starMainActivity(AddCitySearchActivity.this,searchResults.get(position).getId());
//                Intent intent = new Intent(AddCitySearchActivity.this,MainActivity.class);
              //  activityList.add(AddCitySearchActivity.this);
//                startActivity(intent);
//                finish();
            }
        });
    }

    class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchResults.clear();
            if (!s.toString().equals("")) {
                searchResults.addAll(CityUtil.getCityByKeyWords(AddCitySearchActivity.this, s.toString()));
            }
            adapter.notifyDataSetChanged();
        }
    }

}
