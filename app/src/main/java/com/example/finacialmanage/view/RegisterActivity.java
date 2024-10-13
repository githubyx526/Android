package com.example.finacialmanage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finacialmanage.R;
import com.example.finacialmanage.db.MyDBHelper;

public class RegisterActivity extends AppCompatActivity {
    EditText ed_name,ed_pwd,ed_email,ed_phone;
    Button bnt_register,bnt_cancel;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_name = findViewById(R.id.ed_name_rg);
        ed_pwd = findViewById(R.id.ed_pwd_rg);
        ed_email = findViewById(R.id.ed_email_rg);
        ed_phone = findViewById(R.id.ed_phone_rg);
        bnt_cancel = findViewById(R.id.bt_cancel_rg);
        bnt_register = findViewById(R.id.bt_ok_rg);
        myDBHelper = new MyDBHelper(RegisterActivity.this);
        db = myDBHelper.getWritableDatabase();

        bnt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("name",ed_name.getText().toString());
                values.put("pwd",ed_pwd.getText().toString());
                values.put("email",ed_email.getText().toString());
                values.put("phone",ed_phone.getText().toString());
                db.insert("tb_userinfo",null,values);
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bnt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}