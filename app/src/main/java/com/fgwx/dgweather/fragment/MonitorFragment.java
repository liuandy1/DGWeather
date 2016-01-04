package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;
import com.iflytek.ui.SynthesizerDialog;
import com.iflytek.ui.SynthesizerDialogListener;

import java.util.ArrayList;

/**
 * Created by senghor on 2015/12/24.
 */
//监测
public class MonitorFragment extends Fragment{
    private static final String APPID = "appid=519328ab";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_monitor,null,false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        view.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这是语言合成部分 同样需要实例化一个SynthesizerDialog ，并输入appid
                SynthesizerDialog syn = new SynthesizerDialog(getActivity(), APPID);
                syn.setListener(new SynthesizerDialogListener() {

                    @Override
                    public void onEnd(SpeechError arg0) {

                    }
                });
                String str = "唯衣是一家零售兼批发的服装，全称是广州唯衣网络科技有限公司。公司坐落在广东省广州市，拒绝经营假名牌和仿名牌，只做最主流的，最时尚的，品质最好的品牌女装，产品汇集国内外中高档知名女装品牌、一线女装精品，并与其结成战略联盟，每季提供近千余品种款式供我们的客户和消费者们选择。";
                // 根据EditText里的内容实现语音合成
//                syn.setText(et.getText().toString(), null);
                syn.setText(str,null);
                syn.show();

            }
        });
    }

    // 语言识别监听器，有两个方法
    RecognizerDialogListener recoListener = new RecognizerDialogListener() {
        @Override
        public void onResults(ArrayList<RecognizerResult> arrayList, boolean b) {

        }

        @Override
        public void onEnd(SpeechError speechError) {

        }
//
//        @Override
//        public void onResults(ArrayList<RecognizerResult> results,
//                              boolean isLast) {
//            // 新增加了一个ToggleButton tb，首先检查tb是否被按下，如果被按下才进行语言控制，没被按下就进行文字识别
//            if (tb.isChecked()) {
//                // doVoice方法就是进行识别
//                doVoice(results);
//            } else {
//                // 服务器识别完成后会返回集合，我们这里就只得到最匹配的那一项
////                text += results.get(0).text;
////                System.out.println(text);
//            }
//
//        }
//
//        // 首先迭代结果，然后获取每个结果，并进行对比，如果包含有特定字符串，那么就执行相应Intent跳转。
//        // 注意 凡是Intent能办到的（发邮件，跳到已安装应用，拨号，发短信，发彩信，浏览网页，播放多媒体。。。。），它就都能办到。
//        private void doVoice(ArrayList<RecognizerResult> results) {
//            Intent i = new Intent();
//            for (RecognizerResult result : results) {
//                if (result.text.contains("天气")) {
//                    // 天气界面的跳转
//                    i.setClass(Voice1Activity.this, Weather.class);
//                    startActivity(i);
//                } else if (result.text.contains("新闻")) {
//                    // 新闻界面的跳转
//                    i.setClass(Voice1Activity.this, News.class);
//                    startActivity(i);
//                } else if (result.text.contains("短信")) {
//                    // 短信界面的跳转
//                    i.setAction(Intent.ACTION_VIEW);
//                    i.setType("vnd.android-dir/mms-sms");
//                    startActivity(i);
//                } else {
//                    // 如果没有相应指令就用Toast提示用户
//                    Toast.makeText(Voice1Activity.this, "无法识别",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        }
//
//        @Override
//        public void onEnd(SpeechError error) {
//            if (error == null) {
//                // 完成后就把结果显示在EditText上
//                et.setText(text);
//            }
//        }
    };


}
