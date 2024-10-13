package com.example.finacialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finacialmanage.R;
import com.example.finacialmanage.adapter.IncomeAdapter;
import com.example.finacialmanage.bean.IncomeBean;
import com.example.finacialmanage.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class IncomeDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<IncomeBean> arr1 = new ArrayList();
    TextView total_in_detail;
    private MyDBHelper mHelper;
    private SQLiteDatabase db;
    double total_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);
        recyclerView = findViewById(R.id.recy_list);
        mHelper = new MyDBHelper(IncomeDetailActivity.this);
        total_in_detail = findViewById(R.id.totalmoney_in_detail);
        db = mHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from in_come",null);
        while(cursor.moveToNext())
        {
            @SuppressLint("Range") int myid = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") double mymoney = cursor.getDouble(cursor.getColumnIndex("inmoney"));
            @SuppressLint("Range") String mytime = cursor.getString(cursor.getColumnIndex("intime"));
            @SuppressLint("Range") String mytype = cursor.getString(cursor.getColumnIndex("intype"));
            @SuppressLint("Range") String mypayer = cursor.getString(cursor.getColumnIndex("inplayer"));
            @SuppressLint("Range") String myremark = cursor.getString(cursor.getColumnIndex("inremark"));
            total_money+=mymoney;
            IncomeBean incomeBean = new IncomeBean(myid,mymoney,mytime,mytype,mypayer,myremark);
            arr1.add(incomeBean);
        }
        IncomeAdapter adapter = new IncomeAdapter(arr1,IncomeDetailActivity.this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        total_in_detail.setText(total_money+"");
    }
}