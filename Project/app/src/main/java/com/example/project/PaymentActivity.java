package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    TextView payment1, payment2, payment3, payment4;
    String no1, no2, no3, no4;
    String banktxt1, banktxt2, banktxt3, banktxt4;
    int price, type, amount;
    ticket tix;
    hotel htl;
    tour trp;
    FrameLayout btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        payment1 = findViewById(R.id.btnPayment2);
        payment2 = findViewById(R.id.btnPayment3);
        payment3 = findViewById(R.id.btnPayment4);
        payment4 = findViewById(R.id.btnPayment5);
        btnBack = findViewById(R.id.backPayment);

        banktxt1 = payment1.getText().toString();
        banktxt2 = payment2.getText().toString();
        banktxt3 = payment3.getText().toString();
        banktxt4 = payment4.getText().toString();

       Intent intent = getIntent();
       price = intent.getIntExtra("price", 0);
       amount = intent.getIntExtra("amount", 0);

       if(intent.getSerializableExtra("object") instanceof ticket){
           tix = (ticket) intent.getSerializableExtra("object");
           type = 1;
        }else if(intent.getSerializableExtra("object") instanceof hotel){
           htl = (hotel) intent.getSerializableExtra("object");
           type = 2;
       }else if(intent.getSerializableExtra("object") instanceof tour){
           trp = (tour) intent.getSerializableExtra("object");
           type = 3;
        }

        no1 = "A";
        no2 = "B";
        no3 = "C";
        no4 = "D";

        btnBack.setOnClickListener(view -> onBackPressed());
        payment1.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("norek", no1);
            bundle1.putString("bank", banktxt1);
            bundle1.putInt("price", price);
            bundle1.putInt("amount", amount);
            bundle1.putInt("draw", R.drawable.bca);

            switch (type){
                case 1:
                    bundle1.putSerializable("object", tix);
                    break;
                case 2:
                    bundle1.putSerializable("object", htl);
                    break;
                case 3:
                    bundle1.putSerializable("object", trp);
                    break;
            }

            i.putExtras(bundle1);
            startActivity(i);
        });
        payment2.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("norek", no2);
            bundle2.putString("bank", banktxt2);
            bundle2.putInt("price", price);
            bundle2.putInt("amount", amount);

            switch (type){
                case 1:
                    bundle2.putSerializable("object", tix);
                    break;
                case 2:
                    bundle2.putSerializable("object", htl);
                    break;
                case 3:
                    bundle2.putSerializable("object", trp);
                    break;
            }

            bundle2.putInt("draw", R.drawable.mandiri);
            i.putExtras(bundle2);
            startActivity(i);
        });
        payment3.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            Bundle bundle3 = new Bundle();
            bundle3.putString("norek", no3);
            bundle3.putInt("price", price);
            bundle3.putInt("amount", amount);
            bundle3.putString("bank", banktxt3);

            switch (type){
                case 1:
                    bundle3.putSerializable("object", tix);
                    break;
                case 2:
                    bundle3.putSerializable("object", htl);
                    break;
                case 3:
                    bundle3.putSerializable("object", trp);
                    break;
            }

            bundle3.putInt("draw", R.drawable.bni);
            i.putExtras(bundle3);
            startActivity(i);
        });
        payment4.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentDetailActivity.class);
            Bundle bundle4 = new Bundle();
            bundle4.putString("norek", no4);
            bundle4.putInt("price", price);
            bundle4.putInt("amount", amount);
            bundle4.putString("bank", banktxt4);

            switch (type){
                case 1:
                    bundle4.putSerializable("object", tix);
                    break;
                case 2:
                    bundle4.putSerializable("object", htl);
                    break;
                case 3:
                    bundle4.putSerializable("object", trp);
                    break;
            }

            bundle4.putInt("draw", R.drawable.bri);
            i.putExtras(bundle4);
            startActivity(i);
        });
    }
}