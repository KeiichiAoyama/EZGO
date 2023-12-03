package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class KotaActivity extends AppCompatActivity {
    String choose;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kota);

        ListView listView = findViewById(R.id.view);
        SearchView searchView = findViewById(R.id.SearchBar);

        // Data yang di tampilkan
        data = new ArrayList<>();
        for (int b = 0; b < 14; b++) {
            data.add("Item" + b);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        Intent i = getIntent();
        choose = i.getStringExtra("Type");

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = data.get(position);

            if (choose.equalsIgnoreCase("Ticket")) {
                Intent resultIntent = new Intent();
                String mode = getIntent().getStringExtra("mode");
                resultIntent.putExtra("kota", selectedItem);
                resultIntent.putExtra("mode", mode);

                setResult(RESULT_OK, resultIntent);
            } else if (choose.equalsIgnoreCase("Tour")) {
                Intent resultIntent = new Intent(getApplicationContext(), TourActivity.class);
                resultIntent.putExtra("kota", selectedItem);
                startActivity(resultIntent);
            } else if (choose.equalsIgnoreCase("Hotel")) {
                Intent resultIntent = new Intent(getApplicationContext(), HotelActivity.class);
                resultIntent.putExtra("kota", selectedItem);
                startActivity(resultIntent);
            }
            finish();
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}