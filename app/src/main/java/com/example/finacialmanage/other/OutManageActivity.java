package com.example.finacialmanage.other;

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
import com.example.finacialmanage.activity.PayDetailMainActivity;
import com.example.finacialmanage.bean.OutpayBean;
import com.example.finacialmanage.db.MyDBHelper;

public class OutManageActivity extends AppCompatActivity {

    private EditText edMoneyOutmag;
    private EditText edTimeOutmag;
    private Spinner spTypeOutmag;
    private EditText edPayerOutmag;
    private EditText edRemarkOutmag;
    private Button btModifyOutmag;
    private Button btDeleteOutmag;
    private MyDBHelper mhelper;
    private SQLiteDatabase db;
    private OutpayBean outpayBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_manage);
        // 绑定控件
        edMoneyOutmag = findViewById(R.id.ed_money_outmag);
        edTimeOutmag = findViewById(R.id.ed_time_outmag);
        spTypeOutmag = findViewById(R.id.sp_type_outmag);
        edPayerOutmag = findViewById(R.id.ed_payer_outmag);
        edRemarkOutmag = findViewById(R.id.ed_remark_outmag);
        btModifyOutmag = findViewById(R.id.bt_modify_outmag);
        btDeleteOutmag = findViewById(R.id.bt_delete_outmag);
        mhelper = new MyDBHelper(OutManageActivity.this);
        db = mhelper.getWritableDatabase();

        getDataDisplay();
        bntModify();
        bntdelete();
    }
    private void getDataDisplay() {
        outpayBean = (OutpayBean)getIntent().getSerializableExtra("sero");
        edMoneyOutmag.setText(outpayBean.getMoney()+"");
        edTimeOutmag.setText(outpayBean.getTime());
        if (spTypeOutmag.equals("电影-娱乐")) {
            spTypeOutmag.setSelection(1);
        } else if (spTypeOutmag.equals("美食-畅饮")) {
            spTypeOutmag.setSelection(2);
        } else if (spTypeOutmag.equals("欢乐-购物")) {
            spTypeOutmag.setSelection(3);
        } else if (spTypeOutmag.equals("手机-充值")) {
            spTypeOutmag.setSelection(4);
        } else if (spTypeOutmag.equals("交通-出行")) {
            spTypeOutmag.setSelection(5);
        } else if (spTypeOutmag.equals("教育-培训")) {
            spTypeOutmag.setSelection(6);
        } else if (spTypeOutmag.equals("社交-礼仪")) {
            spTypeOutmag.setSelection(7);
        } else if (spTypeOutmag.equals("生活-日用")) {
            spTypeOutmag.setSelection(8);
        }else if (spTypeOutmag.equals("其他")) {
            spTypeOutmag.setSelection(9);
        }
        else {
            spTypeOutmag.setSelection(0);
        }
        edPayerOutmag.setText(outpayBean.getPayer());
        edRemarkOutmag.setText(outpayBean.getRemark());
    }
    private void bntModify() {
        btModifyOutmag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("outmoney",edMoneyOutmag.getText().toString());
                values.put("outtime",edTimeOutmag.getText().toString());
                values.put("outtype",spTypeOutmag.getSelectedItem().toString());
                values.put("outpayee",edPayerOutmag.getText().toString());
                values.put("outremark",edRemarkOutmag.getText().toString());
                db.update("out_come",values,"id=?",new String[]{outpayBean.getId()+""});
                Toast.makeText(OutManageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OutManageActivity.this, PayDetailMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void bntdelete() {
        btDeleteOutmag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("out_come","id=?",new String[]{outpayBean.getId()+""});
                Toast.makeText(OutManageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OutManageActivity.this, PayDetailMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}