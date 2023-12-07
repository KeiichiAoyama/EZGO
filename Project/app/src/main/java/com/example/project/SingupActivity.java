package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SingupActivity extends AppCompatActivity {
    private TextInputEditText inputUsername, inputName, inputPass, inputEmail, inputTelp;
    private String username, name, pass, email, telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        MaterialButton btn = (MaterialButton) findViewById(R.id.btnCreateAcc);
        inputUsername = (TextInputEditText) findViewById(R.id.inputUsername);
        inputName = (TextInputEditText) findViewById(R.id.inputname);
        inputPass = (TextInputEditText) findViewById(R.id.inputPassword);
        inputEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        inputTelp = (TextInputEditText) findViewById(R.id.inputTelp);
        btn.setOnClickListener(view -> {
            username = Objects.requireNonNull(inputUsername.getText()).toString();
            name = Objects.requireNonNull(inputName.getText()).toString();
            pass = Objects.requireNonNull(inputPass.getText()).toString();
            email = inputEmail.getText().toString();
            telp = inputTelp.getText().toString();

            String url = "http://localhost/web-api/router.php?controller=loginController&method=createAccount";
            RequestQueue queue = Volley.newRequestQueue(this);
            Gson gson = new Gson();

            Map<String, Object> params = new HashMap<>();
            params.put("userID", username);
            params.put("uPassword", pass);
            params.put("uName", name);
            params.put("uEmail", email);
            params.put("uPhone", telp);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            boolean success = gson.fromJson(response.toString(), Boolean.class);

                            if (success == true) {
                                /*if (errLogin != null) {
                                    errLogin.setVisibility(View.GONE);
                                }*/

                                User user = new User(username, pass, name, email, telp);

                                SharedPreferences preferences = getSharedPreferences("ezgo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user", user.toJson());
                                editor.apply();

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else {
                                /*if (errLogin != null) {
                                    errLogin.setVisibility(View.VISIBLE);
                                }*/
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Ezgo", "Error: " + error.toString());
                        }
                    });
            queue.add(jsonObjectRequest);

            inputUsername.setText("");
            inputName.setText("");
            inputPass.setText("");
            inputEmail.setText("");
            inputTelp.setText("");
        });

    }
}