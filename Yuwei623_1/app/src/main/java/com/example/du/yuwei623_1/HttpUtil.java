package com.example.du.yuwei623_1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by du on 2018/7/3.
 */

class HttpUtil {
    /**
     * 构造通用参数timestamp、sig和respDataType
     *
     * @return
     */
    public static String createCommonParam() throws NoSuchAlgorithmException {
        // 时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // 签名
        String s = Config.ACCOUNT_SID + Config.AUTH_TOKEN + timestamp;
        MessageDigest md5 = MessageDigest.getInstance("MD5");


        md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(s.getBytes());
        String result = "";
        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }
//            return result;


//        String sig = DigestUtils.md5Hex(Config.ACCOUNT_SID + Config.AUTH_TOKEN + timestamp);
        Log.e(TAG, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        return "&timestamp=" + timestamp + "&sig=" + result + "&respDataType=" + Config.RESP_DATA_TYPE;
    }

//    /**
//     * post请求
//     *
//     * @param url
//     *            功能和操作
//     * @param body
//     *            要post的数据
//     * @return
//     * @throws IOException
//     */
    public static String post(String url, String body)
    {
        System.out.println("url:" + System.lineSeparator() + url);
        System.out.println("body:" + System.lineSeparator() + body);

        String result = "";
        try
        {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null)
            {
                if (firstLine)
                {
                    firstLine = false;
                } else
                {
                    result += System.lineSeparator();
                }
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

//    /**
//     * 回调测试工具方法
//     *
//     * @param url
//     * @param reqStr
//     * @return
//     */
    public static String postHuiDiao(String url, String body)
    {
        String result = "";
        try
        {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);

            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null)
            {
                if (firstLine)
                {
                    firstLine = false;
                } else
                {
                    result += System.lineSeparator();
                }
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
