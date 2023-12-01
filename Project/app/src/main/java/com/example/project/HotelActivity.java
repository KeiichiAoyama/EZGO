package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class HotelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int tanggal, bulan, tahun;
    private TextView btnLocation, calender;
    private Spinner person, room;
    private final String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        //find id
        room = findViewById(R.id.room);
        person = findViewById(R.id.person);
        ImageButton back = findViewById(R.id.backHotel);
        ImageButton search = findViewById(R.id.searchHotel);
        btnLocation = findViewById(R.id.btnLocationHotel);
        calender = findViewById(R.id.calenderHotel);
        MaterialButton btnHotel = findViewById(R.id.btnSearchHotel);

        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, number);
        room.setAdapter(adapterRoom);
        room.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapterPerson = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, number);
        person.setAdapter(adapterPerson);
        person.setOnItemSelectedListener(this);


        calender.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            tahun = calendar.get(Calendar.YEAR);
            bulan = calendar.get(Calendar.MONTH);
            tanggal = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(HotelActivity.this, (datePicker, i, i1, i2) -> {
                tahun = i;
                bulan = i1 + 1;
                tanggal = i2;

                calender.setText(tanggal + "/" + bulan + "/" + tahun);
            }, tahun, bulan, tanggal);
            dialog.show();

        });
        Intent intent = getIntent();
        if(intent != null){
            btnLocation.setText(intent.getStringExtra("kota"));
        }
        //btn
        btnLocation.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ListActivity.class)));

        btnHotel.setOnClickListener(view -> {
            String loc = btnLocation.getText().toString();
            String date = calender.getText().toString();
            String per = person.getSelectedItem().toString();
            String rooms = room.getSelectedItem().toString();
            Intent i = new Intent(getApplicationContext(), TicketViewActivity.class);
            i.putExtra("loc", loc);
            i.putExtra("date", date);
            i.putExtra("room", rooms);
            i.putExtra("per", per);
            startActivity(i);
        });
        back.setOnClickListener(view -> onBackPressed());
        search.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
