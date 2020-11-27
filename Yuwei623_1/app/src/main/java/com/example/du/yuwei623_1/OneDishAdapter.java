package com.example.du.yuwei623_1;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by du on 2018/7/2.
 */

public class OneDishAdapter extends RecyclerView.Adapter<OneDishAdapter.ViewHolder> {
    private Context context;

    private List<OneDish.Steps> stepsList;

    class ViewHolder extends RecyclerView.ViewHolder{
        View StepView;
        ImageView stepsImage;
        TextView stepsName;

        public ViewHolder(View itemView){
            super(itemView);
            StepView = itemView;
            stepsImage = (ImageView)itemView.findViewById(R.id.steps_image);
            stepsName = (TextView)itemView.findViewById(R.id.steps_text);
        }

    }

    public OneDishAdapter(Context context,List<OneDish.Steps> StepsList){
        this.context = context;
        stepsList = StepsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steps,parent,false);
        OneDishAdapter.ViewHolder holder = new OneDishAdapter.ViewHolder(view) ;
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OneDish.Steps steps = stepsList.get(position);
        Glide.with(context).load(steps.getStepsImage()).into(holder.stepsImage);
        holder.stepsName.setText(steps.getStepsName());

    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

}
