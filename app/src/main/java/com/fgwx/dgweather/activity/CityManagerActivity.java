package com.fgwx.dgweather.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseActivity;

public class CityManagerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        //findViewById(R.id.btn_add_city).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
           /* case R.id.btn_add_city:
//                startActivity(new Intent(this, AddCityActivity.class));
                break;*/
        }
    }
    public static void starCityManagerActivity(Context context){
        Intent intent=new Intent(context,CityManagerActivity.class);
        context.startActivity(intent);
    }
}
