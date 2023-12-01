package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class TicketViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterViewTicket adapterViewTicket;
    TextView fromtxt,totxt;
    ImageButton back, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_view);
        recyclerView = findViewById(R.id.viewTicket);
        fromtxt = findViewById(R.id.from);
        totxt = findViewById(R.id.to);
        back = findViewById(R.id.backTicketView);
        search = findViewById(R.id.searchTicketView);

        TicketData[] ticketData = new TicketData[]
                {
                        new TicketData("B1", "5/11/2023", "item1", "item2", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Plane"),
                        new TicketData("B2", "5/11/2023", "item1", "item2", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Plane"),
                        new TicketData("B3", "5/11/2023", "item1", "item2", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Train"),
                        new TicketData("B4", "5/11/2023", "item2", "item1", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Train"),
                        new TicketData("B5", "5/11/2023", "SOC", "CGK", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Train"),
                        new TicketData("B6", "5/11/2023", "SOC", "CGK", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Train"),
                        new TicketData("B7", "5/11/2023", "SOC", "CGK", "10.30", "1j 15mnt", 700000, "Economy", "11.45", "Train")
                };

        adapterViewTicket = new AdapterViewTicket(this, ticketData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterViewTicket);

        Intent i = getIntent();
        String fromText = i.getStringExtra("from");
        String destText = i.getStringExtra("to");
        String dateText = i.getStringExtra("date");
        String typeText = i.getStringExtra("ticktype");
        adapterViewTicket.filter(fromText, destText, dateText, typeText);

        fromtxt.setText(fromText);
        totxt.setText(destText);
        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }
}