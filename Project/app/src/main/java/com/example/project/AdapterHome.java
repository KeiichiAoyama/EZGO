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

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {
    private final Context context;
    private final ArrayList<MyItem> itemList;
    private String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";
    private Intent intent;

    public AdapterHome(Context context, ArrayList<MyItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public ImageView cardImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtCard);
            cardImg = itemView.findViewById(R.id.imgCard);
        }
    }

    @NonNull
    @Override
    public AdapterHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHome.MyViewHolder holder, int position) {
        MyItem item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
        String image = urlImg + item.getImage();
        Picasso.get().load(image).into(holder.cardImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> objectType = item.getObjectType();
                if (objectType == location.class) {
                    intent = new Intent(context, ExploreDetailActivity.class);
                    location loc = (location) item.getObj();
                    intent.putExtra("object", loc);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
