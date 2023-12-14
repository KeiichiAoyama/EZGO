package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ExploreDetailActivity extends AppCompatActivity {
    private ImageView locImg;
    private TextView locName, locCity, locDesc, locLike;
    private ToggleButton btnLike;
    private MaterialButton btnWatch;
    private int likes;
    private LatLng position;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_detail);
        Intent intent = getIntent();
        location loc = (location) intent.getSerializableExtra("object");

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        ImageButton btnBack = (ImageButton) findViewById(R.id.backDetailExplore);
        ImageButton btnSearch = (ImageButton) findViewById(R.id.searchDetailExplore);
        locImg = (ImageView) findViewById(R.id.locImg);
        btnLike = (ToggleButton) findViewById(R.id.btnLike);
        locName = (TextView) findViewById(R.id.locName);
        locCity = (TextView) findViewById(R.id.locCity);
        locDesc = (TextView) findViewById(R.id.locDesc);
        locLike = (TextView) findViewById(R.id.locLike);
        btnWatch = (MaterialButton) findViewById(R.id.btnWatch);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.theMap);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "city");
        params.put("method", "getCity");
        params.put("cityID", loc.cityID);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseOneObject<String> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObject<String>>() {}.getType());
                        boolean success = resp.isSuccess();
                        String cName = resp.getData();

                        if (success == true) {
                            try {
                                locName.setText(loc.lName);
                                locCity.setText(cName);
                                locDesc.setText(loc.lDesc);
                                likes = loc.lLikes;
                                locLike.setText(String.valueOf(loc.lLikes));
                                String image = urlImg + loc.lImage;
                                Picasso.get().load(image).into(locImg);
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

        try{
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    position = new LatLng(loc.lLat, loc.lLong);
                    googleMap.addMarker(new MarkerOptions().position(position).title("My Location Now")).showInfoWindow();
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(position));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });
        btnLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    likes++;
                }else{
                    likes--;
                }

                RequestQueue queue = Volley.newRequestQueue(ExploreDetailActivity.this);
                Gson gson = new Gson();

                Map<String, Object> params = new HashMap<>();
                params.put("controller", "location");
                params.put("method", "like");
                params.put("locID", loc.locID);
                params.put("lLikes", likes);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                        new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Ezgo", "ResponseHome: " + response);
                                ResponseNoObject resp = gson.fromJson(response.toString(), new TypeToken<ResponseNoObject>() {}.getType());
                                boolean success = resp.isSuccess();

                                if (success == true) {
                                    try {
                                        locLike.setText(String.valueOf(likes));
                                        Log.d("Ezgo", "Likes: " + likes);
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
            }
        });
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreDetailActivity.this, WebViewActivity.class);
                intent.putExtra("link", loc.lLink);
                startActivity(intent);
            }
        });
    }
}