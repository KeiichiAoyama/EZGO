package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PaymentDetailActivity extends AppCompatActivity {
    private final int duration = 360;
    TextView txtRek, txtAmount, txtBank;
    ImageView imgBank;
    LinearLayout pb;
    int img, type, amount, price;
    String noRek, bank;
    ticket tix;
    hotel htl;
    tour trp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        txtRek = findViewById(R.id.txtNoRek);
        txtBank = findViewById(R.id.txtBank);
        txtAmount = findViewById(R.id.txtAmount);
        imgBank = findViewById(R.id.imgBank);
        pb = findViewById(R.id.progressBarPayment);

        pb.setVisibility(LinearLayout.INVISIBLE);


        MaterialButton copyButtonRek = findViewById(R.id.btnCopyRek);
        MaterialButton copyButtonAmount = findViewById(R.id.btnCopyAmount);
        MaterialButton btnDone = findViewById(R.id.btnDone);


        Bundle b = getIntent().getExtras();
        noRek = b.getString("norek");
        bank = b.getString("bank");
        img = b.getInt("draw");
        price = b.getInt("price");
        amount = b.getInt("amount");

        if(b.getSerializable("object") instanceof ticket){
            tix = (ticket) b.getSerializable("object");
            type = 1;
        }else if(b.getSerializable("object") instanceof hotel){
            htl = (hotel) b.getSerializable("object");
            type = 2;
        }else if(b.getSerializable("object") instanceof tour){
            trp = (tour) b.getSerializable("object");
            type = 3;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String strprice = decimalFormat.format(price);

        txtBank.setText(bank);
        txtRek.setText(noRek);
        txtAmount.setText(strprice);
        imgBank.setImageResource(img);

        new CountDownTimer(duration * 1000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long totalMinutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                long hours = totalMinutes / 60;
                long minutes = totalMinutes % 60;
                long seconds = totalSeconds % 60;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                final String[] hourMinSec = time.split(":");
                TextView hour = findViewById(R.id.hour);
                TextView min = findViewById(R.id.min);
                TextView sec = findViewById(R.id.sec);
                hour.setText(hourMinSec[0]);
                min.setText(hourMinSec[1]);
                sec.setText(hourMinSec[2]);
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }.start();

        btnDone.setOnClickListener(view -> {
            pb.setVisibility(LinearLayout.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pb.setVisibility(LinearLayout.INVISIBLE);

                    String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
                    String method = "";
                    String productID = "";
                    String id = "";
                    int updAmt = 0;

                    switch (type){
                        case 1:
                            method = "orderTicket";
                            productID = tix.productID;
                            id = tix.ticketID;
                            updAmt = tix.tcSeat - amount;
                            break;
                        case 2:
                            method = "orderHotel";
                            productID = htl.productID;
                            id = htl.hotelID;
                            updAmt = htl.hNights - amount;
                            break;
                        case 3:
                            method = "orderTour";
                            productID = trp.productID;
                            id = trp.tourID;
                            updAmt = trp.tpSlot - amount;
                            break;
                    }

                    SharedPreferences preferences = getSharedPreferences("ezgo", Context.MODE_PRIVATE);
                    String userJson = preferences.getString("user", null);
                    User user = new Gson().fromJson(userJson, User.class);

                    RequestQueue queue = Volley.newRequestQueue(PaymentDetailActivity.this);
                    Gson gson = new Gson();

                    Map<String, Object> params = new HashMap<>();
                    params.put("controller", "order");
                    params.put("method", method);
                    params.put("productID", productID);
                    params.put("userID", user.userID);
                    params.put("payMethod", bank);
                    params.put("tdAmount", amount);
                    params.put("tdTotalPrice", price);
                    params.put("id", id);
                    params.put("upAmount", updAmt);

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
                                            Intent i = new Intent(getApplicationContext(), PaymentSuccessActivity.class);
                                            i.putExtra("price", price);
                                            i.putExtra("norek",noRek);
                                            startActivity(i);
                                            finish();
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
            }, 3000);
        });

        copyButtonRek.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("No Rek", noRek);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Teks disalin ke Clipboard", Toast.LENGTH_SHORT).show();
        });

        copyButtonAmount.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Price", String.valueOf(price));
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Teks disalin ke Clipboard", Toast.LENGTH_SHORT).show();
        });
    }
}