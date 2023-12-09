package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterHome adapterHome;

    ArrayList<MyItem> itemList1;

    LinearLayout btnTicket, btnHotel, btnTour, btnSearch;
    ShapeableImageView btnProfile;
    TextView uName;
    String image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home,container,false);
        SharedPreferences preferences = getActivity().getSharedPreferences("ezgo", Context.MODE_PRIVATE);
        String userJson = preferences.getString("user", null);
        User user = new Gson().fromJson(userJson, User.class);
        Log.d("Ezgo", "uName2: " + user.uName);

        uName = v.findViewById(R.id.profileName);
        btnSearch = v.findViewById(R.id.search);
        btnTicket = v.findViewById(R.id.btnTicket);
        btnHotel = v.findViewById(R.id.btnHotel);
        btnTour = v.findViewById(R.id.btnTour);
        btnProfile = v.findViewById(R.id.profileHome);

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        uName.setText(user.uName);
        if(user.uProfilePicture != null){
            image = urlImg + user.uProfilePicture;
        }else{
            image = urlImg + "defaultpfp.png";
        }
        Picasso.get().load(image).into(btnProfile);

        btnTicket.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), TicketActivity.class);
            startActivity(i);
        });
        btnHotel.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), HotelActivity.class);
            startActivity(i);
        });
        btnTour.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), TourActivity.class);
            startActivity(i);
        });

        btnProfile.setOnClickListener(view -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmenContainer, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        btnSearch.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(),SearchActivity.class);
            startActivity(i);
        });

        recyclerView = v.findViewById(R.id.ViewHome1);
        itemList1 = new ArrayList<>();
        itemList1.add(new MyItem("Pantai",R.drawable.maxresdefault));
        itemList1.add(new MyItem("Raja Ampat", R.drawable.rajaampat));
        itemList1.add(new MyItem("Bromo",R.drawable.bromo));
        itemList1.add(new MyItem("Item 4",R.drawable.maxresdefault));
        itemList1.add(new MyItem("Item 5",R.drawable.maxresdefault));
        adapterHome = new AdapterHome(getActivity(), itemList1);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2 ));
        recyclerView.setAdapter(adapterHome);
        return v;
    }
}