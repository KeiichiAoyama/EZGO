package com.example.project;

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

public class TourDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView calenderTour;

    private int tanggal, bulan, tahun;

    private Spinner person;
    private String[] persons = {"1","2","3","4","5","6","7","8","9","10"};

    private Spinner day;
    private String[] days = {"1 Day","2 Days","3 Days","4 Days","5 Days","6 Days","7 Days","8 Days","9 Days","10 Days"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        // Day
        day = (Spinner)findViewById(R.id.dayDetail1);
        day.setOnItemSelectedListener(this);
        ArrayAdapter adapterDay = new ArrayAdapter(this, android.R.layout.simple_spinner_item,days);
        day.setAdapter(adapterDay);

        // Person
        person = (Spinner)findViewById(R.id.personDetail1);
        person.setOnItemSelectedListener(this);
        ArrayAdapter adapterPerson = new ArrayAdapter(this, android.R.layout.simple_spinner_item,persons);
        person.setAdapter(adapterPerson);

        calenderTour = findViewById(R.id.calenderTour);
        ImageButton back = (ImageButton)findViewById(R.id.backDetail1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        ImageButton search = (ImageButton)findViewById(R.id.searchDetail1);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(i);
                finish();
            }
        });
        calenderTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TourDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tahun = i;
                        bulan = i1;
                        tanggal = i2;

                        calenderTour.setText(tanggal+"/"+bulan+"/"+tahun);
                    }
                },tahun,bulan,tanggal);
                dialog.show();

            }
        });

        MaterialButton btnOrder = (MaterialButton) findViewById(R.id.orderTour);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}