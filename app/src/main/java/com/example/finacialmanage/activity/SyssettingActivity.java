package com.example.finacialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finacialmanage.R;
import com.example.finacialmanage.db.MyDBHelper;
import com.example.finacialmanage.view.LoginActivity;
import com.example.finacialmanage.view.MainActivity;

public class SyssettingActivity extends AppCompatActivity {

    private TextView txtNameSys;
    private EditText edYpwdSys;
    private EditText edXpwdSys;
    private EditText edZxpwdSys;
    private Button btModifySys;
    private Button btCancelSys;
    MyDBHelper mHelper;
    SQLiteDatabase db;
    private String name,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syssetting);

        // 绑定控件
        txtNameSys = findViewById(R.id.txt_name_sys);
        edYpwdSys = findViewById(R.id.ed_ypwd_sys);
        edXpwdSys = findViewById(R.id.ed_xpwd_sys);
        edZxpwdSys = findViewById(R.id.ed_zxpwd_sys);
        btModifySys = findViewById(R.id.bt_modify_sys);
        btCancelSys = findViewById(R.id.bt_cancel_sys);
        mHelper = new MyDBHelper(SyssettingActivity.this);
        db = mHelper.getWritableDatabase();

        name = getSharedPreferences("userinfo",0).getString("username","");
        pwd = getSharedPreferences("userinfo",0).getString("userpwd","");
        txtNameSys.setText(name);

        btModifySys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ypwd = edYpwdSys.getText().toString();
                String xpwd = edXpwdSys.getText().toString();
                String zxpwd = edZxpwdSys.getText().toString();
                if(ypwd.equals(""))
                {
                    Toast.makeText(SyssettingActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(!ypwd.equalsIgnoreCase(pwd))
                {
                    Toast.makeText(SyssettingActivity.this, "输入的密码与原始密码不一样", Toast.LENGTH_SHORT).show();
                }else if(xpwd.equals(""))
                {
                    Toast.makeText(SyssettingActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(xpwd.equalsIgnoreCase(pwd))
                {
                    Toast.makeText(SyssettingActivity.this, "输入的新密码与原始密码不能相同", Toast.LENGTH_SHORT).show();
                }else if(zxpwd.equals(""))
                {
                    Toast.makeText(SyssettingActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(!zxpwd.equalsIgnoreCase(xpwd))
                {
                    Toast.makeText(SyssettingActivity.this, "两次输入的新密码不一样", Toast.LENGTH_SHORT).show();
                }else
                {
                    ContentValues values = new ContentValues();
                    values.put("pwd",xpwd);
                    db.update("tb_userinfo",values,"name=?",new String[]{name});
                    Toast.makeText(SyssettingActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SyssettingActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btCancelSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SyssettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}