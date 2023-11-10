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

public class PesawatActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton back, search;

    private static final int REQUEST_CODE = 1;
    private int tanggal, bulan, tahun;
    private TextView btnFrom, btnTo, calender;
    private Spinner passanger;
    private String[] passangers = {"1","2","3","4","5","6","7","8","9","10"};
    private MaterialButton btnPesawat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesawat);
        //find id
        passanger = (Spinner)findViewById(R.id.passanger);
        back = (ImageButton) findViewById(R.id.backPesawat);
        search = (ImageButton) findViewById(R.id.searchPesawat);
        btnFrom = (TextView) findViewById(R.id.btnFromPlane);
        calender = (TextView) findViewById(R.id.calender);
        btnTo = (TextView) findViewById(R.id.btnToPlane);
        btnPesawat = (MaterialButton) findViewById(R.id.btnSearchPesawat);


        ArrayAdapter adapterPassanger = new ArrayAdapter(this, android.R.layout.simple_spinner_item,passangers);
        passanger.setAdapter(adapterPassanger);
        passanger.setOnItemSelectedListener(this);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PesawatActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tahun = i;
                        bulan = i1 + 1;
                        tanggal = i2;

                        calender.setText(tanggal+"/"+bulan+"/"+tahun);
                    }
                },tahun,bulan,tanggal);
                dialog.show();

            }
        });

        //btn
        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("mode", "asal");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("mode", "tujuan");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        btnPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = btnTo.getText().toString();
                String from = btnFrom.getText().toString();
                String date = calender.getText().toString();
                String pas = passanger.getOnItemSelectedListener().toString();
                Intent i = new Intent(getApplicationContext(), PesawatViewActivity.class);
                i.putExtra("from", from);
                i.putExtra("to",to);
                i.putExtra("date",date);
                i.putExtra("pas",pas);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
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