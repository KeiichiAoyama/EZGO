package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterViewHotel adapterViewHotel;
    FrameLayout back, search;
    String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        recyclerView = findViewById(R.id.viewHotel);
        back = findViewById(R.id.backHotelView);
        search = findViewById(R.id.searchHotelView);

        Intent intent = getIntent();
        int cityText = intent.getIntExtra("loc", 0);
        String dateText = intent.getStringExtra("date");
        int personText = intent.getIntExtra("per", 0);
        String roomText = intent.getStringExtra("room");

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "order");
        params.put("method", "searchHotel");
        params.put("cityID", cityText);
        params.put("hRoomType", roomText);
        params.put("hDate", dateText);
        params.put("hNights", personText);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseOneObjectList<hotel> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObjectList<hotel>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<hotel> hotelData = resp.getData();

                        if (success == true) {
                            try {
                                adapterViewHotel = new AdapterViewHotel(HotelViewActivity.this, hotelData);
                                recyclerView.setLayoutManager(new LinearLayoutManager(HotelViewActivity.this));
                                recyclerView.setAdapter(adapterViewHotel);
                            }catch (Exception e){
                                Log.e("Ezgo", "Error: " + e);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Ezgo", "Error: " + error.toString());
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        Log.e("Ezgo", "Response: " + responseBody);
                    }
                });
        queue.add(jsonObjectRequest);

        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }
}
