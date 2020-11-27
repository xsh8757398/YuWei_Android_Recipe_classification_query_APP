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

public class Login extends Activity {

    private EditText l_userId = null;
    private EditText l_userPsw = null;
    private TextView l_message_code = null;
    private Button l_login = null;
    private Button l_register = null;
    private TextView l_forget_psd = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        l_userId = (EditText)findViewById(R.id.l_userId);
        l_userPsw = (EditText)findViewById(R.id.l_userPsw);
        l_message_code = (TextView)findViewById(R.id.l_message_code);
        l_login = (Button)findViewById(R.id.l_login);
        l_register = (Button)findViewById(R.id.l_register);
        l_forget_psd = (TextView)findViewById(R.id.l_forget_psd);

        l_message_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Login_Message.class);
                startActivity(intent);
            }
        });

        l_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(l_userId.getText().toString().isEmpty())
                        && !(l_userPsw.getText().toString().isEmpty())){
                    Log.e("WangJ", "都不空");
                    login(l_userId.getText().toString(), l_userPsw.getText().toString());
                } else {
                    Toast.makeText(Login.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent(Login.this,MainPage.class);
//                startActivity(intent);
            }
        });

        l_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        l_forget_psd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ForgetPsd.class);
                startActivity(intent);
            }
        });

    }

    private void login(String userId, String userPsd) {
        String s = "";
        String registerUrlStr = Constant.URL_Login + "?userId=" + userId + "&userPsd=" + userPsd;
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
                    if(resCode.equals(zero)){
                        Log.e("WangJ", "---------------------------------------------------------------------");
                        Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this,MainPage.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"账号或密码错误",Toast.LENGTH_LONG).show();
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
