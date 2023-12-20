package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class HotelDetailActivity extends AppCompatActivity {
    FrameLayout back;
    ImageView img;
    TextView name, price, nights, totprice, addfee;
    MaterialButton add, order;

    int quantity = 1;
    int fee = 2000;
    int totPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        Intent intent = getIntent();
        hotel htl = (hotel) intent.getSerializableExtra("object");

        back = findViewById(R.id.backDetailHotel);
        img = findViewById(R.id.imgHotel);
        name = findViewById(R.id.htlName);
        price = findViewById(R.id.htlPrice);
        nights = findViewById(R.id.htlNights);
        totprice = findViewById(R.id.htlTotPrice);
        addfee = findViewById(R.id.htlAddFee);
        add = findViewById(R.id.addMore);
        order = findViewById(R.id.orderHotel);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        totPrice = htl.hPrice * quantity + fee;

        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";
        String image = urlImg + htl.hImage;

        Picasso.get().load(image).into(img);

        name.setText(htl.hName);
        price.setText(decimalFormat.format(htl.hPrice));
        nights.setText(Integer.toString(quantity));
        addfee.setText(decimalFormat.format(fee));
        totprice.setText(decimalFormat.format(totPrice));

        back.setOnClickListener(view -> onBackPressed());

        add.setOnClickListener(view -> {
            if((quantity + 1) <= htl.hNights){
                quantity++;
                totPrice = htl.hPrice * quantity + fee;
                nights.setText(Integer.toString(quantity));
                totprice.setText(decimalFormat.format(totPrice));
            }else{
                Toast.makeText(getApplicationContext(), "Max Amount Reached", Toast.LENGTH_LONG).show();
            }
        });

        order.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
            i.putExtra("object", htl);
            i.putExtra("price", totPrice);
            i.putExtra("amount", quantity);
            startActivity(i);
        });
    }
}