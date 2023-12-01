package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class LandingFragmentThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_landing_three,container,false);
        MaterialButton btnLogin = v.findViewById(R.id.btnLoginLand);
        MaterialButton btnRegis = v.findViewById(R.id.btnRegisLand);
        btnLogin.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        });
        btnRegis.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(),SingupActivity.class);
            startActivity(i);
        });
        return v;
    }
}