package com.example.finacialmanage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finacialmanage.R;
import com.example.finacialmanage.db.MyDBHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_name,ed_pwd;
    private Button bnt_newregister,bnt_login;
    private MyDBHelper mhelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_name = findViewById(R.id.ed_name_lg);
        ed_pwd = findViewById(R.id.ed_pwd_lg);
        bnt_login = findViewById(R.id.bt_login_lg);
        bnt_newregister = findViewById(R.id.bt_newregister_lg);
        mhelper = new MyDBHelper(LoginActivity.this);
        db = mhelper.getWritableDatabase();

        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputname = ed_name.getText().toString();
                String inputpwd = ed_pwd.getText().toString();

                if(inputname.equals("")||inputpwd.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Cursor cursor = db.rawQuery("select * from tb_userinfo where name=? and pwd=?",new String[]{inputname,inputpwd});
                    if(cursor.moveToNext())
                    {
                        @SuppressLint("Range") String getname  = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String getpwd  = cursor.getString(cursor.getColumnIndex("pwd"));
                        if(inputname.equalsIgnoreCase(getname)&&inputpwd.equalsIgnoreCase(getpwd))
                        {
                            SharedPreferences.Editor editor = getSharedPreferences("userinfo",0).edit();
                            editor.putString("username",inputname);
                            editor.putString("userpwd",inputpwd);
                            editor.commit();
                            Toast.makeText(LoginActivity.this,"Yes!!",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                        ed_pwd.setText("");
                        ed_name.setText("");
                    }
                }
            }
        });
        bnt_newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent11);
                finish();
            }
        });
    }
}