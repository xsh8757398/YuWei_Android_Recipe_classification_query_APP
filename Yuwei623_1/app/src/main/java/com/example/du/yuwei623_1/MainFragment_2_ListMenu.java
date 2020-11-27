package com.example.du.yuwei623_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.du.yuwei623_1.MainFragment_2_1.getRequest3;

/**
 * Created by du on 2018/6/30.
 */

public class MainFragment_2_ListMenu extends Activity {
    private static final String TAG = "MainFragment_2_ListMenu";
    public RecyclerView recyclerView;
    private List<Dish> dishList = new ArrayList<>();
    private Handler mHandler;
    public static final int UPDATE_TEXT = 1;
    int start = 0;
    int count = 1;
    MainFragment_2_ListMenuAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_2_listmenu);

        final Intent intent = this.getIntent();
        final String a = intent.getStringExtra("id");
        System.out.println("*****************************" + a);

        recyclerView = (RecyclerView)findViewById(R.id.lishMenuDish);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        for(start=0;start<10;start++){
            request(a,start,count);
        }


        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 100) {
                    adapter = new MainFragment_2_ListMenuAdapter(MainFragment_2_ListMenu.this,dishList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        };

    }

    private void request(String a, final int start, int count){
            getRequest3(a, start, count, new CallBack() {
                @Override
                public void Call(String result1) {
                    try {
                        JSONObject object;

                        object = new JSONObject(result1);

                        if (object.getInt("error_code") == 0) {
                            String result = object.getString("result");

                            JSONObject object1 = new JSONObject(result);
                            String data = object1.getString("data");

                            JSONArray jsonArray = new JSONArray(data);
                            JSONObject object3 = jsonArray.getJSONObject(0);
                            final String id = object3.getString("id");
                            final String title = object3.getString("title");
                            String albums = object3.getString("albums");
                            JSONArray jsonArray1 = new JSONArray(albums);
                            final String albums1 = jsonArray1.getString(0);
//                        System.out.println("id//////////////////////////////" + id);
//                        System.out.println("title//////////////////////////////" + title);
//                        System.out.println("albums//////////////////////////////" + albums);
//                        System.out.println("albums1//////////////////////////////" + albums1);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
                                    System.out.println("id//////////////////////////////" + id);
                                    System.out.println("title//////////////////////////////" + title);
                                    Dish dish = new Dish(id, title, albums1);
                                    dishList.add(dish);
//                                }
//                            });
                        mHandler.sendEmptyMessage(start*10+10);

                        } else {
                            System.out.println(object.get("error_code") + ":" + object.get("reason"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

//    private void initDish(String id,String title) {
//        Dish dish1 = new Dish(id,title,url);
//        dishList.add(dish1);
//    }

    public static class Dish{
        String dish_id;
        String dish_name;
        String dish_image;

        public Dish(String dish_id, String dish_name, String dish_image) {
            this.dish_id = dish_id;
            this.dish_name = dish_name;
            this.dish_image = dish_image;
        }

        public String getDish_id() {
            return dish_id;
        }

        public void setDish_id(String dish_id) {
            this.dish_id = dish_id;
        }

        public String getDish_name() {
            return dish_name;
        }

        public void setDish_name(String dish_name) {
            this.dish_name = dish_name;
        }

        public String getDish_image() {
            return dish_image;
        }

        public void setDish_image(String dish_image) {
            this.dish_image = dish_image;
        }
    }

//    public void loadImage(View view) {
//        String url = "https://www.baidu.com/img/bd_logo1.png";
//        Glide.with(this)
//                .load(url)
//                .into(test);
//    }
}
