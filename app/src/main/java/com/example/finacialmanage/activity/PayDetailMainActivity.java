package com.example.finacialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finacialmanage.adapter.OutpayAdapter;
import com.example.finacialmanage.R;
import com.example.finacialmanage.bean.OutpayBean;
import com.example.finacialmanage.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class PayDetailMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<OutpayBean> arr1 = new ArrayList();
    private MyDBHelper mHelper;
    private SQLiteDatabase db;
    TextView total_out_detail;
    double total_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detail_main);
        recyclerView = findViewById(R.id.recy_list_out);
        mHelper = new MyDBHelper(PayDetailMainActivity.this);
        db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from out_come",null);
        while (cursor.moveToNext())
        {
            @SuppressLint("Range") int myid = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") double mymoney = cursor.getDouble(cursor.getColumnIndex("outmoney"));
            @SuppressLint("Range") String mytime = cursor.getString(cursor.getColumnIndex("outtime"));
            @SuppressLint("Range") String mytype = cursor.getString(cursor.getColumnIndex("outtype"));
            @SuppressLint("Range") String mypayee = cursor.getString(cursor.getColumnIndex("outpayee"));
            @SuppressLint("Range") String myremark = cursor.getString(cursor.getColumnIndex("outremark"));
            total_money+=mymoney;
            OutpayBean outpayBean = new OutpayBean(myid,mymoney,mytime,mytype,mypayee,myremark);
            arr1.add(outpayBean);
        }
        OutpayAdapter outpayAdapter = new OutpayAdapter(PayDetailMainActivity.this,arr1);
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(outpayAdapter);
        total_out_detail.setText(total_money+"");
    }
}