package com.fgwx.dgweather.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.baidu.mapapi.model.LatLng;
import com.fgwx.dgweather.R;
import com.fgwx.dgweather.activity.MainActivity;
import com.fgwx.dgweather.adapter.ForecastSortAdapter;
import com.fgwx.dgweather.base.BaseActivity;
import com.fgwx.dgweather.base.BaseFragment;
import com.fgwx.dgweather.bean.CityBean;
import com.fgwx.dgweather.bean.DangerAndShelterBean;
import com.fgwx.dgweather.bean.HomeForecastBaseBean;
import com.fgwx.dgweather.bean.SiteBean;
import com.fgwx.dgweather.bean.SiteMonitorBaseBean;
import com.fgwx.dgweather.utils.LogUtil;
import com.fgwx.dgweather.utils.MPreferencesUtil;
import com.fgwx.dgweather.utils.NetWorkUtil;
import com.fgwx.dgweather.utils.SiteUtil;
import com.fgwx.dgweather.utils.ToastUtil;
import com.fgwx.dgweather.utils.WeatherNetUtils;
import com.fgwx.dgweather.view.ForecastFirstView;
import com.fgwx.dgweather.view.ForecastSecondView;
import com.fgwx.dgweather.view.WeatherVerticalViewPager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.logging.LogRecord;

/**
 * Class description goes here.
 *
 * @author liu shanguang (account@sohu-inc.com)
 */
public class ForecastFragment extends BaseFragment {

    private List<View> mViews;
    private MainActivity mContext;
    private WeatherVerticalViewPager mViewPager;
    private ForecastSortAdapter adapter;
    private ForecastFirstView mForecastFirstView;
    private ForecastSecondView mForecastSecondView;
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragnent_forecast, null, false);
        initUI(view);
        // getAreaData();
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (MainActivity) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //先访问定位的缓存数据
    }

    private void initUI(View view) {
        gson = new Gson();
        mViews = new ArrayList<>();
        mViewPager = (WeatherVerticalViewPager) view.findViewById(R.id.vp_home_page_fragment);
        mForecastFirstView = new ForecastFirstView(mContext);
        mForecastSecondView = new ForecastSecondView(mContext);
        mViews.add(mForecastFirstView);
        mViews.add(mForecastSecondView);
        adapter = new ForecastSortAdapter(mViews);
        mViewPager.setAdapter(adapter);
    }

    public void changePager(int i) {
        mForecastFirstView.selectPosition(i);
    }

    public void setSecondPage() {
        mViewPager.setCurrentItem(1);
    }

    private void setCacheData(HomeForecastBaseBean data, String foucsCityId) {
        MPreferencesUtil.getInstance().setValue(MPreferencesUtil.FORECASTDATA + foucsCityId, gson.toJson(data));
    }

    private HomeForecastBaseBean getCacheData(String foucsCityId) {
        String string = MPreferencesUtil.getInstance().getValue(MPreferencesUtil.FORECASTDATA + foucsCityId, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return gson.fromJson(string, HomeForecastBaseBean.class);
    }

    public void getSiteMonitorData(List<SiteBean.DataEntity> dataEntities) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("siteId", SiteUtil.setSiteBeanIdToStringList(dataEntities));
        WeatherNetUtils.getSiteMonitorData(new Response.Listener<SiteMonitorBaseBean>() {
            @Override
            public void onResponse(SiteMonitorBaseBean response) {
                setSiteMonitorData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, map);
    }

    /**
     * 获取易灾点和避难所的信息
     *
     * @param cityId
     * @param lng1    左上角的点
     * @param lng2    右下角的点
     * @param page    可以不传的话，就传0
     * @param pageSie 可以不传的话，就传0
     */
    public void getDanAndSheData(String cityId, LatLng lng1, LatLng lng2, int page, int pageSie) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("queryDanger", "1");
        map.put("queryShelter", "1");
        map.put("cityId", cityId);
        if (lng1 != null) {
            map.put("ltLng", lng1.longitude + "");
            map.put("ltLat", lng1.latitude + "");
        }
        if (lng2 != null) {
            map.put("rbLng", lng2.longitude + "");
            map.put("rbLat", lng2.latitude + "");
        }
        if (page > 0) {
            map.put("page", page + "");
        }
        if (pageSie > 0) {
            map.put("pageSize", pageSie + "");
        }
        WeatherNetUtils.getDangerAndShelter(new Response.Listener<DangerAndShelterBean>() {
            @Override
            public void onResponse(DangerAndShelterBean response) {
                if (null == response) {
                    ToastUtil.show(getActivity(), "服务器异常,请稍后重试");
                    return;
                }
                if (response.getCode() != 200) {
                    ToastUtil.show(getActivity(), "服务器异常,请稍后重试");
                    return;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, map);
    }

    /**
     * 获取易灾点和避难所的信息
     *
     * @param cityId
     * @param lng1    左上角的点
     * @param page    可以不传的话，就传0
     * @param pageSie 可以不传的话，就传0
     */
    public void getDanAndSheData(String cityId, LatLng lng1, int page, int pageSie) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("queryDanger", "1");
        map.put("queryShelter", "1");
//        map.put("cityId", cityId);
        if (lng1 != null) {
//            map.put("ltLng", lng1.longitude - 0.3 + "");
//            map.put("ltLat", lng1.latitude + 0.5 + "");
//
//            map.put("rbLng", lng1.longitude + 0.2 + "");
//            map.put("rbLat", lng1.latitude - 0.2 + "");
        }
        if (page > 0) {
            map.put("page", page + "");
        }
        if (pageSie > 0) {
            map.put("pageSize", pageSie + "");
        }
        WeatherNetUtils.getDangerAndShelter(new Response.Listener<DangerAndShelterBean>() {
            @Override
            public void onResponse(DangerAndShelterBean response) {
                if (null == response) {
                    ToastUtil.show(getActivity(), "服务器异常,请稍后重试");
                    return;
                }
                if (response.getCode() != 200) {
                    ToastUtil.show(getActivity(), "服务器异常,请稍后重试");
                    return;
                }
                if (null != response.getDangers() && response.getDangers().size() > 0) {
                    LogUtil.e("易灾点:" + response.getDangers().size());
                    mForecastFirstView.setDangerData(response.getDangers());
                }
                if (null != response.getShelters() && response.getShelters().size() > 0) {
                    LogUtil.e("避难所:" + response.getShelters().size());
                    mForecastFirstView.setShelterData(response.getShelters());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, map);
    }

    public void getForecastNetData(final CityBean cityBean, final SiteBean.DataEntity siteBean, final String foucsCityId) {
        if(getCacheData(foucsCityId)==null){
            loading(true);
        }
        setForecastData(getCacheData(foucsCityId));
        TreeMap<String, String> map = new TreeMap<>();
        if (!TextUtils.isEmpty(cityBean.getId()))
            map.put("cityId", cityBean.getId());//城市Id，必须
//            map.put("cityId", "441900");//城市Id，必须
        LogUtil.e("请求首页数据,城市id:" + cityBean.getId() + "     城市名字:" + cityBean.getName());
        //map.put("streetId", null);//街道Id
        if (!TextUtils.isEmpty(siteBean.getId()))
            map.put("siteId", siteBean.getId());//站点Id
        LogUtil.e("请求首页数据,站点id:" + siteBean.getId() + "     站点名字:" + siteBean.getName());
        //map.put("last10DayTime", null);
        map.put("query10Day", "1");//是否查询10天天气预报（不可空，0否1是）
        map.put("queryExact", "1");//是否查询精确预报 （不可空，0否1是）
        //map.put("lastExactTime", null);//精确预报最后更新时间
        //map.put("lastSiteTime", null);
        map.put("queryLife", "1");
        map.put("lastLifeTime", "1");
        map.put("querySun", "1");
        //map.put("lastSunTime",null);
        WeatherNetUtils.getHomeForecastData(new Response.Listener<HomeForecastBaseBean>() {
            @Override
            public void onResponse(HomeForecastBaseBean response) {
                LogUtil.e("访问成功了");
                loading(false);
                if (response == null) {
                    return;
                }
                int code = response.getCode();
                switch (code) {
                    case 200:
                        //时间
                        if ("东莞市".equals(cityBean.getName())) {
                            response.getData().setCityName(siteBean.getAreaName());
                        } else {
                            response.getData().setCityName(cityBean.getName());
                        }
                        setCacheData(response, foucsCityId);
                        setForecastData(response);
                        break;
                    default:
                        break;
                }
                //设置数据
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("访问失败了");
                loading(false);
                Log.v("XXX",error.toString());
                LogUtil.e(error.toString());
                Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show();
                loading(false);
            }
        }, map);
    }


    private void setForecastData(HomeForecastBaseBean homeForecastBaseBean) {
        if (homeForecastBaseBean == null)
            return;
        setFirstPageData(homeForecastBaseBean);
        setSecondePageData(homeForecastBaseBean);
    }

    private void setSiteMonitorData(SiteMonitorBaseBean siteMonitorBaseBean) {
        if (siteMonitorBaseBean == null)
            return;
        mForecastFirstView.setSiteMonitorData(siteMonitorBaseBean);
    }

    private void setFirstPageData(HomeForecastBaseBean homeForecastBaseBean) {
        mForecastFirstView.setFirstForecastData(homeForecastBaseBean);
    }

    private void setSecondePageData(HomeForecastBaseBean homeForecastBaseBean) {
        mForecastSecondView.setSecondForecastData(homeForecastBaseBean);
    }

    public void recycle() {
        mForecastFirstView.recycle();
    }

    public void changeView() {
        mForecastFirstView.toHome();
    }

    public void initViewPager(int i) {
        mForecastFirstView.initViewPager(0, true);
    }
   /* private void getAreaData(){
        WeatherNetUtils.getAreaData(new Response.Listener<AreaBaseBean>() {
            @Override
            public void onResponse(AreaBaseBean response) {
                LogUtil.e("访问成功了");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("访问失败了");
                LogUtil.e(error.toString());
            }
        });
    }*/

}
