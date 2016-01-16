package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.base.BaseFragment;
import com.fgwx.dgweather.view.MultiWarnPopupwindow;

/**
 * Created by senghor on 2015/12/24.
 */
// 互动
public class InteractFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interact, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getActivity().findViewById(R.id.tv_test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showPopupwindow();
                showShare("东莞", "晴转多云", "16~21度", "东南风", "3级");
            }
        });
    }

    /**
     * area   地区
     * weaDesc   天气描述
     * tempRange   温度范围
     * windDirec   风向
     * windSpeed   风速
     */
    private void showShare(String area, String weaDesc, String tempRange, String windDirec, String windSpeed) {
        ShareSDK.initSDK(getActivity());
        cn.sharesdk.onekeyshare.OnekeyShare oks = new cn.sharesdk.onekeyshare.OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
        // oks.setNotification(R.drawable.ic_launcher,
        // getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://mob.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("亲！好的天气带来好的心情！东莞天气提醒您，" + area + "今天的天气是" + weaDesc + "，" + tempRange + "，" + windDirec + "，"
                + windSpeed + "。\n");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这是评论");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getActivity());
    }

    public void showPopupwindow() {
        MultiWarnPopupwindow popupWindown = new MultiWarnPopupwindow(getActivity());
        popupWindown.showAtLocation(getActivity().findViewById(R.id.ll), Gravity.CENTER, 0, 0);
    }
}
