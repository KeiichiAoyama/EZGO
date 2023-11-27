package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterViewTicket extends RecyclerView.Adapter<AdapterViewTicket.MyViewHolder> {
    final Context context;
    final TicketData[] originalTicket;
    private TicketData[] ticket;

    public AdapterViewTicket(Context context, TicketData[] ticket) {
        this.context = context;
        this.originalTicket = ticket.clone();
        this.ticket = ticket;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtFrom, txtDest, txtPrice, txTravelTime, txtDate, txtArivTime, txtdeptTime, txtType;

        public MyViewHolder(View itemView) {
            super(itemView);
            txTravelTime = itemView.findViewById(R.id.duration);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtdeptTime = itemView.findViewById(R.id.FromTime);
            txtArivTime = itemView.findViewById(R.id.ToTime);
            txtType = itemView.findViewById(R.id.seatType);
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
        TicketData ticketData = ticket[position];
        holder.txtFrom.setText(ticketData.getFrom());
        holder.txtDest.setText(ticketData.getDest());
        holder.txTravelTime.setText(ticketData.getTravelTime());
        holder.txtdeptTime.setText(ticketData.getDeptTime());
        holder.txtArivTime.setText(ticketData.getArivTime());
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, TicketDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ID", ticketData.getId());
            bundle.putString("From", ticketData.getFrom());
            bundle.putString("Dest", ticketData.getDest());
            bundle.putString("TravelTime", ticketData.getTravelTime());
            bundle.putString("DeptTime", ticketData.getDeptTime());
            bundle.putString("ArivTime", ticketData.getArivTime());
            i.putExtras(bundle);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return ticket.length;
    }

    public void filter(String fromText, String destText, String dateText, String type) {
        ArrayList<TicketData> filteredList = new ArrayList<>();
        for (TicketData ticketData : originalTicket) {
            if (ticketData.getFrom().toLowerCase().contains(fromText.toLowerCase()) &&
                    ticketData.getDest().toLowerCase().contains(destText.toLowerCase()) &&
                    ticketData.getTicketType().toLowerCase().contains(type.toLowerCase()) &&
                    ticketData.getDate().equals(dateText)) {
                filteredList.add(ticketData);
            }
        }
        ticket = filteredList.toArray(new TicketData[0]);
        notifyDataSetChanged();
    }
}
