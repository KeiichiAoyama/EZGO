package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AdapterViewHotel extends RecyclerView.Adapter<AdapterViewHotel.MyViewHolder> {
    private final Context context;
    private final HotelData[] hotel;

    public AdapterViewHotel(Context context, HotelData[] hotel) {
        this.context = context;
        this.hotel = hotel;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNama, txtPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNamaHotel);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }

    @NonNull
    @Override
    public AdapterViewHotel.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_hotel, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHotel.MyViewHolder holder, int position) {
        HotelData hotelData = hotel[position];
        holder.txtNama.setText(hotelData.getNama());
        //holder.txtPrice.setText(String.valueOf(hotelData.getHarga()));
    }

    @Override
    public int getItemCount() {
        return hotel.length;
    }
}
