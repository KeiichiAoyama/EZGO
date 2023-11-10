package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class HotelViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterViewHotel adapterViewHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        recyclerView = (RecyclerView) findViewById(R.id.viewHotel);

        HotelData[] hotelData = new HotelData[]
                {
                        new HotelData("Episode Gading Serpong",1000000 ),
                        new HotelData("Episode Gading Serpong",1000000 ),
                        new HotelData("Episode Gading Serpong",1000000 ),
                        new HotelData("Episode Gading Serpong",1000000 )
                };

        adapterViewHotel = new AdapterViewHotel(this, hotelData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterViewHotel);
    }
}
