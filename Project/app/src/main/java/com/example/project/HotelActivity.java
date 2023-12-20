package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int tanggal, bulan, tahun;
    private TextView calender;
    private Spinner person, room, location;
    private List<city> cities;
    private String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

    private final String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final String[] roomType = {"Standard Room", "Cabin Room", "Deluxe Room", "Vintage Suite", "Ocean View Suite"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        room = findViewById(R.id.room);
        person = findViewById(R.id.person);
        FrameLayout back = findViewById(R.id.backHotel);
        FrameLayout search = findViewById(R.id.searchHotel);
        location = findViewById(R.id.htlCitySpin);
        calender = findViewById(R.id.calenderHotel);
        MaterialButton btnHotel = findViewById(R.id.btnSearchHotel);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "city");
        params.put("method", "list");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                response -> {
                    Log.d("Ezgo", "ResponseHome: " + response);
                    ResponseOneObjectList<city> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObjectList<city>>() {
                    }.getType());
                    boolean success = resp.isSuccess();
                    cities = resp.getData();

                    if (success == true) {
                        try {
                            List<String> cityNames = new ArrayList<>();

                            for (city city : cities) {
                                cityNames.add(city.cName);
                                Log.d("Ezgo", "ResponseHome: " + cityNames);
                            }

                            ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(HotelActivity.this, android.R.layout.simple_spinner_item, cityNames);
                            location.setAdapter(adapterFrom);
                            location.setOnItemSelectedListener(HotelActivity.this);
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

        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomType);
        room.setAdapter(adapterRoom);
        room.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapterPerson = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, number);
        person.setAdapter(adapterPerson);
        person.setOnItemSelectedListener(this);

        calender.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            tahun = calendar.get(Calendar.YEAR);
            bulan = calendar.get(Calendar.MONTH);
            tanggal = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(HotelActivity.this, (datePicker, i, i1, i2) -> {
                tahun = i;
                bulan = i1 + 1;
                tanggal = i2;

                calender.setText(String.format("%04d-%02d-%02d", tahun, bulan, tanggal));
            }, tahun, bulan, tanggal);
            dialog.show();

        });

        btnHotel.setOnClickListener(view -> {
            int City = 0;

            String targetCity = location.getSelectedItem().toString();

            for (city obj : cities) {
                if (targetCity.equals(obj.cName)) {
                    City = obj.cityID;
                }
            }

            String date = calender.getText().toString();
            int per = Integer.parseInt(person.getSelectedItem().toString());
            String rooms = room.getSelectedItem().toString();

            Intent i = new Intent(getApplicationContext(), HotelViewActivity.class);
            i.putExtra("loc", City);
            i.putExtra("date", date);
            i.putExtra("room", rooms);
            i.putExtra("per", per);
            startActivity(i);
        });

        back.setOnClickListener(view -> onBackPressed());

        search.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
