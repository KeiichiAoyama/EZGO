package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

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

public class TourViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterViewTour adapterViewTour;
    FrameLayout back, search;
    String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_view);
        recyclerView = findViewById(R.id.viewTour);
        back = findViewById(R.id.backTourView);
        search = findViewById(R.id.searchTourView);

        Intent intent = getIntent();
        int cityText = intent.getIntExtra("loc", 0);
        String dateText = intent.getStringExtra("date");
        int personText = intent.getIntExtra("per", 0);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "order");
        params.put("method", "searchTour");
        params.put("cityID", cityText);
        params.put("tpDate", dateText);
        params.put("tpSlot", personText);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseOneObjectList<tour> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObjectList<tour>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<tour> tourData = resp.getData();

                        if (success == true) {
                            try {
                                adapterViewTour = new AdapterViewTour(TourViewActivity.this, tourData);
                                recyclerView.setLayoutManager(new LinearLayoutManager(TourViewActivity.this));
                                recyclerView.setAdapter(adapterViewTour);
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