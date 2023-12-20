package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {
    private ArrayList<SearchItem> courseModelArrayList;
    private final Context context;
    private Intent intent;

    public AdapterSearch(ArrayList<SearchItem> courseModelArrayList, Context context) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
    }

    public void setData(ArrayList<SearchItem> newData) {
        this.courseModelArrayList = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView objName;
        private final TextView objType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            objName = itemView.findViewById(R.id.searchName);
            objType = itemView.findViewById(R.id.searchType);
        }
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder holder, int position) {
        SearchItem model = courseModelArrayList.get(position);
        holder.objName.setText(model.getTitle());
        holder.objType.setText(model.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> objectType = model.getObjectType();
                if (objectType == location.class) {
                    intent = new Intent(context, ExploreDetailActivity.class);
                    location loc = (location) model.getObj();
                    intent.putExtra("object", loc);
                } else if (objectType == ticket.class) {
                    intent = new Intent(context, TicketDetailActivity.class);
                    ticket tix = (ticket) model.getObj();
                    intent.putExtra("object", tix);
                } else if (objectType == hotel.class) {
                    intent = new Intent(context, HotelDetailActivity.class);
                    hotel htl = (hotel) model.getObj();
                    intent.putExtra("object", htl);
                } else if (objectType == tour.class) {
                    intent = new Intent(context, TourDetailActivity.class);
                    tour trp = (tour) model.getObj();
                    intent.putExtra("object", trp);
                }
                context.startActivity(intent);
            }
        });
    }
}
