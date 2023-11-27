package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText inputPass,inputEmail;

    private TextView errLogin;

    private String serverPass, serverEmail, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        serverPass = "B";
        serverEmail = "A";
        MaterialButton btnlogin = (MaterialButton) findViewById(R.id.btnLogin);
        inputEmail = (TextInputEditText)findViewById(R.id.inputEmail);
        inputPass = (TextInputEditText)findViewById(R.id.inputPass);
        errLogin = (TextView)findViewById(R.id.errLogin);
        btnlogin.setOnClickListener(view -> {
            email = Objects.requireNonNull(inputEmail.getText()).toString();
            pass = Objects.requireNonNull(inputPass.getText()).toString();
            if (email.equalsIgnoreCase(serverEmail) && pass.equalsIgnoreCase(serverPass)) {
                if (errLogin != null) {
                    errLogin.setVisibility(View.GONE);
                }
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            } else {
                if (errLogin != null) {
                    errLogin.setVisibility(View.VISIBLE);
                }
            }
            inputEmail.setText("");
            inputPass.setText("");
        });

    }
}