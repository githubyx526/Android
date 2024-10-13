package com.example.finacialmanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.example.finacialmanage.R;
import com.example.finacialmanage.db.MyDBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAnalyseActivity extends AppCompatActivity {
    LineChart income_chart,outpay_chart;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    String []indata  = {"学习-奖金","补助-奖金","比赛-奖励","业余-兼职 ","基本-工资 ","福利-分红", "加班-津贴","其他"};
    int xxjjmoney = 0;
    int bzjjmoney = 0;
    int bsjlmoney = 0;
    int yyjzmoney = 0;
    int jbgzmoney = 0;
    int flfhmoney = 0;
    int jbjtmoney = 0;
    int qtmoney = 0;
    String []outdata  = {"电影-娱乐","美食-畅饮","欢乐-购物","手机-充值","交通-出行","教育-培训","社交-礼仪","生活-日用","其他"};
    int ddyymoney = 0;
    int msjymoney = 0;
    int ylgwmoney = 0;
    int sjczmoney = 0;
    int jtcxmoney = 0;
    int jypxmoney = 0;
    int sjlymoney = 0;
    int shrymoney = 0;
    int othermoney = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analyse);
        income_chart = findViewById(R.id.icome_chart_data);
        outpay_chart = findViewById(R.id.outpay_chart_data);
        mhelper=new MyDBHelper(DataAnalyseActivity.this);
        db = mhelper.getWritableDatabase();
        income_init();
        outpay_init();
    }

    private void income_init() {
        //获取数据
        Cursor cursor = db.rawQuery("select * from in_come", null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Double mymoney = cursor.getDouble(cursor.getColumnIndex("inmoney"));
            @SuppressLint("Range") String mytype = cursor.getString(cursor.getColumnIndex("intype"));
            if (mytype.equals("学习-奖金")) {
                xxjjmoney += mymoney;
            } else if (mytype.equals("补助-奖金")) {
                bzjjmoney += mymoney;
            } else if (mytype.equals("比赛-奖励")) {
                bsjlmoney += mymoney;
            } else if (mytype.equals("业余-兼职")) {
                yyjzmoney += mymoney;
            } else if (mytype.equals("基本-工资")) {
                jbgzmoney += mymoney;
            } else if (mytype.equals("福利-分红")) {
                flfhmoney += mymoney;
            } else if (mytype.equals("加班-津贴")) {
                jbjtmoney += mymoney;
            } else if (mytype.equals("其他")) {
                qtmoney += mymoney;
            }
        }
        //第二部分：LineChart图标初始化设置--xy轴的设置
        XAxis xAxis = income_chart.getXAxis();
        YAxis yAxisleft = income_chart.getAxisLeft();
        YAxis yAxisright = income_chart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yAxisleft.setAxisMinimum(0f);
        yAxisright.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                xAxis.setTextSize(9);
                return indata[(int)value];
            }
        });

        //第三部分：LineDataSet曲线的初始化设置
        List<Entry> intentries = new ArrayList<>();
        intentries.add(new Entry(0,xxjjmoney));
        intentries.add(new Entry(1,bzjjmoney));
        intentries.add(new Entry(2,bsjlmoney));
        intentries.add(new Entry(3,yyjzmoney));
        intentries.add(new Entry(4,jbgzmoney));
        intentries.add(new Entry(5,flfhmoney));
        intentries.add(new Entry(6,jbjtmoney));
        intentries.add(new Entry(7,qtmoney));
        LineDataSet lineDataSet = new LineDataSet(intentries,"金额");
        lineDataSet.setValueTextSize(25);
        lineDataSet.setValueTextColor(Color.WHITE);
        //第四部分：曲线展示
        lineDataSet.setDrawFilled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineData data = new LineData(lineDataSet);
        income_chart.setData(data);
    }

    private void outpay_init() {
        //指出汇中分析
        Cursor cursor = db.rawQuery("select * from out_come",null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Double outmoney = cursor.getDouble(cursor.getColumnIndex("outmoney"));
            @SuppressLint("Range") String outtype = cursor.getString(cursor.getColumnIndex("outtype"));
            if (outtype.equals("电影-娱乐")) {
                ddyymoney += outmoney;
            } else if (outtype.equals("美食-畅饮")) {
                msjymoney += outmoney;
            } else if (outtype.equals("欢乐-购物")) {
                ylgwmoney += outmoney;
            } else if (outtype.equals("手机-充值")) {
                sjczmoney += outmoney;
            } else if (outtype.equals("交通-出行")) {
                jtcxmoney += outmoney;
            } else if (outtype.equals("教育-培训")) {
                jypxmoney += outmoney;
            } else if (outtype.equals("社交-礼仪")) {
                sjlymoney += outmoney;
            } else if (outtype.equals("生活-日用")) {
                shrymoney += outmoney;
            }else if (outtype.equals("其他")) {
                othermoney += outmoney;
            }
        }
        //第二部分：LineChart图标初始化设置--xy轴的设置
        XAxis xAxis = outpay_chart.getXAxis();
        YAxis yAxisleft = outpay_chart.getAxisLeft();
        YAxis yAxisright = outpay_chart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yAxisleft.setAxisMinimum(0f);
        yAxisright.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                xAxis.setTextSize(9);
                return outdata[(int)value];
            }
        });

        //第三部分：LineDataSet曲线的初始化设置
        List<Entry> outentries = new ArrayList<>();
        outentries.add(new Entry(0,ddyymoney));
        outentries.add(new Entry(1,msjymoney));
        outentries.add(new Entry(2,ylgwmoney));
        outentries.add(new Entry(3,sjczmoney));
        outentries.add(new Entry(4,jtcxmoney));
        outentries.add(new Entry(5,jypxmoney));
        outentries.add(new Entry(6,sjlymoney));
        outentries.add(new Entry(7,shrymoney));
        outentries.add(new Entry(8,othermoney));
        LineDataSet lineDataSet = new LineDataSet(outentries,"金额");
        lineDataSet.setValueTextSize(25);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineData data = new LineData(lineDataSet);
        outpay_chart.setData(data);
    }
}