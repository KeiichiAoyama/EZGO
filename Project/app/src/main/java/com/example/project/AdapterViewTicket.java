package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterViewTicket extends RecyclerView.Adapter<AdapterViewTicket.MyViewHolder> {
    final Context context;
    final List<ticket> originalTicket;
    private List<ticket> tickets = new ArrayList<>();

    public AdapterViewTicket(Context context, List<ticket> ticket) {
        this.context = context;
        this.originalTicket = ticket;
        this.tickets = ticket;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPrice, txTravelTime, txtDate, txtArivTime, txtdeptTime, vendor;

        public MyViewHolder(View itemView) {
            super(itemView);
            txTravelTime = itemView.findViewById(R.id.duration);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtdeptTime = itemView.findViewById(R.id.FromTime);
            txtArivTime = itemView.findViewById(R.id.ToTime);
            txtDate = itemView.findViewById(R.id.seatType);
            vendor = itemView.findViewById(R.id.planeName);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_ticket, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ticket tix = tickets.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String price = "Rp "+decimalFormat.format(tix.tcPrice)+"/Pax";

        holder.txTravelTime.setText(tix.tcTravelTime);
        holder.txtdeptTime.setText(tix.tcDepartureTime);

        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        }

        LocalTime sum =  null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime time1 = LocalTime.parse(tix.tcDepartureTime, formatter);
            LocalTime time2 = LocalTime.parse(tix.tcTravelTime, formatter);

            sum = time1.plusHours(time2.getHour())
                    .plusMinutes(time2.getMinute())
                    .plusSeconds(time2.getSecond());
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dateForm = null;
        try {
            dateForm = inputFormat.parse(tix.tcDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");
        String outputDateString = outputFormat.format(dateForm);

        holder.txtDate.setText(outputDateString);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.txtArivTime.setText(sum.format(formatter));
        }
        holder.txtPrice.setText(price);
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, TicketDetailActivity.class);
            i.putExtra("object", tix);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return originalTicket.size();
    }
}
