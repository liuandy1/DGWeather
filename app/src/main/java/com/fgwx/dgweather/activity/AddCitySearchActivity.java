package com.fgwx.dgweather.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.adapter.CityAddAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.view.AdapterScroGridView;

import java.util.ArrayList;
import java.util.List;

public class AddCitySearchActivity extends BaseActivity {
    private EditText et_search;
    private ListView lv_searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city_search);
        initView();
    }

    private void initView() {
        et_search = (EditText) findViewById(R.id.et_search);
        lv_searchResult = (ListView) findViewById(R.id.lv_searchResult);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.activity_add_city_search_item, new String[]{"name", "head", "desc"});
        lv_searchResult.setAdapter(adapter);

        et_search.addTextChangedListener(new EditChangedListener());
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

        }
    }
}
