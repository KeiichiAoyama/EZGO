package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {
    private Context context;
    private ArrayList<MyItem> itemList;

    public AdapterHome(Context context, ArrayList<MyItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtCard);
        }
    }

    @Override
    public AdapterHome.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_home, parent, false);
        return new AdapterHome.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHome.MyViewHolder holder, int position) {
        MyItem item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
