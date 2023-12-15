package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class TicketViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterViewTicket adapterViewTicket;
    TextView fromtxt,totxt;
    FrameLayout back, search;
    String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_view);
        recyclerView = findViewById(R.id.viewTicket);
        fromtxt = findViewById(R.id.from);
        totxt = findViewById(R.id.to);
        back = findViewById(R.id.backTicketView);
        search = findViewById(R.id.searchTicketView);

        Intent intent = getIntent();
        int fromText = intent.getIntExtra("from", 0);
        int destText = intent.getIntExtra("to", 0);
        String dateText = intent.getStringExtra("date");
        int pasText = intent.getIntExtra("pas", 0);
        String typeText = intent.getStringExtra("type");
        String fromStr = intent.getStringExtra("fromStr");
        String toStr = intent.getStringExtra("toStr");

        Log.d("Ezgo", "Params: " + fromText + destText + dateText + pasText + typeText);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "order");
        params.put("method", "searchTicket");
        params.put("tcFrom", fromText);
        params.put("tcDestination", destText);
        params.put("tcDate", dateText);
        params.put("tcSeat", pasText);
        params.put("tcType", typeText);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseOneObjectList<ticket> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObjectList<ticket>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<ticket> tickets = resp.getData();

                        if (success == true) {
                            try {
                                adapterViewTicket = new AdapterViewTicket(TicketViewActivity.this, tickets);
                                recyclerView.setLayoutManager(new LinearLayoutManager(TicketViewActivity.this));
                                recyclerView.setAdapter(adapterViewTicket);
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

        fromtxt.setText(fromStr);
        totxt.setText(toStr);

        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }
}