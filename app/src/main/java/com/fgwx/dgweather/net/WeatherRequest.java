package com.fgwx.dgweather.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class WeatherRequest<T> extends Request<T> {
    private final Listener<T> mListener;

    private Gson mGson;

    private Class<T> mClass;

    private WeatherRequest(int method, String url, Class<T> clazz, Listener<T> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
    }


/*
    public WeatherRequest(String url, Class<T> clazz, Listener<T> listener,
                          Response.ErrorListener errorListener) {
        this(Method.GET, UrlUtil.buildUrl(url, new TreeMap<String, String>()), clazz, listener, errorListener);
    }
*/

    public WeatherRequest(String url, Class<T> clazz, Listener<T> listener,
                          Map<String, String> mParam,
                          Response.ErrorListener errorListener) {
        this(Method.POST, url, clazz, listener, errorListener);
        this.mParam=mParam;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //得到返回数据
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
          // Log.v("request", jsonString);
            // 转化成对象返回
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }catch (Exception e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        /*String localCookie = LocalCookieUtil.getCookies(); //保存的cookie
        if(!TextUtils.isEmpty(localCookie)){
            String cookie = mHeader.get("Cookie");
            if(cookie != null){
                cookie = cookie + ";" + localCookie;
            }else{
                mHeader = new HashMap<>();
                cookie = localCookie;
            }
            mHeader.put("Cookie",cookie);
        }*/
        if(mHeader == null){
            mHeader = new HashMap<>();
        }
        mHeader.put("Cache-Control","no-store,no-cache,must-revalidate");
        mHeader.put("Pragma","no-cache");
        return mHeader;
//        return mHeader.isEmpty()?super.getHeaders():mHeader;
    }

    /**额外设置的header,出去登录相关的*/
    public void setHeader ( Map<String, String> header){
        mHeader = header;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if(mParam == null){
            mParam = new TreeMap<>();
        }
//      /*  mParam.put("clientInfo", PhoneInformation.getInstance().getClientJsonString());
      /*  mParam.put("ts", System.currentTimeMillis() + "");
        mParam.put("signature", Md5Util.getMD5Str(UrlUtil.getSignatureParams((TreeMap) mParam)));*/
        return mParam;
    }
    private Map<String, String> mHeader = new HashMap<>();
    private Map<String,String> mParam=new HashMap<String,String>();
}