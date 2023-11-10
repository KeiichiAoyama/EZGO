package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterViewTour extends RecyclerView.Adapter<AdapterViewTour.MyViewHolder> {
    private Context context;
    private ArrayList<MyItem> itemList;

    public AdapterViewTour(Context context, ArrayList<MyItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtPlace);
        }
    }

    @Override
    public AdapterViewTour.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_explore, parent, false);
        return new AdapterViewTour.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewTour.MyViewHolder holder, int position) {
        MyItem item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}