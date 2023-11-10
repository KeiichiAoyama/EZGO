package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView,recyclerView2;
    AdapterHome adapterHome;

    ArrayList<MyItem> itemList1,itemList2;

    LinearLayout btnPesawat, btnKereta, btnBus, btnHotel, btnTour, btnSearch;
    ShapeableImageView btnProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home,container,false);
        //find id
        btnSearch = v.findViewById(R.id.search);
        btnPesawat = v.findViewById(R.id.btnPesawat);
        btnKereta = v.findViewById(R.id.btnKereta);
        btnBus = v.findViewById(R.id.btnBus);
        btnHotel = v.findViewById(R.id.btnHotel);
        btnTour = v.findViewById(R.id.btnTour);
        btnProfile = v.findViewById(R.id.profileHome);

        btnPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PesawatActivity.class);
                startActivity(i);
            }
        });

        btnKereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), KeretaActivity.class);
                startActivity(i);
            }
        });
        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), BusActivity.class);
                startActivity(i);
            }
        });
        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), HotelActivity.class);
                startActivity(i);
            }
        });
        btnTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), TourActivity.class);
                startActivity(i);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();

                // Memulai transaksi untuk mengganti Fragment pertama dengan Fragment kedua
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmenContainer, profileFragment);
                transaction.addToBackStack(null); // Menambahkan transaksi ke back stack (opsional)
                transaction.commit();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),SearchActivity.class);
                startActivity(i);
            }
        });

        recyclerView = v.findViewById(R.id.ViewHome1);
        itemList1 = new ArrayList<>();
        itemList1.add(new MyItem("Item 1"));
        itemList1.add(new MyItem("Item 2"));
        itemList1.add(new MyItem("Item 3"));
        itemList1.add(new MyItem("Item 4"));
        itemList1.add(new MyItem("Item 5"));
        adapterHome = new AdapterHome(getActivity(), itemList1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapterHome);

        recyclerView2 = v.findViewById(R.id.ViewHome2);
        itemList2 = new ArrayList<>();
        itemList2.add(new MyItem("Item 1"));
        itemList2.add(new MyItem("Item 2"));
        itemList2.add(new MyItem("Item 3"));
        itemList2.add(new MyItem("Item 4"));
        itemList2.add(new MyItem("Item 5"));
        adapterHome = new AdapterHome(getActivity(), itemList2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setAdapter(adapterHome);

        return v;
    }
}