package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    TextView account, password, terms, about, settings, del, logout, name, email;
    ShapeableImageView img;
    String image;

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
        name = v.findViewById(R.id.accName);
        email = v.findViewById(R.id.accEmail);
        img = v.findViewById(R.id.accImg);

        SharedPreferences preferences = getActivity().getSharedPreferences("ezgo", Context.MODE_PRIVATE);
        String userJson = preferences.getString("user", null);
        User user = new Gson().fromJson(userJson, User.class);

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        name.setText(user.uName);
        email.setText(user.uEmail);
        if(user.uProfilePicture != null){
            image = urlImg + user.uProfilePicture;
        }else{
            image = urlImg + "defaultpfp.png";
        }
        Picasso.get().load(image).into(img);

        account.setOnClickListener(view -> startActivity(new Intent(getActivity(), AccountInformationActivity.class)));
        password.setOnClickListener(view -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        terms.setOnClickListener(view -> startActivity(new Intent(getActivity(), TermsNCondActivity.class)));
        settings.setOnClickListener(view -> startActivity(new Intent(getActivity(), SettingsActivity.class)));
        about.setOnClickListener(view -> startActivity(new Intent(getActivity(), AboutActivity.class)));
        logout.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("user");
            editor.apply();

            internalDB db = new internalDB(getActivity());
            db.deleteUser(user);

            startActivity(new Intent(getActivity(), LandingActivity.class));
        });
        return v;
    }
}