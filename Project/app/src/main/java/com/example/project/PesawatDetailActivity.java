package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PesawatDetailActivity extends AppCompatActivity {

    ImageButton back, search;
    ImageView iconPesawat;
    TextView from,to,date,arivTime,departTime,travelTime,planeName,seatType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesawat_detail);
        back = (ImageButton) findViewById(R.id.backDetailPesawat);
        search = (ImageButton) findViewById(R.id.searchDetailPesawat);
        iconPesawat = (ImageView)findViewById(R.id.iconDetailPesawat);
        from = (TextView)findViewById(R.id.txtFromDetailPesawat);
        to = (TextView)findViewById(R.id.txtToDetailPesawat);
        date = (TextView)findViewById(R.id.txtDateDetailPesawat);
        arivTime = (TextView)findViewById(R.id.txtArrivalTimeDetailPesawat);
        departTime = (TextView)findViewById(R.id.txtDepartureDetailPesawat);
        travelTime = (TextView)findViewById(R.id.txtTravelTimeDetailPesawat);
        planeName = (TextView)findViewById(R.id.txtNamaPesawatDetail);
        seatType = (TextView)findViewById(R.id.txtSeatTypeDetailPesawat);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
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

    }
}