package com.example.du.yuwei623_1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by du on 2018/6/27.
 */

public class MainFragment extends Fragment {

    SearchView searchView;
    List<MainFragment_2_ListMenu.Dish> cardDishList = new ArrayList<>();
    MainFragment_CardView adapter;
    Handler mHandler;

    public static MainFragment newInstance(String param1){
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.main_fragment,container,false);

        searchView = (SearchView) view.findViewById(R.id.searchView);

        initCard();

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setIconified(true);
                Log.e(TAG, "onQueryTextSubmit: "+ query );
                Intent intent = new Intent(getContext(),OneDish.class);
                intent.putExtra("DISH_NAME",query);
                startActivity(intent);
                return true;
            }

            //搜索框内部改变回调，newText就是搜索框里的内容
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange: " + newText);
                Toast.makeText(getActivity(),newText,Toast.LENGTH_SHORT).show();    //这里面就是一个Toast
                return true;
            }
        });

//        mHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what == 100) {
//
//                }
//            }

        RecyclerView card_recycler_view = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        card_recycler_view.setLayoutManager(layoutManager);
        adapter = new MainFragment_CardView(getActivity(), cardDishList);
        card_recycler_view.setAdapter(adapter);
        return view;
    }

    void initCard(){
        final MainFragment_2_ListMenu.Dish dish1 = new MainFragment_2_ListMenu.Dish("123","春笋红烧肉",url1);
        final MainFragment_2_ListMenu.Dish dish2 = new MainFragment_2_ListMenu.Dish("123","四宝炸酱",url2);
        final MainFragment_2_ListMenu.Dish dish3 = new MainFragment_2_ListMenu.Dish("123","小鸡炖蘑菇",url3);
        final MainFragment_2_ListMenu.Dish dish4 = new MainFragment_2_ListMenu.Dish("123","水煮牛肉",url4);
        final MainFragment_2_ListMenu.Dish dish5 = new MainFragment_2_ListMenu.Dish("123","酱肘子",url5);
        cardDishList.add(dish1);
        cardDishList.add(dish2);
        cardDishList.add(dish3);
        cardDishList.add(dish4);
        cardDishList.add(dish5);
//        mHandler.sendEmptyMessage(100);
    }

    String ADDRESS = "http://10.0.161.102:8080/img/";
    String url1 = ADDRESS +"11091_451805.jpg";
    String url2 = ADDRESS +"11131_393183.jpg";
    String url3 = ADDRESS +"9059_218076.jpg";
    String url4 = ADDRESS +"7401_189475.jpg";
    String url5 = ADDRESS +"8850_796364.jpg";
}
