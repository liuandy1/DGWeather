package com.fgwx.dgweather.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgwx.dgweather.R;
import com.fgwx.dgweather.utils.LogUtil;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import java.util.ArrayList;

/**
 * Created by senghor on 2015/12/24.
 */
//监测
public class MonitorFragment extends Fragment {

    //    private static final String APPID = "appid=519328ab";
    // 语音合成对象
    private SpeechSynthesizer mTts;
    private InitListener mTtsInitListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, null, false);
        initView(view);
        return view;

    }

    private void initView(View view) {

        mTtsInitListener = new InitListener() {
            @Override
            public void onInit(int i) {
                LogUtil.e("InitListener init() code = " + i);
                if (i != ErrorCode.SUCCESS) {
//                    showTip("初始化失败,错误码："+i);
                } else {
                    // 初始化成功，之后可以调用startSpeaking方法
                    // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                    // 正确的做法是将onCreate中的startSpeaking调用移至这里
                }
            }
        };

        // 初始化合成对象
        mTts = SpeechSynthesizer.createSynthesizer(getActivity(), mTtsInitListener);
        view.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "唯衣是一家零售兼批发的服装，全称是广州唯衣网络科技有限公司。公司坐落在广东省广州市，拒绝经营假名牌和仿名牌，只做最主流的，最时尚的，品质最好的品牌女装，产品汇集国内外中高档知名女装品牌、一线女装精品，并与其结成战略联盟，每季提供近千余品种款式供我们的客户和消费者们选择。";

                setParam();
                mTts.startSpeaking(str, new SynthesizerListener() {
                    @Override
                    public void onSpeakBegin() {
                        LogUtil.e("开始播放");
                    }

                    @Override
                    public void onBufferProgress(int i, int i1, int i2, String s) {

                    }

                    @Override
                    public void onSpeakPaused() {

                    }

                    @Override
                    public void onSpeakResumed() {

                    }

                    @Override
                    public void onSpeakProgress(int i, int i1, int i2) {

                    }

                    @Override
                    public void onCompleted(SpeechError speechError) {
                        LogUtil.e("播放结束");

                    }

                    @Override
                    public void onEvent(int i, int i1, int i2, Bundle bundle) {

                    }
                });
            }
        });
    }

    private void setParam() {
        mTts.setParameter(SpeechConstant.PARAMS, null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoqi");
//        if(!"neutral".equals(emot)){
//            // 当前仅发音人“小艾”支持设置情感
//            // “小艾”发音人需要付费使用，具体请联系：msp_support@iflytek.com
//            mTts.setParameter(SpeechConstant.EMOT, emot);
//        }
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");

        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
//        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.wav");

    }

    @Override
    public void onResume() {
        super.onResume();

        //移动数据统计分析
//        FlowerCollector.onResume(TtsDemo.this);
//        FlowerCollector.onPageStart(TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTts.stopSpeaking();
        // 退出时释放连接
        mTts.destroy();
    }
}
