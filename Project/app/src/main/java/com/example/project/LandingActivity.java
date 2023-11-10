package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class LandingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new LandingAdapter(this));
    }


}