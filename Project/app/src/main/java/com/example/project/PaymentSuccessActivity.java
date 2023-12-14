package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class PaymentSuccessActivity extends AppCompatActivity {
    FrameLayout btnClose;
    MaterialButton btnBackHome;
    TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        Intent i = getIntent();
        int price = i.getIntExtra("price", 0);

        btnClose = findViewById(R.id.backCloseSuccess);
        btnBackHome = findViewById(R.id.btnBackToHome);
        amount = findViewById(R.id.txtAmountTf);

        amount.setText(String.valueOf(price));

        btnClose.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        btnBackHome.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
}