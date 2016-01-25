package cn.sharesdk.onekeyshare.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.utils.Constant;
import com.fgwx.dgweather.utils.ScreenShoot;

import java.io.File;
import java.util.UUID;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by M on 2016/1/23.
 */
public class ShareSDKUtil {

    private static String WEB_ADDRESS = "http://www.dg121.com/default.asp";
    /**
     * 带参数的分享
     * area   地区
     * weaDesc   天气描述
     * tempRange   温度范围
     * windDirec   风向
     * windSpeed   风速
     */
    public static void showShare(Activity context,String area, String weaDesc, String tempRange, String windDirec, String windSpeed) {
        ShareSDK.initSDK(context);
        //分享的图片
        String filePath = Constant.IMAGE_PATH + UUID.randomUUID().toString() + ".jpg";
        ScreenShoot.shoot(context,new File(filePath));

        cn.sharesdk.onekeyshare.OnekeyShare oks = new cn.sharesdk.onekeyshare.OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
        // oks.setNotification(R.drawable.ic_launcher,
        // getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(context.getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(WEB_ADDRESS);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("亲！好的天气带来好的心情！东莞天气提醒您，" + area + "今天的天气是" + weaDesc + "，" + tempRange + "，" + windDirec + "，"
                + windSpeed + "。\n");
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(filePath);// 确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(WEB_ADDRESS);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这是评论");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(WEB_ADDRESS);

        // 启动分享GUI
        oks.show(context);
    }
}
