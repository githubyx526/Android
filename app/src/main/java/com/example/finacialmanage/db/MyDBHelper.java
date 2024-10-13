package com.example.finacialmanage.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "financial.db";
    private static final String DBNAME1 = "financial1.db";
    private static final int VERSION = 1;

    public MyDBHelper(@Nullable Context context) {
        super(context, DBNAME1, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_userinfo(id Integer primary key autoincrement,name varchar(10),pwd varchar(15),email varchar(50),phone varchar(11))");
        //收入表
        db.execSQL("create table in_come(id Integer primary key autoincrement,inmoney double,intime varchar(20),intype varchar(30),inplayer varchar(100),inremark varchar(500))");
        //支出表
        db.execSQL("create table out_come(id Integer primary key autoincrement,outmoney double,outtime varchar(20),outtype varchar(30),outpayee varchar(100),outremark varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
