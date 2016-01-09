package com.fgwx.dgweather.utils;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * 作者：燕青 on 2016/1/6 18:47
 * 邮箱：359222347@qq.com
 */
public class SpeechUtil {

    private static SpeechSynthesizer mTts;

    /**
     * 考试时播报
     *
     * @param context
     * @param str     要播报的文字
     */
    public static void broadcastWeather(Context context, String str, SynthesizerListener listener) {
        // 初始化合成对象
        if (mTts == null) {
            mTts = SpeechSynthesizer.createSynthesizer(context, null);
            setParam(mTts);
        }
        mTts.startSpeaking(str, listener);
    }

    /**
     * 暂停播报
     *
     * @param context
     */
    public static void pauseSpeech(Context context) {
        if (mTts == null) {
            mTts = SpeechSynthesizer.createSynthesizer(context, null);
            setParam(mTts);
        }
        mTts.pauseSpeaking();
    }

    /**
     * 停止播报
     *
     * @param context
     */
    public static void stopSpeech(Context context) {
        if (mTts == null) {
            mTts = SpeechSynthesizer.createSynthesizer(context, null);
            setParam(mTts);
        }
        mTts.stopSpeaking();
    }


    private static void setParam(SpeechSynthesizer mTts) {
        mTts.setParameter(SpeechConstant.PARAMS, null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoqi");
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
    }
}
