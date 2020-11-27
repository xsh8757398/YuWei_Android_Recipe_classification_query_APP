package com.example.du.yuwei623_1;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.du.yuwei623_1.MainFragment_2_1.getRequest1;

/**
 * Created by du on 2018/7/2.
 */

public class OneDish extends AppCompatActivity {

    public static final String DISH_NAME = "dish_name";
    public static final String DISH_IMAGE_ID = "dish_image_id";

    public RecyclerView recyclerView1;
    private List<Steps> stepsList = new ArrayList<>();

    private Handler mHandler;

    String IMTRO;
    String INGREDIENTS;
    String BURDEN;
    String STEPS;
class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mSpace;
        }

    }

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }
}
    OneDishAdapter adapter1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_dish);

        Intent intent = getIntent();
        final String dishName = intent.getStringExtra("DISH_NAME");
        final String dishImageId = intent.getStringExtra("DISH_IMAGE_ID");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        final ImageView dishImageView = (ImageView)findViewById(R.id.dish_image_view);
        final TextView imtroText = (TextView)findViewById(R.id.imtro);
        final TextView ingredientsText = (TextView)findViewById(R.id.ingredients);
        final TextView burdenText = (TextView)findViewById(R.id.burden);
//        final TextView stepsText = (TextView)findViewById(R.id.steps);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView1 = (RecyclerView)findViewById(R.id.one_dish_step);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OneDish.this);
//        recyclerView1.addItemDecoration(new SpaceItemDecoration(1));
        recyclerView1.setLayoutManager(layoutManager);

        request(dishName);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 100) {
                    imtroText.setText(IMTRO);
                    ingredientsText.setText(INGREDIENTS);
                    burdenText.setText(BURDEN);
//                    stepsText.setText(STEPS);

                    collapsingToolbarLayout.setTitle(dishName);
                    Glide.with(OneDish.this).load(dishImageId).into(dishImageView);

                    adapter1 = new OneDishAdapter(OneDish.this,stepsList);
                    recyclerView1.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();

                }
            }
        };

    }

    void request(String dishName){
        getRequest1(dishName, new CallBack() {
            @Override
            public void Call(String result) {

                try {
                    JSONObject object = new JSONObject(result);
                    if(object.getInt("error_code")==0) {
                        String rs = object.getString("result");
                        JSONObject object1 = new JSONObject(rs);
                        String data = object1.getString("data");

                        JSONArray jsonArray = new JSONArray(data);
                        JSONObject object3 = jsonArray.getJSONObject(0);
                        final String id = object3.getString("id");
                        final String title = object3.getString("title");
//                        String albums = object3.getString("albums");
                        final String imtro = object3.getString("imtro");
                        final String ingredients = object3.getString("ingredients");
                        final String burden = object3.getString("burden");
                        final String steps = object3.getString("steps");
//                        JSONArray jsonArray1 = new JSONArray(albums);
//                        final String albums1 = jsonArray1.getString(0);
                        System.out.println("id//////////////////////////////" + id);
                        System.out.println("title//////////////////////////////" + title);
//                        System.out.println("albums//////////////////////////////" + albums);
//                        System.out.println("albums1//////////////////////////////" + albums1);
                        System.out.println("steps//////////////////////////////" + steps);
                        IMTRO = imtro;
                        INGREDIENTS = ingredients;
                        BURDEN = burden;
                        STEPS = steps;

                        JSONArray jsonArray1 = new JSONArray(STEPS);
                        int count = jsonArray1.length();
                        System.out.println("这个数组的长度是-------：" + count);
                        for(int i =0;i<count;i++){
                            JSONObject object4 = jsonArray1.getJSONObject(i);
                            String img = object4.getString("img");
                            String step = object4.getString("step");
                            System.out.println("这个图片是-------：" + img);
                            System.out.println("这个步骤是-------：" + step);
                            Steps steps1 = new Steps(step,img);
                            stepsList.add(steps1);
                        }

                        mHandler.sendEmptyMessage(100);
                    }else{
                        System.out.println(object.get("error_code")+":"+object.get("reason"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Steps{
        private String StepsName;
        private String StepsImage;

        public Steps(String stepsName, String stepsImage) {
            StepsName = stepsName;
            StepsImage = stepsImage;
        }

        public String getStepsName() {
            return StepsName;
        }

        public void setStepsName(String stepsName) {
            StepsName = stepsName;
        }

        public String getStepsImage() {
            return StepsImage;
        }

        public void setStepsImage(String stepsImage) {
            StepsImage = stepsImage;
        }
    }

//    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
//        int mSpace;
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
//            outRect.left = mSpace;
//            outRect.right = mSpace;
//            outRect.bottom = mSpace;
//            if (parent.getChildAdapterPosition(view) == 0) {
//                outRect.top = mSpace;
//            }
//
//        }
//
//        public SpaceItemDecoration(int space) {
//            this.mSpace = space;
//        }
//    }

}
