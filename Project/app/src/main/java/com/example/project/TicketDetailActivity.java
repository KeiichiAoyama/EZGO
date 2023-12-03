package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.Objects;

public class TicketDetailActivity extends AppCompatActivity {

    ImageButton back, search;
    ImageView iconPesawat;

    MaterialButton order;
    TextView from,to,date,arivTime,departTime,travelTime,planeName,seatType,ticketprice,qty,addfee,totalprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        back = findViewById(R.id.backDetailTicket);
        iconPesawat = findViewById(R.id.iconDetailTicket);
        from = findViewById(R.id.txtFromDetail);
        to = findViewById(R.id.txtToDetail);
        date = findViewById(R.id.txtDateDetail);
        arivTime = findViewById(R.id.txtArrivalTimeDetail);
        departTime = findViewById(R.id.txtDepartureDetail);
        travelTime = findViewById(R.id.txtTravelTimeDetail);
        planeName = findViewById(R.id.txtNamaDetail);
        seatType = findViewById(R.id.txtSeatTypeDetailTicket);
        ticketprice = findViewById(R.id.ticketPrice);
        qty = findViewById(R.id.ticketQty);
        addfee = findViewById(R.id.addFee);
        totalprice = findViewById(R.id.totalPrice);
        order = findViewById(R.id.orderTicket);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String ID = bundle.getString("ID");
            int price = bundle.getInt("Price");
            int Fee = 2000;
            int quanty = Integer.parseInt(bundle.getString("Qty"));
            int tPrice = price*quanty + Fee;
            from.setText(bundle.getString("From"));
            to.setText(bundle.getString("Dest"));
            travelTime.setText(bundle.getString("TravelTime"));
            departTime.setText(bundle.getString("DeptTime"));
            arivTime.setText(bundle.getString("ArivTime"));
            ticketprice.setText(decimalFormat.format(bundle.getInt("Price")));
            qty.setText(bundle.getString("Qty"));
            addfee.setText(decimalFormat.format(Fee));
            totalprice.setText(decimalFormat.format(tPrice));
        }

        back.setOnClickListener(view -> onBackPressed());

        order.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
            Bundle b = new Bundle();
            b.putString("id", Objects.requireNonNull(bundle).getString("ID"));
            b.putString("price", totalprice.getText().toString());
            i.putExtras(b);
            startActivity(i);
        });

    }
}