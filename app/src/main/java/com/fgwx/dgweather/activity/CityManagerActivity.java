package com.fgwx.dgweather.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.view.DeleteListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityManagerActivity extends BaseActivity {
    private DeleteListView dlv_cityManager;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        dlv_cityManager = (DeleteListView) findViewById(R.id.dlv_cityManager);

        // 不要直接Arrays.asList
        mDatas = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        dlv_cityManager.setAdapter(mAdapter);

        dlv_cityManager.setDelButtonClickListener(new DeleteListView.DelButtonClickListener() {
            @Override
            public void clickHappend(final int position) {
                mAdapter.remove(mAdapter.getItem(position));
            }
        });

        dlv_cityManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    public static void starCityManagerActivity(Context context) {
        Intent intent = new Intent(context, CityManagerActivity.class);
        context.startActivity(intent);
    }
}
