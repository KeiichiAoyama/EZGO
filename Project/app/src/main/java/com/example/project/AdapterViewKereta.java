package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterViewKereta extends RecyclerView.Adapter<AdapterViewKereta.MyViewHolder> {
    private Context context;

    private TicketData[] ticket;

    public AdapterViewKereta(Context context, TicketData[] ticket) {
        this.context = context;
        this.ticket = ticket;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtFrom, txtDest, txtPrice, txTravelTime, txtDate, txtArivTime, txtdeptTime, txtType;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtFrom = itemView.findViewById(R.id.txtFrom);
            txtDest = itemView.findViewById(R.id.txtTo);
            txTravelTime = itemView.findViewById(R.id.duration);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtdeptTime = itemView.findViewById(R.id.FromTime);
            txtArivTime = itemView.findViewById(R.id.ToTime);
            txtType = itemView.findViewById(R.id.seatType);
        }
    }

    @Override
    public AdapterViewKereta.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pesawat, parent, false);
        return new AdapterViewKereta.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewKereta.MyViewHolder holder, int position) {
        TicketData ticketData = ticket[position];
        holder.txtFrom.setText(ticketData.getFrom());
        holder.txtDest.setText(ticketData.getDest());
        holder.txTravelTime.setText(ticketData.getTravelTime());
        holder.txtdeptTime.setText(ticketData.getDeptTime());
        holder.txtArivTime.setText(ticketData.getArivTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, KeretaDetailActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticket.length;
    }
}
