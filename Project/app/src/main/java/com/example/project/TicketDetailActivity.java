package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketDetailActivity extends AppCompatActivity {

    FrameLayout back, search;
    ImageView iconPesawat, bg;

    int quantity = 1;
    int fee = 2000;
    int totPrice = 0;

    MaterialButton order, add;
    TextView from,to,date,arivTime,departTime,travelTime,planeName,rating,ticketprice,qty,addfee,totalprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        Intent intent = getIntent();
        ticket tix = (ticket) intent.getSerializableExtra("object");

        back = findViewById(R.id.backDetailTicket);
        iconPesawat = findViewById(R.id.iconDetailTicket);
        from = findViewById(R.id.txtFromDetail);
        to = findViewById(R.id.txtToDetail);
        date = findViewById(R.id.txtDateDetail);
        arivTime = findViewById(R.id.txtArrivalTimeDetail);
        departTime = findViewById(R.id.txtDepartureDetail);
        travelTime = findViewById(R.id.txtTravelTimeDetail);
        planeName = findViewById(R.id.txtNamaDetail);
        rating = findViewById(R.id.txtRatingTicket);
        ticketprice = findViewById(R.id.ticketPrice);
        qty = findViewById(R.id.ticketQty);
        addfee = findViewById(R.id.addFee);
        totalprice = findViewById(R.id.totalPrice);
        order = findViewById(R.id.orderList);
        add = findViewById(R.id.addMore);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        totPrice = tix.tcPrice * quantity + fee;

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("controller", "city");
        params.put("method", "getCityTwo");
        params.put("cityIDfrom", tix.tcFrom);
        params.put("cityIDdest", tix.tcDestination);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                new JSONObject(params),
                response -> {
                    Log.d("Ezgo", "ResponseHome: " + response);
                    ResponseOneObjectList<String> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObjectList<String>>() {}.getType());
                    boolean success = resp.isSuccess();
                    List<String> cNames = resp.getData();

                    if (success) {
                        try {
                            from.setText(cNames.get(0));
                            to.setText(cNames.get(1));
                            travelTime.setText(tix.tcTravelTime);
                            departTime.setText(tix.tcDepartureTime);

                            DateTimeFormatter formatter = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            }

                            LocalTime sum =  null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                LocalTime time1 = LocalTime.parse(tix.tcDepartureTime, formatter);
                                LocalTime time2 = LocalTime.parse(tix.tcTravelTime, formatter);

                                sum = time1.plusHours(time2.getHour())
                                        .plusMinutes(time2.getMinute())
                                        .plusSeconds(time2.getSecond());
                            }


                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateForm = inputFormat.parse(tix.tcDate);
                            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");
                            String outputDateString = outputFormat.format(dateForm);

                            date.setText(outputDateString);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                arivTime.setText(sum.format(formatter));
                            }
                            rating.setText(Double.toString(tix.tcRating));
                            ticketprice.setText(decimalFormat.format(tix.tcPrice));
                            qty.setText(Integer.toString(quantity));
                            addfee.setText(decimalFormat.format(fee));
                            totalprice.setText(decimalFormat.format(totPrice));
                        }catch (Exception e){
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

        back.setOnClickListener(view -> onBackPressed());

        add.setOnClickListener(view -> {
            if((quantity + 1) <= tix.tcSeat){
                quantity++;
                totPrice = tix.tcPrice * quantity + fee;
                qty.setText(Integer.toString(quantity));
                totalprice.setText(decimalFormat.format(totPrice));
            }else{
                Toast.makeText(getApplicationContext(), "Max Amount Reached", Toast.LENGTH_LONG).show();
            }
        });

        order.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
            i.putExtra("object", tix);
            i.putExtra("price", totPrice);
            i.putExtra("amount", quantity);
            startActivity(i);
        });

    }
}