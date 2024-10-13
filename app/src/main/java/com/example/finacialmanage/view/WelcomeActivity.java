package com.example.finacialmanage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finacialmanage.R;

public class WelcomeActivity extends AppCompatActivity {
    Button bt_know_wel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        bt_know_wel = findViewById(R.id.know_wel);

        bt_know_wel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Boolean status = getSharedPreferences("loginstatus",MODE_PRIVATE).getBoolean("st",false);
        SharedPreferences.Editor editor = getSharedPreferences("loginstatus",MODE_PRIVATE).edit();
        editor.putBoolean("st",true);
        editor.commit();
        if(status)
        {
            Intent intent2 = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent2);
            finish();
        }
    }
}