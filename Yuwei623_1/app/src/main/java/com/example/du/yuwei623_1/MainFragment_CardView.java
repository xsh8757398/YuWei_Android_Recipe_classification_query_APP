package com.example.du.yuwei623_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by du on 2018/7/2.
 */

public class MainFragment_CardView extends RecyclerView.Adapter<MainFragment_CardView.ViewHolder> {

    private Context cardContext;
    private List<MainFragment_2_ListMenu.Dish> cardDishList;

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView cardImage;
        TextView cardName;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            cardImage = (ImageView)view.findViewById(R.id.card_image);
            cardName = (TextView)view.findViewById(R.id.card_name);
        }
    }

    public MainFragment_CardView(Context context,List<MainFragment_2_ListMenu.Dish> CardList){
        this.cardContext = context;
        cardDishList = CardList;
    }

    @Override
    public MainFragment_CardView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(cardContext ==null){
            cardContext = parent.getContext();
        }
        View view = LayoutInflater.from(cardContext).inflate(R.layout.item_cardview,parent,false);
        final MainFragment_CardView.ViewHolder viewHolder = new MainFragment_CardView.ViewHolder(view);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MainFragment_2_ListMenu.Dish dish = cardDishList.get(position);
                Toast.makeText(v.getContext(),"you cliked view" + dish.getDish_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cardContext,OneDish.class);
                intent.putExtra("DISH_NAME",dish.getDish_name());
                intent.putExtra("DISH_IMAGE_ID",dish.getDish_image());
                cardContext.startActivity(intent);
            }
        });

        viewHolder.cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MainFragment_2_ListMenu.Dish dish = cardDishList.get(position);
                Toast.makeText(v.getContext(),"you cliked image" + dish.getDish_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cardContext,OneDish.class);
                intent.putExtra("DISH_NAME",dish.getDish_name());
                intent.putExtra("DISH_IMAGE_ID",dish.getDish_image());
                cardContext.startActivity(intent);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainFragment_CardView.ViewHolder holder, int position) {
        MainFragment_2_ListMenu.Dish cardDish = cardDishList.get(position);
        holder.cardName.setText(cardDish.getDish_name());
        Glide.with(cardContext).load(cardDish.getDish_image()).into(holder.cardImage);
    }

    @Override
    public int getItemCount() {
        return cardDishList.size();
    }
}
