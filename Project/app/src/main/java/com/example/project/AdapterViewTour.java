package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewTour extends RecyclerView.Adapter<AdapterViewTour.MyViewHolder> {
    private final Context context;
    final List<tour> originalTour;
    private List<tour> tour =  new ArrayList<>();

    public AdapterViewTour(Context context, List<tour> tour) {
        this.context = context;
        this.originalTour = tour;
        this.tour = tour;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public ImageView imgTour;

        public MyViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.tourNameView);
            imgTour = itemView.findViewById(R.id.imgTourView);
        }
    }

    @NonNull
    @Override
    public AdapterViewTour.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_tour, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewTour.MyViewHolder holder, int position) {
        tour trp = tour.get(position);

        holder.textTitle.setText(trp.tpName);

        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";
        String image = urlImg + trp.tpImage;

        Picasso.get().load(image).into(holder.imgTour);

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, TourDetailActivity.class);
            i.putExtra("object", trp);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return tour.size();
    }
}
