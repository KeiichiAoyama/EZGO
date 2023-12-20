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
import android.widget.FrameLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_detail);
        Intent intent = getIntent();
        location loc = (location) intent.getSerializableExtra("object");

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        FrameLayout btnBack = findViewById(R.id.backDetailExplore);
        locImg = findViewById(R.id.imgExploreDetail);
        btnLike = findViewById(R.id.btnLike);
        locName = findViewById(R.id.titleExplore);
        locCity = findViewById(R.id.address);
        locDesc = findViewById(R.id.descExplore);
        locLike = findViewById(R.id.locLike);
        btnWatch = findViewById(R.id.btnWatch);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.theMap);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "city");
        params.put("method", "getCity");
        params.put("cityID", loc.cityID);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                response -> {
                    Log.d("Ezgo", "ResponseHome: " + response);
                    ResponseOneObject<String> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObject<String>>() {
                    }.getType());
                    boolean success = resp.isSuccess();
                    String cName = resp.getData();

                    if (success) {
                        try {
                            locName.setText(loc.lName);
                            locCity.setText(cName);
                            locDesc.setText(loc.lDesc);
                            likes = loc.lLikes;
                            locLike.setText(String.valueOf(loc.lLikes));
                            String image = urlImg + loc.lImage;
                            Picasso.get().load(image).into(locImg);
                        } catch (Exception e) {
                            Log.e("Ezgo", "Error: " + e);
                        }
                    }
                },
                error -> {
                    Log.e("Ezgo", "Error: " + error.toString());
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    Log.e("Ezgo", "Response: " + responseBody);
                });
        queue.add(jsonObjectRequest);

        try {
            mapFragment.getMapAsync(googleMap -> {
                position = new LatLng(loc.lLat, loc.lLong);
                googleMap.addMarker(new MarkerOptions().position(position).title("My Location Now")).showInfoWindow();
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(position));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        btnBack.setOnClickListener(view -> onBackPressed());
        btnLike.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                likes++;
            } else {
                likes--;
            }

            RequestQueue queue1 = Volley.newRequestQueue(ExploreDetailActivity.this);
            Gson gson1 = new Gson();

            Map<String, Object> params1 = new HashMap<>();
            params1.put("controller", "location");
            params1.put("method", "like");
            params1.put("locID", loc.locID);
            params1.put("lLikes", likes);

            JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, urlReq,
                    new JSONObject(params1),
                    response -> {
                        Log.d("Ezgo", "ResponseHome: " + response);
                        ResponseNoObject resp = gson1.fromJson(response.toString(), new TypeToken<ResponseNoObject>() {
                        }.getType());
                        boolean success = resp.isSuccess();

                        if (success) {
                            try {
                                locLike.setText(String.valueOf(likes));
                                Log.d("Ezgo", "Likes: " + likes);
                            } catch (Exception e) {
                                Log.e("Ezgo", "Error: " + e);
                            }
                        }
                    },
                    error -> {
                        Log.e("Ezgo", "Error: " + error.toString());
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        Log.e("Ezgo", "Response: " + responseBody);
                    });
            queue1.add(jsonObjectRequest1);
        });
        btnWatch.setOnClickListener(view -> {
            Intent intent1 = new Intent(ExploreDetailActivity.this, WebViewActivity.class);
            intent1.putExtra("link", loc.lLink);
            startActivity(intent1);
        });
    }
}