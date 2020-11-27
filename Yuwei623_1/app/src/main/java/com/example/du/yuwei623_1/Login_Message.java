package com.example.du.yuwei623_1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by du on 2018/6/23.
 */

public class Login_Message extends Activity {

    private EditText m_userId = null;
    private EditText m_indentify_code = null;
    private Button m_send_indentify_code = null;
    private Button m_login = null;
    private Button m_return = null;
    String codeResult2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_message);

        m_userId = (EditText)findViewById(R.id.m_userId);
        m_indentify_code = (EditText)findViewById(R.id.m_indentify_code);
        m_send_indentify_code = (Button)findViewById(R.id.m_send_indentify_code);
        m_login = (Button)findViewById(R.id.m_login);
        m_return = (Button)findViewById(R.id.m_return);

        m_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Message.this,Login.class);
                startActivity(intent);
            }
        });

        m_send_indentify_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IndustrySMS1 industrySMS1 = new IndustrySMS1();
                codeResult2 = industrySMS1.sendMessage(m_userId.getText().toString());
                Toast.makeText(Login_Message.this,"发送成功",Toast.LENGTH_LONG).show();
//                Log.e("999999999999999999999999999999999999999999999"  + codeResult2 );
            }
        });

        m_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((m_indentify_code.getText().toString()).equals(codeResult2)){
                    register(m_userId.getText().toString());
                }else{
                    Toast.makeText(Login_Message.this,"验证码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void register(String userId) {
        String registerUrlStr = Constant.URL_Register + "?userId=" + userId + "&userPsd=111";
        new MyAsyncTask().execute(registerUrlStr);
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private TextView tv; // 举例一个UI元素，后边会用到

//        public MyAsyncTask(TextView v) {
//            tv = v;
//        }

        @Override
        protected void onPreExecute() {
            Log.w("WangJ", "task onPreExecute()");
        }

        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        protected String doInBackground(String... params) {
            Log.w("WangJ", "task doInBackground()");
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = response.toString();

//            Log.d(TAG, "doInBackground: "+s);
            return s; // 这里返回的结果就作为onPostExecute方法的入参
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }

        /**
         * 运行在UI线程中，所以可以直接操作UI元素
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            if (!"".equals(s)) {
//                LogUtil.logResponse(s); // 日志输出原始应答报文
                try {
                    JSONObject resultJson = new JSONObject(s); // 此处就可以将服务端返回的Json的字符串还原成Json格式的数据
                    String resCode = resultJson.getString("resCode");
                    // 下边就可以根据需求将Json转化合适的数据结构来使用了
                    // ... ...自己的业务逻辑

                    Log.e("WangJ", resCode);
                    String zero = "0";

                    if(resCode.equals("100")){
                        Toast.makeText(Login_Message.this,"登陆成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login_Message.this,MainPage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login_Message.this,"账号不存在，请先注册",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("WangJ", "结果为空！");
            }
        }
    }


}
