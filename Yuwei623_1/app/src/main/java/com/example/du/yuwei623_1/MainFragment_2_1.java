package com.example.du.yuwei623_1;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by du on 2018/6/28.
 */

public class MainFragment_2_1 {
    public static String DEF_CHATSET = "UTF-8";
    public static int DEF_CONN_TIMEOUT = 30000;
    public static int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="389487f25cbfd729ba235de797587ecd";



    //1.菜谱大全    根据菜名查出菜谱
    public static void getRequest1(String name,CallBack callBack){

        String result =null;
        String url ="http://apis.juhe.cn/cook/query.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("menu",name);//需要查询的菜谱名
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("pn","");//数据返回起始下标
        params.put("rn","");//数据返回条数，最大30
        params.put("albums","");//albums字段类型，1字符串，默认数组

        try {
            net(url, params, "GET",callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.分类标签列表    查有多少种分类，不能查出菜谱
    public static void getRequest2(String name,CallBack callBack){
        String result =null;
        String url ="http://apis.juhe.cn/cook/category";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("parentid",name);//分类ID，默认全部
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            net(url, params, "GET",callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.按标签检索菜谱
    public static void getRequest3(String name,int start,int count,CallBack callBack){
        String result =null;
        String url ="http://apis.juhe.cn/cook/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("cid",name);//标签ID
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("pn",start);//数据返回起始下标，默认0
        params.put("rn",count);//数据返回条数，最大30，默认10
        params.put("format","");//steps字段屏蔽，默认显示，format=1时屏蔽

        try {
            net(url, params, "GET",callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
    //4.按菜谱ID查看详细
    public static void getRequest4(String name,CallBack callBack){
        String result =null;
        String url ="http://apis.juhe.cn/cook/queryid";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("id",name);//菜谱的ID
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            net(url, params, "GET",callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static void net(final String strUrl, final Map params, final String method, final CallBack callBack) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                BufferedReader reader = null;
                String rs = null;
                try {
                    StringBuffer sb = new StringBuffer();

                    String s="";
                    if(method==null || method.equals("GET")){
                        s = strUrl+"?"+urlencode(params);
                    }

                    URL url = new URL(s);
                    conn = (HttpURLConnection) url.openConnection();
                    if(method==null || method.equals("GET")){
                        conn.setRequestMethod("GET");
                    }else{
                        conn.setRequestMethod("POST");
                        conn.setDoOutput(true);
                    }
                    conn.setRequestProperty("User-agent", userAgent);
                    conn.setUseCaches(false);
                    conn.setConnectTimeout(DEF_CONN_TIMEOUT);
                    conn.setReadTimeout(DEF_READ_TIMEOUT);
                    conn.setInstanceFollowRedirects(false);
                    conn.connect();
                    if (params!= null && method.equals("POST")) {
                        try {
                            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                            out.writeBytes(urlencode(params));
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }
                    InputStream is = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        sb.append(strRead);
                    }
                    rs = sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
                String result = rs;
                Log.e(TAG, "run:2333333333333333222222222222 "+result );
                callBack.Call(result);
            }
        }).start();
    }


    //将map型转为请求参数型鱼香肉丝
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
