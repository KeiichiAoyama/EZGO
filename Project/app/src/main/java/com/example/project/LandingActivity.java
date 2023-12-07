package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.gson.Gson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        internalDB db = new internalDB(this);
        boolean check = db.checkUserExist();

        Toast.makeText(this, Boolean.toString(check), Toast.LENGTH_SHORT).show();

        if(check == true){
            User user = db.getUser();
            Toast.makeText(this, user.userID, Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getSharedPreferences("ezgo", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user", user.toJson());
            editor.apply();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }else{
            ViewPager2 viewPager = findViewById(R.id.pager);
            viewPager.setAdapter(new LandingAdapter(this));
        }
    }


}