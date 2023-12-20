package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment {
    TextView account, password, about, del, logout, name;
    ShapeableImageView img;
    String image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profile, container, false);
        account = v.findViewById(R.id.btnAccount);
        password = v.findViewById(R.id.btnPassword);
        about = v.findViewById(R.id.btnAbout);
        del = v.findViewById(R.id.btnDelAcc);
        logout = v.findViewById(R.id.btnLogout);
        name = v.findViewById(R.id.accName);
        img = v.findViewById(R.id.accImg);

        SharedPreferences preferences = getActivity().getSharedPreferences("ezgo", Context.MODE_PRIVATE);
        String userJson = preferences.getString("user", null);
        User user = new Gson().fromJson(userJson, User.class);

        String urlReq = "https://projekuasmobappezgowebsite.000webhostapp.com/router.php";
        String urlImg = "https://projekuasmobappezgowebsite.000webhostapp.com/images/";

        name.setText(user.uName);
        if(user.uProfilePicture != null){
            image = urlImg + user.uProfilePicture;
        }else{
            image = urlImg + "defaultpfp.png";
        }
        Picasso.get().load(image).into(img);

        account.setOnClickListener(view -> startActivity(new Intent(getActivity(), AccountInformationActivity.class)));
        password.setOnClickListener(view -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        about.setOnClickListener(view -> startActivity(new Intent(getActivity(), AboutActivity.class)));
        logout.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("user");
            editor.apply();

            internalDB db = new internalDB(getActivity());
            db.deleteUser(user);

            startActivity(new Intent(getActivity(), LandingActivity.class));
        });
        del.setOnClickListener(view -> {
            RequestQueue queue = Volley.newRequestQueue(requireActivity());
            Gson gson = new Gson();

            Map<String, Object> params = new HashMap<>();
            params.put("controller", "account");
            params.put("method", "delete");
            params.put("userID", user.userID);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlReq,
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Ezgo", "ResponseHome: " + response);
                            ResponseNoObject resp = gson.fromJson(response.toString(), new TypeToken<ResponseNoObject>() {}.getType());
                            boolean success = resp.isSuccess();

                            if (success == true) {
                                try {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.remove("user");
                                    editor.apply();

                                    internalDB db = new internalDB(getActivity());
                                    db.deleteUser(user);

                                    startActivity(new Intent(getActivity(), LandingActivity.class));
                                }catch (Exception e){
                                    Log.e("Ezgo", "Error: " + e);
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Ezgo", "Error: " + error.toString());
                            String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            Log.e("Ezgo", "Response: " + responseBody);
                        }
                    });
            queue.add(jsonObjectRequest);
        });
        return v;
    }
}