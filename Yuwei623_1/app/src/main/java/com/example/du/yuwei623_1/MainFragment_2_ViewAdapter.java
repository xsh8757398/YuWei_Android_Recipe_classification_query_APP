package com.example.du.yuwei623_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by du on 2018/6/29.
 */

public class MainFragment_2_ViewAdapter extends RecyclerView.Adapter<MainFragment_2_ViewAdapter.ViewHolder> {
    private Context context;

    private List<MainFragment_2.Menu> menuList;

    public MainFragment_2_ViewAdapter(Context context,List<MainFragment_2.Menu> MenuList) {
        this.context = context;
        menuList = MenuList;
    }

    @Override
    public MainFragment_2_ViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, parent, false);
        final MainFragment_2_ViewAdapter.ViewHolder viewHolder = new MainFragment_2_ViewAdapter.ViewHolder(view);

        viewHolder.MenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MainFragment_2.Menu menu = menuList.get(position);
                Toast.makeText(v.getContext(),"you cliked view" + menu.getMenu_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),MainFragment_2_ListMenu.class);
                intent.putExtra("id",menu.getMenu_id());
                startActivity(v.getContext(),intent,new Bundle());
            }
        });

        viewHolder.menu_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MainFragment_2.Menu menu = menuList.get(position);
                Toast.makeText(v.getContext(),"you cliked image" + menu.getMenu_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),MainFragment_2_ListMenu.class);
                intent.putExtra("id",menu.getMenu_id());
                startActivity(v.getContext(),intent,new Bundle());
            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MainFragment_2_ViewAdapter.ViewHolder holder, int position) {
        MainFragment_2.Menu menu = menuList.get(position);
        int type = holder.getItemViewType();
        if(type == 1){
            Glide.with(context).load(menu.getImageId()).into(holder.menu_image);
            holder.menu_name.setText(menu.getMenu_name());
        }else {
//            holder.menu_image.setImageResource(Glide.with(this).load(url).into());
            Glide.with(context).load(menu.getImageId()).into(holder.menu_image);
            holder.menu_name.setText(menu.getMenu_name());
        }

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View MenuView;
        ImageView menu_image;
        TextView menu_name;

        ViewHolder(View itemView) {
            super(itemView);
            MenuView = itemView;
            menu_image = itemView.findViewById(R.id.menu_image);
            menu_name = itemView.findViewById(R.id.menu_name);
        }

    }
}
