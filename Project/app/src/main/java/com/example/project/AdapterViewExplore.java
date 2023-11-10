package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterViewExplore extends RecyclerView.Adapter<AdapterViewExplore.MyViewHolder> {
        private Context context;
        private ExploreData[] explore;

        public AdapterViewExplore(Context context, ExploreData[] explore) {
            this.context = context;
            this.explore = explore;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtPlace, txtRating;
            public ImageView imgDis;
            public ToggleButton btnLike;

            public MyViewHolder(View itemView) {
                super(itemView);
                txtPlace = itemView.findViewById(R.id.txtPlace);
                txtRating = itemView.findViewById(R.id.txtRating);
                imgDis = itemView.findViewById(R.id.ImgDis);
                btnLike = itemView.findViewById(R.id.btnLike);

            }
        }

        @Override
        public AdapterViewExplore.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.card_explore, parent, false);
            return new AdapterViewExplore.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AdapterViewExplore.MyViewHolder holder, int position) {
            ExploreData exploreData = explore[position];
            holder.txtPlace.setText(exploreData.getPlace());
            holder.imgDis.setImageResource(exploreData.getGambar());
            holder.btnLike.setChecked(exploreData.isLike());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ExploreDetailActivity.class);
                    context.startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return explore.length;
        }
    }

