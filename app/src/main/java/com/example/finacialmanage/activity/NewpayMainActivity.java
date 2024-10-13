package com.example.finacialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finacialmanage.R;
import com.example.finacialmanage.db.MyDBHelper;
import com.example.finacialmanage.view.MainActivity;

public class NewpayMainActivity extends AppCompatActivity {
    private EditText ed_money, ed_time, ed_payer, ed_remark;
    private Spinner sp_type;
    private Button bt_save, bt_cancel;
    private MyDBHelper mHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpay_main);

        // 绑定控件
        ed_money = findViewById(R.id.ed_money_newout);
        ed_time = findViewById(R.id.ed_time_newout);
        sp_type = findViewById(R.id.sp_type_newout);
        ed_payer = findViewById(R.id.ed_payer_newout);
        ed_remark = findViewById(R.id.ed_remark_newout);
        bt_save = findViewById(R.id.bt_save_newout);
        bt_cancel = findViewById(R.id.bt_cancel_newout);
        mHelper = new MyDBHelper(NewpayMainActivity.this);
        db = mHelper.getWritableDatabase();

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("outmoney",ed_money.getText().toString());
                values.put("outtime",ed_time.getText().toString());
                values.put("outtype",sp_type.getSelectedItem().toString());
                values.put("outpayee",ed_payer.getText().toString());
                values.put("outremark",ed_remark.getText().toString());
                db.insert("out_come",null,values);
                Toast.makeText(NewpayMainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewpayMainActivity.this,NewInComeActivity.class);
                startActivity(intent);
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewpayMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}