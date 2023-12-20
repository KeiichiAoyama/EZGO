package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;

public class TourDetailActivity extends AppCompatActivity{
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
        setContentView(R.layout.activity_tour_detail);
        Intent intent = getIntent();
        tour tour = (tour) intent.getSerializableExtra("object");

        back = findViewById(R.id.backDetailTour);
        img = findViewById(R.id.imgTour);
        name = findViewById(R.id.htlName);
        price = findViewById(R.id.priceTour);
        nights = findViewById(R.id.slotTour);
        totprice = findViewById(R.id.totPriceTour);
        addfee = findViewById(R.id.addFeeTour);
        add = findViewById(R.id.addMore);
        order = findViewById(R.id.orderTour);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        totPrice = tour.tpPrice * quantity + fee;

        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";
        String image = urlImg + tour.tpImage;

        Picasso.get().load(image).into(img);

        name.setText(tour.tpName);
        price.setText(decimalFormat.format(tour.tpPrice));
        nights.setText(Integer.toString(quantity));
        addfee.setText(decimalFormat.format(fee));
        totprice.setText(decimalFormat.format(totPrice));

        back.setOnClickListener(view -> onBackPressed());

        add.setOnClickListener(view -> {
            if((quantity + 1) <= tour.tpSlot){
                quantity++;
                totPrice = tour.tpPrice * quantity + fee;
                nights.setText(Integer.toString(quantity));
                totprice.setText(decimalFormat.format(totPrice));
            }else{
                Toast.makeText(getApplicationContext(), "Max Amount Reached", Toast.LENGTH_LONG).show();
            }
        });

        order.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
            i.putExtra("object", tour);
            i.putExtra("price", totPrice);
            i.putExtra("amount", quantity);
            startActivity(i);
        });
    }
}