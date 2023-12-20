package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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

public class ExploreFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterViewExplore adapterViewExplore;
    SearchView btnSearch;
    ArrayList<ExploreData> exploreData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_explore, container, false);
        v.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white));

        recyclerView = v.findViewById(R.id.viewExplore);
        btnSearch = v.findViewById(R.id.SearchBar1);

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "location");
        params.put("method", "explore");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseOneObjectList<location> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObjectList<location>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<location> locs = resp.getData();

                        if (success == true) {
                            try {
                                exploreData.clear();
                                int num = 1;
                                for (location loc : locs) {
                                    exploreData.add(new ExploreData(loc));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }

                                adapterViewExplore = new AdapterViewExplore(getActivity(), exploreData);
                                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                recyclerView.setAdapter(adapterViewExplore);
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