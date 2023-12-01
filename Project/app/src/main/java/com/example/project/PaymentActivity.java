package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    TextView payment1, payment2, payment3,payment4;
    String no1,no2,no3,no4;
    FrameLayout btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        payment1 = findViewById(R.id.btnPayment2);
        payment2 = findViewById(R.id.btnPayment3);
        payment3 = findViewById(R.id.btnPayment4);
        payment4 = findViewById(R.id.btnPayment5);

        btnBack.setOnClickListener(view -> onBackPressed());
        payment1.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            i.putExtra("norek",no1);
            startActivity(i);
        });
        payment2.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            i.putExtra("norek",no2);
            startActivity(i);
        });
        payment3.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            i.putExtra("norek",no3);
            startActivity(i);
        });
        payment4.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            i.putExtra("norek",no4);
            startActivity(i);
        });
    }
}