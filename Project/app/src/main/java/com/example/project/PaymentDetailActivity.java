package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PaymentDetailActivity extends AppCompatActivity {
    private final int duration = 360;
    TextView txtRek, txtAmount, txtBank;
    ImageView imgBank;
    LinearLayout pb;
    int img;
    String noRek, amount, bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        txtRek = findViewById(R.id.txtNoRek);
        txtBank = findViewById(R.id.txtBank);
        txtAmount = findViewById(R.id.txtAmount);
        imgBank = findViewById(R.id.imgBank);
        pb = findViewById(R.id.progressBarPayment);

        pb.setVisibility(LinearLayout.INVISIBLE);


        MaterialButton copyButtonRek = findViewById(R.id.btnCopyRek);
        MaterialButton copyButtonAmount = findViewById(R.id.btnCopyAmount);
        MaterialButton btnDone = findViewById(R.id.btnDone);


        Bundle b = getIntent().getExtras();
        noRek = b.getString("norek");
        bank = b.getString("bank");
        img = b.getInt("draw");
        amount = b.getString("price");
        txtBank.setText(bank);
        txtRek.setText(noRek);
        imgBank.setImageResource(img);

        new CountDownTimer(duration * 1000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long totalMinutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                long hours = totalMinutes / 60;
                long minutes = totalMinutes % 60;
                long seconds = totalSeconds % 60;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                final String[] hourMinSec = time.split(":");
                TextView hour = findViewById(R.id.hour);
                TextView min = findViewById(R.id.min);
                TextView sec = findViewById(R.id.sec);
                hour.setText(hourMinSec[0]);
                min.setText(hourMinSec[1]);
                sec.setText(hourMinSec[2]);
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }.start();

        btnDone.setOnClickListener(view -> {
            pb.setVisibility(LinearLayout.VISIBLE);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pb.setVisibility(LinearLayout.INVISIBLE);
                    // Kode yang akan dijalankan setelah waktu tertentu (delay)
                    Intent i = new Intent(getApplicationContext(), PaymentSuccessActivity.class);
                    startActivity(i);
                    finish(); // Opsional, untuk menutup activity saat ini jika diinginkan
                }
            }, 3000); // Delay dalam milidetik (dalam contoh ini, 2000ms atau 2 detik)


        });

        copyButtonRek.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("No Rek", noRek);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Teks disalin ke Clipboard", Toast.LENGTH_SHORT).show();
        });

        copyButtonAmount.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Amount", amount);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Teks disalin ke Clipboard", Toast.LENGTH_SHORT).show();
        });
    }
}