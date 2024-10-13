package com.example.finacialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

public class NewInComeActivity extends AppCompatActivity {
    private EditText ed_money, ed_time, ed_payer, ed_remark;
    private Spinner sp_type;
    private Button bt_save, bt_cancel;
//    private Button btn_query;
    private MyDBHelper mHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_come);
        // 绑定控件
        ed_money = findViewById(R.id.ed_money_newin);
        ed_time = findViewById(R.id.ed_time_newin);
        sp_type = findViewById(R.id.sp_type_newin);
        ed_payer = findViewById(R.id.ed_payer_newin);
        ed_remark = findViewById(R.id.ed_remark_newin);
        bt_save = findViewById(R.id.bt_save_newin);
        bt_cancel = findViewById(R.id.bt_cancel_newin);
//        btn_query = findViewById(R.id.bt_query_newin);
        mHelper = new MyDBHelper(NewInComeActivity.this);
        db = mHelper.getWritableDatabase();
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("inmoney",ed_money.getText().toString());
                values.put("intime",ed_time.getText().toString());
                values.put("intype",sp_type.getSelectedItem().toString());
                values.put("inplayer",ed_payer.getText().toString());
                values.put("inremark",ed_remark.getText().toString());
                db.insert("in_come",null,values);
                Toast.makeText(NewInComeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewInComeActivity.this,NewInComeActivity.class);
                startActivity(intent);
            }
        });
//        btn_query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cursor cursor = db.rawQuery("select * from in_come where inmoney=?",new String[]{ed_time.getText().toString()});
//                if(cursor.getCount() !=  0)
//                {
//                    Toast.makeText(NewInComeActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
//                    while (cursor.moveToNext())
//                    {
//                        @SuppressLint("Range") String myson = cursor.getString(cursor.getColumnIndex("inmoney"));
//                        @SuppressLint("Range") String myname = cursor.getString(cursor.getColumnIndex("ed_time"));
//                        @SuppressLint("Range") String mysex = cursor.getString(cursor.getColumnIndex("intype"));
//                        @SuppressLint("Range") String mypro = cursor.getString(cursor.getColumnIndex("inplayer"));
//                        @SuppressLint("Range") String mydep = cursor.getString(cursor.getColumnIndex("inremark"));
//                        ed_time.setText("学号="+myson+"\n"+"姓名="+myname+"\n"+"性别="+mysex+"\n"+"班级="+mypro+"\n"+"部系="+mydep+"\n");
//                    }
//                }
//                else
//                {
//                    Toast.makeText(NewInComeActivity.this,"没有查询到该学号的信息",Toast.LENGTH_SHORT).show();
//                    ed_time.setText("");
//                }
//            }
//        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewInComeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}