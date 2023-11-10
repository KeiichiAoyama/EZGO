package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int selectedTab = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutHome = findViewById(R.id.layoutHome);
        final LinearLayout layoutExplore = findViewById(R.id.layoutExplore);
        final LinearLayout layoutOrder = findViewById(R.id.layoutOrder);
        final LinearLayout layoutProfile = findViewById(R.id.layoutProfile);

        final ImageView imageHome = findViewById(R.id.imageHome);
        final ImageView imageExplore = findViewById(R.id.imageExplore);
        final ImageView imageOrder = findViewById(R.id.imageOrder);
        final ImageView imageProfile = findViewById(R.id.imageProfile);

        final TextView txtHome = findViewById(R.id.txtHome);
        final TextView txtExplore = findViewById(R.id.txtExplore);
        final TextView txtOrder = findViewById(R.id.txtOrder);
        final TextView txtProfile = findViewById(R.id.txtProfile);


        getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                                .replace(R.id.fragmenContainer, HomeFragment.class,null)
                                        .commit();
        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 1){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmenContainer, HomeFragment.class,null)
                            .commit();

                    txtExplore.setVisibility(View.GONE);
                    txtOrder.setVisibility(View.GONE);
                    txtProfile.setVisibility(View.GONE);

                    imageExplore.setImageResource(R.drawable.outline_explore_24);
                    imageOrder.setImageResource(R.drawable.round_list_alt_24_2);
                    imageProfile.setImageResource(R.drawable.outline_person_24);

                    layoutExplore.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutOrder.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //Select home
                    txtHome.setVisibility(View.VISIBLE);
                    imageHome.setImageResource(R.drawable.round_home_24);
                    layoutHome.setBackgroundResource(R.drawable.round_back);

                    //Animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    layoutHome.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }
            }
        });

        layoutExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 2){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmenContainer, ExploreFragment.class,null)
                            .commit();

                    txtHome.setVisibility(View.GONE);
                    txtOrder.setVisibility(View.GONE);
                    txtProfile.setVisibility(View.GONE);

                    imageHome.setImageResource(R.drawable.outline_home_24);
                    imageOrder.setImageResource(R.drawable.round_list_alt_24_2);
                    imageProfile.setImageResource(R.drawable.outline_person_24);

                    layoutHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutOrder.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //Select home
                    txtExplore.setVisibility(View.VISIBLE);
                    imageExplore.setImageResource(R.drawable.round_explore_24);
                    layoutExplore.setBackgroundResource(R.drawable.round_back);

                    //Animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    layoutExplore.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }
            }
        });

        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 3){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmenContainer, OrderFragment.class,null)
                            .commit();

                    txtExplore.setVisibility(View.GONE);
                    txtHome.setVisibility(View.GONE);
                    txtProfile.setVisibility(View.GONE);

                    imageExplore.setImageResource(R.drawable.outline_explore_24);
                    imageHome.setImageResource(R.drawable.outline_home_24);
                    imageProfile.setImageResource(R.drawable.outline_person_24);

                    layoutExplore.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //Select home
                    txtOrder.setVisibility(View.VISIBLE);
                    imageOrder.setImageResource(R.drawable.round_list_alt_24);
                    layoutOrder.setBackgroundResource(R.drawable.round_back);

                    //Animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    layoutOrder.startAnimation(scaleAnimation);

                    selectedTab = 3;
                }
            }
        });

        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 4){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmenContainer, ProfileFragment.class,null)
                            .commit();

                    txtExplore.setVisibility(View.GONE);
                    txtOrder.setVisibility(View.GONE);
                    txtHome.setVisibility(View.GONE);

                    imageExplore.setImageResource(R.drawable.outline_explore_24);
                    imageOrder.setImageResource(R.drawable.round_list_alt_24_2);
                    imageHome.setImageResource(R.drawable.outline_home_24);

                    layoutExplore.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutOrder.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    layoutHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //Select home
                    txtProfile.setVisibility(View.VISIBLE);
                    imageProfile.setImageResource(R.drawable.round_person_24);
                    layoutProfile.setBackgroundResource(R.drawable.round_back);

                    //Animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    layoutProfile.startAnimation(scaleAnimation);

                    selectedTab = 4;
                }
            }
        });

    }
}