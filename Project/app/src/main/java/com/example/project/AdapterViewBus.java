package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterViewBus extends RecyclerView.Adapter<AdapterViewBus.MyViewHolder> {
    private Context context;
    private TicketData[] originalTicket;
    private TicketData[] ticket;

    public AdapterViewBus(Context context, TicketData[] ticket) {
        this.context = context;
        this.originalTicket = ticket.clone(); // Salin data asli
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
    public AdapterViewBus.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_bus, parent, false);
        return new AdapterViewBus.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewBus.MyViewHolder holder, int position) {
        TicketData ticketData = ticket[position];
        holder.txtFrom.setText(ticketData.getFrom());
        holder.txtDest.setText(ticketData.getDest());
        holder.txTravelTime.setText(ticketData.getTravelTime());
        holder.txtdeptTime.setText(ticketData.getDeptTime());
        holder.txtArivTime.setText(ticketData.getArivTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BusDetailActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticket.length;
    }

    public void filter(String fromText, String destText, String dateText) {
        ArrayList<TicketData> filteredList = new ArrayList<>();
        for (TicketData ticketData : originalTicket) {
            // Lakukan filter berdasarkan asal (From), tujuan (Dest), dan tanggal (Date)
            if (ticketData.getFrom().toLowerCase().contains(fromText.toLowerCase()) &&
                    ticketData.getDest().toLowerCase().contains(destText.toLowerCase()) &&
                    ticketData.getDate().equals(dateText)) {
                filteredList.add(ticketData);
            }
        }
        ticket = filteredList.toArray(new TicketData[0]);
        notifyDataSetChanged();
    }
}
