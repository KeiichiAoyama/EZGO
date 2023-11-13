package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BusViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterViewBus adapterViewBus;

    ImageButton back,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_view);
        recyclerView = (RecyclerView)findViewById(R.id.viewBus);

        TicketData[] ticketData = new TicketData[]
                {
                        new TicketData("A1","5/11/2023","item1","item2","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("A2","5/11/2023","item1","item2","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("A3","5/11/2023","item1","item2","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("A4","5/11/2023","item2","item1","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("A5","5/11/2023","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("A6","5/11/2023","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("A6","5/11/2023","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45")
                };

        adapterViewBus = new AdapterViewBus(this, ticketData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterViewBus);

        Intent i = getIntent();
        String fromText = i.getStringExtra("from");
        String destText = i.getStringExtra("to");
        String dateText = i.getStringExtra("date");
        adapterViewBus.filter(fromText, destText, dateText);

        back = (ImageButton) findViewById(R.id.backPesawatView);
        search = (ImageButton) findViewById(R.id.searchPesawatView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });
    }
}