package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class TicketActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int REQUEST_CODE = 1;
    private int tanggal, bulan, tahun;
    private TextView btnFrom, btnTo, calender;
    private Spinner passenger,type;
    private final String[] types = {"Plane", "Train", "Bus"};
    private final String[] passangers = {"1","2","3","4","5","6","7","8","9","10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        //find id
        type = findViewById(R.id.type);
        passenger = findViewById(R.id.passanger);
        ImageButton back = findViewById(R.id.backTicket1);
        ImageButton search = findViewById(R.id.searchTicket1);
        btnFrom = findViewById(R.id.btnFrom);
        calender = findViewById(R.id.calender);
        btnTo = findViewById(R.id.btnTo);
        MaterialButton btnPesawat = findViewById(R.id.btnSearchTicket1);

        ArrayAdapter<String> adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,types);
        type.setAdapter(adapterType);
        type.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapterPassanger = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,passangers);
        passenger.setAdapter(adapterPassanger);
        passenger.setOnItemSelectedListener(this);

        calender.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            tahun = calendar.get(Calendar.YEAR);
            bulan = calendar.get(Calendar.MONTH);
            tanggal = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(TicketActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    tahun = i;
                    bulan = i1 + 1;
                    tanggal = i2;

                    calender.setText(tanggal+"/"+bulan+"/"+tahun);
                }
            },tahun,bulan,tanggal);
            dialog.show();

        });

        //btn
        btnFrom.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), KotaActivity.class);
            intent.putExtra("Type","Ticket");
            intent.putExtra("mode", "asal");
            startActivityForResult(intent, REQUEST_CODE);
        });
        btnTo.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), KotaActivity.class);
            intent.putExtra("Type","Ticket");
            intent.putExtra("mode", "tujuan");
            startActivityForResult(intent, REQUEST_CODE);
        });
        btnPesawat.setOnClickListener(view -> {
            String to = btnTo.getText().toString();
            String from = btnFrom.getText().toString();
            String date = calender.getText().toString();
            String pas = passenger.getSelectedItem().toString();
            String ticktype = type.getSelectedItem().toString();
            Intent i = new Intent(getApplicationContext(), TicketViewActivity.class);
            i.putExtra("from", from);
            i.putExtra("to",to);
            i.putExtra("date",date);
            i.putExtra("ticktype", ticktype);
            i.putExtra("pas",pas);
            startActivity(i);
        });
        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(i);
        });
    }

    // set filter
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String mode = data.getStringExtra("mode");
            String kota = data.getStringExtra("kota");

            if (mode != null && kota != null) {
                if (mode.equals("asal")) {
                    btnFrom.setText(kota);
                } else if (mode.equals("tujuan")) {
                    btnTo.setText(kota);
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}