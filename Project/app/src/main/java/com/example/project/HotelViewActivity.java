package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class HotelViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterViewHotel adapterViewHotel;
    ImageButton back, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        recyclerView = findViewById(R.id.viewHotel);
        back = findViewById(R.id.backHotelView);
        search = findViewById(R.id.searchHotelView);

        HotelData[] hotelData = new HotelData[]
                {
                        new HotelData("A1","Episode Gading Serpong","item1","P",1000000 ,"5/11/2023")
                };

        adapterViewHotel = new AdapterViewHotel(this, hotelData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterViewHotel);

        Intent i = getIntent();
        String locText = i.getStringExtra("loc");
        String dateText = i.getStringExtra("date");
        adapterViewHotel.filterHotel(locText, dateText);
        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }
}
