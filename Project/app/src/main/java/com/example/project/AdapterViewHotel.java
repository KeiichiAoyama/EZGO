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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AdapterViewHotel extends RecyclerView.Adapter<AdapterViewHotel.MyViewHolder> {
    private final Context context;
    final List<hotel> originalHotel;
    private List<hotel> hotel =  new ArrayList<>();

    public AdapterViewHotel(Context context, List<hotel> hotel) {
        this.context = context;
        this.originalHotel = hotel;
        this.hotel = hotel;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNama, txtPrice, txtKamar, txtMalam, txtDate;
        public ImageView imgHotel;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNamaHotel);
            txtPrice = itemView.findViewById(R.id.hotelPrice);
            txtKamar = itemView.findViewById(R.id.txtKamarHotel);
            txtMalam = itemView.findViewById(R.id.txtMalamHotel);
            txtDate = itemView.findViewById(R.id.txtDateHotel);
            imgHotel = itemView.findViewById(R.id.imgHotelView);
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
        hotel hotelData = hotel.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String price = decimalFormat.format(hotelData.hPrice)+"/Pax";

        String nights = hotelData.hNights + " Nights Available";

        holder.txtNama.setText(hotelData.hName);
        holder.txtPrice.setText(price);
        holder.txtKamar.setText(hotelData.hRoomType);
        holder.txtMalam.setText(nights);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dateForm = null;
        try {
            dateForm = inputFormat.parse(hotelData.hDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");
        String outputDateString = outputFormat.format(dateForm);

        holder.txtDate.setText(outputDateString);

        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";
        String image = urlImg + hotelData.hImage;

        Picasso.get().load(image).into(holder.imgHotel);

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, HotelDetailActivity.class);
            i.putExtra("object", hotelData);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return hotel.size();
    }
}
