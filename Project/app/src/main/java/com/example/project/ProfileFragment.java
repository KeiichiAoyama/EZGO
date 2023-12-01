package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    TextView account, password, terms, about, settings, del, logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profile, container, false);
        account = v.findViewById(R.id.btnAccount);
        password = v.findViewById(R.id.btnPassword);
        terms = v.findViewById(R.id.btnTerms);
        settings = v.findViewById(R.id.btnSettings);
        about = v.findViewById(R.id.btnAbout);
        del = v.findViewById(R.id.btnDelAcc);
        logout = v.findViewById(R.id.btnLogout);

        account.setOnClickListener(view -> startActivity(new Intent(getActivity(), AccountInformationActivity.class)));
        password.setOnClickListener(view -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        terms.setOnClickListener(view -> startActivity(new Intent(getActivity(), TermsNCondActivity.class)));
        settings.setOnClickListener(view -> startActivity(new Intent(getActivity(), SettingsActivity.class)));
        about.setOnClickListener(view -> startActivity(new Intent(getActivity(), AboutActivity.class)));
        return v;
    }
}