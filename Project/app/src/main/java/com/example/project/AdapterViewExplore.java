package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterViewExplore extends RecyclerView.Adapter<AdapterViewExplore.MyViewHolder> {
    private final Context context;
    private final ExploreData[] explore;

    public AdapterViewExplore(Context context, ExploreData[] explore) {
        this.context = context;
        this.explore = explore;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPlace, txtRating;
        public ImageView imgDis;
        public ToggleButton btnLike;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtPlace = itemView.findViewById(R.id.txtPlace);
            imgDis = itemView.findViewById(R.id.ImgDis);

        }
    }

    @NonNull
    @Override
    public AdapterViewExplore.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_explore, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewExplore.MyViewHolder holder, int position) {
        ExploreData exploreData = explore[position];
        holder.txtPlace.setText(exploreData.getPlace());
        holder.imgDis.setImageResource(exploreData.getGambar());;
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, ExploreDetailActivity.class);
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return explore.length;
    }
}

