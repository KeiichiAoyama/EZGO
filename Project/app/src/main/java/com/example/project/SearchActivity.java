package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterSearch adapter;
    private ArrayList<SearchItem> originalData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TextView btnCancel = findViewById(R.id.btnCancel);
        SearchView search = findViewById(R.id.SearchBar);
        recyclerView = findViewById(R.id.orderList);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "search");
        params.put("method", "explore");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseFourObjectList<location, ticket, hotel, tour> resp = gson.fromJson(response.toString(), new TypeToken<ResponseFourObjectList<location, ticket, hotel, tour>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<location> locs = resp.getData1();
                        List<ticket> tixs = resp.getData2();
                        List<hotel> htls = resp.getData3();
                        List<tour> trps = resp.getData4();

                        if (success == true) {
                            try {
                                originalData.clear();
                                int num = 1;
                                for (location loc : locs) {
                                    originalData.add(new SearchItem(loc));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }

                                for (ticket tix : tixs) {
                                    originalData.add(new SearchItem(tix));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }

                                for (hotel htl : htls) {
                                    originalData.add(new SearchItem(htl));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }

                                for (tour trp : trps) {
                                    originalData.add(new SearchItem(trp));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }

                                adapter = new AdapterSearch(originalData, SearchActivity.this);
                                recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
                                recyclerView.setAdapter(adapter);
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

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });
    }

    private void filterData(String query) {
        // Filter data based on the user's input and update the adapter
        ArrayList<SearchItem> filteredData = new ArrayList<>();
        for (SearchItem item : originalData) {
            if (item.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredData.add(item);
            }
        }
        adapter.setData(filteredData);
    }
}