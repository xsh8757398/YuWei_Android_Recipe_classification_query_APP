package com.example.du.yuwei623_1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by du on 2018/7/3.
 */

public class IndustrySMS1 {
    private static final String TAG = "IndustrySMS";

    private static String operation = "/industrySMS/sendSMS";

    private static String accountSid = Config.ACCOUNT_SID;

    public String to;

    private static String smsContent;

    String result;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        Log.e(TAG, "22222222222222222222222222222222222222" );
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.login);
//    }

     String sendMessage(String phoneNumber){
        to = phoneNumber;
        int  code = (int)((Math.random()*9+1)*100000);
        smsContent ="【余味】"+ "登录验证码：{" + code + "}，如非本人操作，请忽略此短信。";
        try {
            execute();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ""+code;
    }


    /**
     * 验证码通知短信
     */
    public void execute() throws NoSuchAlgorithmException {
        Log.e(TAG, "111111111111111111111111111111" );
        String tmpSmsContent = null;
        try{
            Log.e(TAG, "88888888888888888888888888888888888888888"+URLDecoder.decode(smsContent, "UTF-8"));

//            tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");

            tmpSmsContent = URLDecoder.decode(smsContent, "UTF-8");

        }catch(Exception e){

        }
        final String url = Config.BASE_URL + operation;
        final String body = "accountSid=" + accountSid +
                "&to=" + to + "&smsContent="
                + tmpSmsContent
                + HttpUtil.createCommonParam();


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 提交请求
                System.out.println("result:--------------------------------------------------" + result);
                result = HttpUtil.post(url, body);
                System.out.println("result:--------------------------------------------------" + result);
            }
        }).start();

        System.out.println("result:" + System.lineSeparator() + result);
    }
}
