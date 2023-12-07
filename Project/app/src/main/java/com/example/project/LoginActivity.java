package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText inputPass,inputUsername;

    private TextView errLogin;

    private String username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MaterialButton btnlogin = (MaterialButton) findViewById(R.id.btnLogin);
        inputUsername = (TextInputEditText)findViewById(R.id.inputUsername);
        inputPass = (TextInputEditText)findViewById(R.id.inputPass);
        errLogin = (TextView)findViewById(R.id.errLogin);
        btnlogin.setOnClickListener(view -> {
            username = Objects.requireNonNull(inputUsername.getText()).toString();
            pass = Objects.requireNonNull(inputPass.getText()).toString();

            String url = "http://localhost/web-api/router.php?controller=loginController&method=login";
            RequestQueue queue = Volley.newRequestQueue(this);
            Gson gson = new Gson();

            Map<String, Object> params = new HashMap<>();
            params.put("userID", username);
            params.put("uPassword", pass);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ResponseOneObject<User> resp = gson.fromJson(response.toString(), new TypeToken<ResponseOneObject<User>>() {}.getType());
                            boolean success = resp.isSuccess();
                            User user = resp.getData();

                            if (success == true) {
                                if (errLogin != null) {
                                    errLogin.setVisibility(View.GONE);
                                }

                                SharedPreferences preferences = getSharedPreferences("ezgo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user", user.toJson());
                                editor.apply();

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else {
                                if (errLogin != null) {
                                    errLogin.setVisibility(View.VISIBLE);
                                }
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
            inputPass.setText("");
        });

    }
}