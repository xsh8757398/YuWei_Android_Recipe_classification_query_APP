package com.example.du.yuwei623_1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by du on 2018/6/28.
 */

public class MainFragment_3 extends Fragment{
    public static MainFragment_3 newInstance(String param1){
        MainFragment_3 fragment_3 = new MainFragment_3();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment_3.setArguments(args);
        return fragment_3;
    }

    public MainFragment_3(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_fragment_3,container,false);

        return view;
    }
}
