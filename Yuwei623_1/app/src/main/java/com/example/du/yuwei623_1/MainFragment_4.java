package com.example.du.yuwei623_1;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by du on 2018/6/28.
 */

public class MainFragment_4 extends Fragment {

    private static final String TAG = "MainFragment_4";

    int recLen = 0;
    ProgressDialog progressDialog;
    TextView textView;
    private Mycount mc;
    EditText minute;
    EditText second;
    Button startbtn;
    Button stopbtn;
    String min;
    String s;

    public static MainFragment_4 newInstance(String param1){
        MainFragment_4 fragment_4 = new MainFragment_4();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment_4.setArguments(args);
        return fragment_4;
    }

    public MainFragment_4(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.setTitle("到时间啦~~~~~~");
        progressDialog.setMessage("");
        progressDialog.setCancelable(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_fragment_4,container,false);
        textView = (TextView)view.findViewById(R.id.timertask);
        minute = (EditText)view.findViewById(R.id.minute);
        second = (EditText)view.findViewById(R.id.second);
        startbtn = (Button)view.findViewById(R.id.startbtn);
        stopbtn = (Button)view.findViewById(R.id.stopbtn);
        minute.setInputType( InputType.TYPE_CLASS_NUMBER);
        minute.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        second.setInputType( InputType.TYPE_CLASS_NUMBER);
        second.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min = minute.getText().toString();
                s = second.getText().toString();
                int count_min =Integer.parseInt(min);
                int count_s =Integer.parseInt(s);
                int AllTime = (count_min*60+count_s)*1000;
                mc = new Mycount(AllTime,1000);
                mc.start();
                Log.e(TAG, "minminminminminminminminminminminminminminminmin" + count_min);
                Log.e(TAG, "minminminminminminminminminminminminminminminmin" + count_s);
            }
        });

        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mc.cancel();
            }
        });

        return view;
    }

    class Mycount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText("请等待" + millisUntilFinished/1000 + "秒");
        }

        @Override
        public void onFinish() {
            progressDialog.show();
        }


    }
}
