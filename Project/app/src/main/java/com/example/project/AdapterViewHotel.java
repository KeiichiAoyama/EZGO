package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class AdapterViewHotel extends RecyclerView.Adapter<AdapterViewHotel.MyViewHolder> {
    private final Context context;
    final HotelData[] originalHotel;
    private HotelData[] hotel;

    public AdapterViewHotel(Context context, HotelData[] hotel) {
        this.context = context;
        this.originalHotel = hotel.clone();
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
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String price = "Rp "+decimalFormat.format(hotelData.getPrice())+"/Pax";
        holder.txtNama.setText(hotelData.getName());
        holder.txtPrice.setText(price);
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, HotelDetailActivity.class);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return hotel.length;
    }

    public void filterHotel(String locText, String dateText) {
        ArrayList<HotelData> filteredList = new ArrayList<>();

        for (HotelData hotelData : originalHotel) {
            String location = hotelData.getAddress();
            String date = hotelData.getDate();

            boolean isFromMatch = location.toLowerCase().contains(locText.toLowerCase());
            boolean isDateMatch = date.equals(dateText);

            if (isFromMatch && isDateMatch) {
                filteredList.add(hotelData);
            }
        }

        hotel = filteredList.toArray(new HotelData[0]);
        notifyDataSetChanged();
    }
}
