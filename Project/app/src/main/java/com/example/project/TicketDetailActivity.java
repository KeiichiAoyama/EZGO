package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class TicketDetailActivity extends AppCompatActivity {

    ImageButton back, search;
    ImageView iconPesawat;
    private String ID;

    MaterialButton order;
    TextView from,to,date,arivTime,departTime,travelTime,planeName,seatType,ticketprice,qty,addfee,totalprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        back = (ImageButton) findViewById(R.id.backDetailTicket);
        search = (ImageButton) findViewById(R.id.searchDetailTicket);
        iconPesawat = (ImageView)findViewById(R.id.iconDetailTicket);
        from = (TextView)findViewById(R.id.txtFromDetail);
        to = (TextView)findViewById(R.id.txtToDetail);
        date = (TextView)findViewById(R.id.txtDateDetail);
        arivTime = (TextView)findViewById(R.id.txtArrivalTimeDetail);
        departTime = (TextView)findViewById(R.id.txtDepartureDetail);
        travelTime = (TextView)findViewById(R.id.txtTravelTimeDetail);
        planeName = (TextView)findViewById(R.id.txtNamaDetail);
        seatType = (TextView)findViewById(R.id.txtSeatTypeDetailTicket);
        ticketprice = (TextView)findViewById(R.id.ticketPrice);
        qty = (TextView)findViewById(R.id.ticketQty);
        addfee = (TextView)findViewById(R.id.addFee);
        totalprice = (TextView)findViewById(R.id.totalPrice);
        order = (MaterialButton)findViewById(R.id.orderTicket);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ID = bundle.getString("ID");
            from.setText(bundle.getString("From"));
            to.setText(bundle.getString("Dest"));
            travelTime.setText(bundle.getString("TravelTime"));
            departTime.setText(bundle.getString("DeptTime"));
            arivTime.setText(bundle.getString("ArivTime"));
        }
        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(i);
        });
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