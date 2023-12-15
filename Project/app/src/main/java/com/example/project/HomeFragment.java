package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView,recyclerView2;
    AdapterHome adapterHome;

    ArrayList<MyItem> itemList1 = new ArrayList<>();
    ArrayList<MyItem> itemList2 = new ArrayList<>();

    LinearLayout btnTicket, btnHotel, btnTour, btnSearch;
    ShapeableImageView btnProfile;
    TextView uName;
    String image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home,container,false);
        SharedPreferences preferences = getActivity().getSharedPreferences("ezgo", Context.MODE_PRIVATE);
        String userJson = preferences.getString("user", null);
        User user = new Gson().fromJson(userJson, User.class);
        Log.d("Ezgo", "uName2: " + user.uName);

        uName = v.findViewById(R.id.profileName);
        btnSearch = v.findViewById(R.id.search);
        btnTicket = v.findViewById(R.id.btnTicket);
        btnHotel = v.findViewById(R.id.btnHotel);
        btnTour = v.findViewById(R.id.btnTour);
        btnProfile = v.findViewById(R.id.profileHome);

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        uName.setText(user.uName);
        if(user.uProfilePicture != null){
            image = urlImg + user.uProfilePicture;
        }else{
            image = urlImg + "defaultpfp.png";
        }
        Picasso.get().load(image).into(btnProfile);

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "location");
        params.put("method", "homePopular");

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
                                itemList1.clear();
                                int num = 1;
                                for (location loc : locs) {
                                    itemList1.add(new MyItem(loc));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }
                                recyclerView = v.findViewById(R.id.ViewHome1);
                                adapterHome = new AdapterHome(getActivity(), itemList1);
                                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                recyclerView.setAdapter(adapterHome);
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

        queue = Volley.newRequestQueue(requireActivity());
        params = new HashMap<>();
        params.put("controller", "order");
        params.put("method", "homePopular");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseThreeObjectList<ticket, hotel, tour> resp = gson.fromJson(response.toString(), new TypeToken<ResponseThreeObjectList<ticket, hotel, tour>>() {}.getType());
                        boolean success = resp.isSuccess();
                        List<ticket> ticks = resp.getData1();
                        List<hotel> hotls = resp.getData2();
                        List<tour> tours = resp.getData3();

                        if (success == true) {
                            try {
                                itemList2.clear();
                                int num = 1;
                                for (ticket tick : ticks) {
                                    itemList2.add(new MyItem(tick));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }
                                for (hotel hotl : hotls) {
                                    itemList2.add(new MyItem(hotl));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }
                                for (tour tour : tours) {
                                    itemList2.add(new MyItem(tour));
                                    Log.d("Ezgo", "currently: " + num);
                                    num++;
                                }
                                recyclerView2 = v.findViewById(R.id.ViewHome2);
                                adapterHome = new AdapterHome(getActivity(), itemList2);
                                recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                recyclerView2.setAdapter(adapterHome);
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

        btnTicket.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), TicketActivity.class);
            startActivity(i);
        });
        btnHotel.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), HotelActivity.class);
            startActivity(i);
        });
        btnTour.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), TourActivity.class);
            startActivity(i);
        });

        btnProfile.setOnClickListener(view -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmenContainer, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        btnSearch.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(),SearchActivity.class);
            startActivity(i);
        });

        return v;
    }
}