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

public class TicketActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int REQUEST_CODE = 1;
    private int tanggal, bulan, tahun;
    private TextView calender;
    private Spinner passenger, type, from, to;
    private final String[] types = {"Plane", "Train", "Bus"};
    private final String[] passangers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private List<city> cities;
    private String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        //find id
        type = findViewById(R.id.type);
        passenger = findViewById(R.id.slotTourSpin);
        FrameLayout back = findViewById(R.id.backTicket1);
        FrameLayout search = findViewById(R.id.searchTicket1);
        from = findViewById(R.id.fromSpin);
        to = findViewById(R.id.toSpin);
        MaterialButton btnPesawat = findViewById(R.id.btnSearchTicket1);
        calender = findViewById(R.id.calender);

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

                            ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(TicketActivity.this, android.R.layout.simple_spinner_item, cityNames);
                            from.setAdapter(adapterFrom);
                            from.setOnItemSelectedListener(TicketActivity.this);

                            ArrayAdapter<String> adapterTo = new ArrayAdapter<>(TicketActivity.this, android.R.layout.simple_spinner_item, cityNames);
                            to.setAdapter(adapterTo);
                            to.setOnItemSelectedListener(TicketActivity.this);
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

        ArrayAdapter<String> adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        type.setAdapter(adapterType);
        type.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapterPassanger = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, passangers);
        passenger.setAdapter(adapterPassanger);
        passenger.setOnItemSelectedListener(this);

        calender.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            tahun = calendar.get(Calendar.YEAR);
            bulan = calendar.get(Calendar.MONTH);
            tanggal = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(TicketActivity.this, (datePicker, i, i1, i2) -> {
                tahun = i;
                bulan = i1 + 1;
                tanggal = i2;

                calender.setText(String.format("%04d-%02d-%02d", tahun, bulan, tanggal));
            }, tahun, bulan, tanggal);
            dialog.show();

        });

        //btn
        btnPesawat.setOnClickListener(view -> {
            int toCity = 0;
            int fromCity = 0;

            String targetFrom = from.getSelectedItem().toString();
            String targetTo = to.getSelectedItem().toString();

            for (city obj : cities) {
                if (targetFrom.equals(obj.cName)) {
                    fromCity = obj.cityID;
                } else if (targetTo.equals(obj.cName)) {
                    toCity = obj.cityID;
                }
            }

            String date = calender.getText().toString();
            int pas = Integer.parseInt(passenger.getSelectedItem().toString());
            String ticktype = type.getSelectedItem().toString();

            Intent i = new Intent(getApplicationContext(), TicketViewActivity.class);
            i.putExtra("from", fromCity);
            i.putExtra("to", toCity);
            i.putExtra("fromStr", from.getSelectedItem().toString());
            i.putExtra("toStr", to.getSelectedItem().toString());
            i.putExtra("date", date);
            i.putExtra("pas", pas);
            i.putExtra("type", ticktype);
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
}