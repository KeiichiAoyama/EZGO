package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

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

public class OrderFragment extends Fragment {
    RecyclerView ticket, hotel, tour;
    AdapterViewTicket adapterViewTicket;
    SearchView btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_order,container,false);
        SharedPreferences preferences = getActivity().getSharedPreferences("ezgo", Context.MODE_PRIVATE);
        String userJson = preferences.getString("user", null);
        User user = new Gson().fromJson(userJson, User.class);
        Log.d("Ezgo", "uName3: " + user.uName);

        btnSearch = v.findViewById(R.id.SearchBar);

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "transaction");
        params.put("method", "transAll");
        params.put("userID", user.userID);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseFourObjectList<String, ticket, hotel, tour> resp = gson.fromJson(response.toString(), new TypeToken<ResponseFourObjectList<String, ticket, hotel, tour>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<String> ids = resp.getData1();
                        List<ticket> tixs = resp.getData2();
                        List<hotel> htls = resp.getData3();
                        List<tour> trps = resp.getData4();

                        if (success == true) {
                            try {
                                List<ticket> tickets = new ArrayList<>();

                                for (String id : ids) {
                                    for (ticket tix : tixs) {
                                        if(tix.productID.equals(id)){
                                            tickets.add(tix);
                                        }
                                    }
                                }

                                ticket = v.findViewById(R.id.hist_orderTicket);
                                adapterViewTicket = new AdapterViewTicket(getActivity(), tickets);
                                ticket.setLayoutManager(new LinearLayoutManager(getActivity()));
                                ticket.setAdapter(adapterViewTicket);
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

        btnSearch.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(),SearchActivity.class);
            startActivity(i);
        });

        return v;
    }
}