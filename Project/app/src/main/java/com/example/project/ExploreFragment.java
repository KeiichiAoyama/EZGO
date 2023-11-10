package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterViewExplore adapterViewExplore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_explore,container,false);

            recyclerView = v.findViewById(R.id.viewExplore);
            ExploreData[] exploreData = new ExploreData[]
                {
                        new ExploreData(R.drawable.rajaampat,"Raja Ampat",4,true),
                        new ExploreData(R.drawable.maxresdefault,"Pantai",4,true),
                        new ExploreData(R.drawable.bromo,"Bromo",4,true),
                        new ExploreData(R.drawable.maxresdefault,"Raja Ampat",4,false),
                        new ExploreData(R.drawable.maxresdefault,"Raja Ampat",4,true)
                };
            adapterViewExplore = new AdapterViewExplore(getActivity(), exploreData);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapterViewExplore);


        return v;
    }
}