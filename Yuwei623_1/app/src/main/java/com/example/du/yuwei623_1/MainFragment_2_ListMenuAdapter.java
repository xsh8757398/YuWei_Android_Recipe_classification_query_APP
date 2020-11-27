package com.example.du.yuwei623_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by du on 2018/6/30.
 */

public class MainFragment_2_ListMenuAdapter extends RecyclerView.Adapter<MainFragment_2_ListMenuAdapter.ViewHolder> {
    private Context context;

    private List<MainFragment_2_ListMenu.Dish> dishList;

    public MainFragment_2_ListMenuAdapter(Context context,List<MainFragment_2_ListMenu.Dish> DishList) {
        this.context = context;
        dishList = DishList;
//        Log.e(TAG, "MainFragment_2_ListMenuAdapter: ================================================"+dishList.size() );
    }

    @Override
    public MainFragment_2_ListMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dishs, parent, false);
        final MainFragment_2_ListMenuAdapter.ViewHolder viewHolder = new MainFragment_2_ListMenuAdapter.ViewHolder(view);

        viewHolder.DishView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MainFragment_2_ListMenu.Dish dish = dishList.get(position);
                Toast.makeText(v.getContext(),"you cliked view" + dish.getDish_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,OneDish.class);
                intent.putExtra("DISH_NAME",dish.getDish_name());
                intent.putExtra("DISH_IMAGE_ID",dish.getDish_image());
                context.startActivity(intent);
            }
        });

        viewHolder.dishs_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MainFragment_2_ListMenu.Dish dish = dishList.get(position);
                Toast.makeText(v.getContext(),"you cliked image" + dish.getDish_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,OneDish.class);
                intent.putExtra("DISH_NAME",dish.getDish_name());
                intent.putExtra("DISH_IMAGE_ID",dish.getDish_image());
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainFragment_2_ListMenuAdapter.ViewHolder holder, int position) {

        MainFragment_2_ListMenu.Dish dish = dishList.get(position);
        int type = holder.getItemViewType();
//        Log.e(TAG, "onBindViewHolder: 11111111111111111111111111"+dish.getDish_image());
        if(type == 1){
            Glide.with(context).load(dish.getDish_image()).into(holder.dishs_image);
            holder.dishs_name.setText(dish.getDish_name());
        }else {
//            holder.menu_image.setImageResource(Glide.with(this).load(url).into());
            Glide.with(context).load(dish.getDish_image()).into(holder.dishs_image);
            holder.dishs_name.setText(dish.getDish_name());
        }

    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View DishView;
        ImageView dishs_image;
        TextView dishs_name;

        ViewHolder(View itemView) {
            super(itemView);
            DishView = itemView;
            dishs_image = itemView.findViewById(R.id.dishs_image);
            dishs_name = itemView.findViewById(R.id.dishs_name);
        }
    }
}
