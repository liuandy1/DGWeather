package com.fgwx.dgweather.net;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by senghor on 2015/1/3.
 *
 */
public class VolleySingleton {
    private static final String DEFAULT_TAG = "DEFAULT";
    private static VolleySingleton sInstance;
    private RequestQueue mRequestQueue;
    //private ImageLoader mImageLoader;
    private static Context mContext; // application context

    private VolleySingleton(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
        //mImageLoader = new ImageLoader(mRequestQueue,new BitmapLruCache(context));
    }

    public static synchronized void init(Context context){
        if(context instanceof Application){
            sInstance = new VolleySingleton(context);
        }else{
            throw new RuntimeException("must be call on Application");
        }
    }
    public static synchronized VolleySingleton getInstance(){
        if(sInstance == null){
            throw new RuntimeException("must be call init() on subApplication onCreate function before call getInstance()");
        }
        return sInstance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(DEFAULT_TAG);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? DEFAULT_TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static class LoginJsonRequest extends Request<JSONObject> {
        //使用正则表达式从reponse的头中提取cookie内容的子串
        private static final Pattern pattern = Pattern.compile("Set-Cookie\\d*");
        private Map<String, String> mMap;
        private Response.Listener<JSONObject> mListener;
        public String cookieFromResponse;
        private String mHeader;
        private Map<String, String> sendHeader=new HashMap<String, String>(1);
        public LoginJsonRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map map) {
            super(Method.POST, url, errorListener);
            mListener = listener;
            mMap = map;
        }
        public LoginJsonRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            mListener = listener;
        }
        //当http请求是post时，则需要该使用该函数设置往里面添加的键值对
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            if(mMap == null){
                mMap = new TreeMap<>();
            }
//            mMap.put("clientInfo", PhoneInformation.getInstance().getClientJsonString());
            return mMap;
        }
        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString =
                        new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//                Log.e("login-xx", jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray array = new JSONArray();
                for (String key : response.headers.keySet()) {
                    Matcher matcher = pattern.matcher(key);
                    if (matcher.find()) {
                        cookieFromResponse = response.headers.get(key);
                        cookieFromResponse = cookieFromResponse.substring(0, cookieFromResponse.indexOf(";"));
//                        String keyValue[] = cookieFromResponse.split("=", -1);
                        array.put(cookieFromResponse);
//                        Cookie cookie = new BasicClientCookie(keyValue[0], keyValue[1]);
//                        cookies.add(cookie);
                    }
                }
//                Log.w("xx","get headers in parseNetworkResponse "+response.headers.toString());
//                Log.e("login-xx", array.toString());
                jsonObject.put("Cookie", array);
//                Log.w("xx", jsonObject.toString());
                return Response.success(jsonObject,
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }

        @Override
        protected void deliverResponse(JSONObject response) {
            mListener.onResponse(response);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            if(sendHeader == null){
                sendHeader = new HashMap<>();
            }
            sendHeader.put("Cache-Control","no-store,no-cache,must-revalidate");
            sendHeader.put("Pragma","no-cache");
            sendHeader.put("Connection","Close");
            return sendHeader;
        }
        public void setSendCookie(String cookie){
            if(sendHeader == null){
                sendHeader = new HashMap<>();
            }
            sendHeader.put("Cookie",cookie);
        }
    }
}
