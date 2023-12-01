package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.button.MaterialButton;

public class PaymentSuccessActivity extends AppCompatActivity {
    FrameLayout btnClose;
    MaterialButton btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        btnClose = findViewById(R.id.backCloseSuccess);
        btnBackHome = findViewById(R.id.btnBackToHome);

        btnClose.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        btnBackHome.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
}