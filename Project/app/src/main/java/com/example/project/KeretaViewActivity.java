package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class KeretaViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterViewKereta adapterViewKereta;

    ImageButton back,search;
    LinearLayout content2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereta_view);
        recyclerView = (RecyclerView)findViewById(R.id.viewKereta);

        TicketData[] ticketData = new TicketData[]
                {
                        new TicketData("2020","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("2020","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("2020","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("2020","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                        new TicketData("2020","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45")
                };

        adapterViewKereta = new AdapterViewKereta(this, ticketData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterViewKereta);
        back = (ImageButton) findViewById(R.id.backKeretaView);
        search = (ImageButton) findViewById(R.id.searchKeretaView);
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