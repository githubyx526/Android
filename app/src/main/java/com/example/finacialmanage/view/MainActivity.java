package com.example.finacialmanage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finacialmanage.R;
import com.example.finacialmanage.activity.DataAnalyseActivity;
import com.example.finacialmanage.activity.IncomeDetailActivity;
import com.example.finacialmanage.activity.NewInComeActivity;
import com.example.finacialmanage.activity.NewpayMainActivity;
import com.example.finacialmanage.activity.PayDetailMainActivity;
import com.example.finacialmanage.activity.SyssettingActivity;

public class MainActivity extends AppCompatActivity {
    Button bt_newincome,bt_incomedetail,btn_newpay,btn_paydetail,bt_dataalyse,btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btn_Onclick();
    }
    private void initView() {
        bt_newincome = findViewById(R.id.bt_newincome_main);
        bt_incomedetail =findViewById(R.id.bt_incomedetail_main);
        btn_newpay = findViewById(R.id.bt_newpay_main);
        btn_paydetail = findViewById(R.id.bt_paydetail_main);
        bt_dataalyse = findViewById(R.id.bt_dataanalyse_main);
        btn_setting = findViewById(R.id.bt_syssetting_main);
    }

    private void btn_Onclick() {
        bt_newincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewInComeActivity.class);
                startActivity(intent);
            }
        });
        bt_incomedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IncomeDetailActivity.class);
                startActivity(intent);
            }
        });
        btn_newpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewpayMainActivity.class);
                startActivity(intent);
            }
        });
        btn_paydetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PayDetailMainActivity.class);
                startActivity(intent);
            }
        });
        bt_dataalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataAnalyseActivity.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SyssettingActivity.class);
                startActivity(intent);
            }
        });
    }
}