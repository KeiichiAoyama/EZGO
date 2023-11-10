package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PesawatViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterViewPesawat adapterViewPesawat;

    ImageButton back,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesawat_view);
        recyclerView = (RecyclerView)findViewById(R.id.viewPesawat);

        TicketData[] ticketData = new TicketData[]
                {
                  new TicketData("5/11/2023","item1","item2","10.30","1j 15mnt",700000,"Economy","11.45"),
                  new TicketData("5/11/2023","item1","item2","10.30","1j 15mnt",700000,"Economy","11.45"),
                  new TicketData("5/11/2023","item1","item2","10.30","1j 15mnt",700000,"Economy","11.45"),
                  new TicketData("5/11/2023","item2","item1","10.30","1j 15mnt",700000,"Economy","11.45"),
                  new TicketData("5/11/2023","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                  new TicketData("5/11/2023","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45"),
                  new TicketData("5/11/2023","SOC","CGK","10.30","1j 15mnt",700000,"Economy","11.45")
                };

        adapterViewPesawat = new AdapterViewPesawat(this, ticketData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterViewPesawat);

        Intent i = getIntent();
        String fromText = i.getStringExtra("from");
        String destText = i.getStringExtra("to");
        String dateText = i.getStringExtra("date");
        adapterViewPesawat.filter(fromText, destText, dateText);

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