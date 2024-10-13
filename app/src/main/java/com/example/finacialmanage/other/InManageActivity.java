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
import com.example.finacialmanage.activity.IncomeDetailActivity;
import com.example.finacialmanage.bean.IncomeBean;
import com.example.finacialmanage.db.MyDBHelper;

public class InManageActivity extends AppCompatActivity {

    private EditText edMoneyInmag;
    private EditText edTimeInmag;
    private Spinner spTypeInmag;
    private EditText edPayerInmag;
    private EditText edRemarkInmag;
    private Button btModifyInmag;
    private Button btDeleteInmag;
    private MyDBHelper mhelper;
    private SQLiteDatabase db;
    private IncomeBean incomeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_manage);

        // 绑定控件
        edMoneyInmag = findViewById(R.id.ed_money_inmag);
        edTimeInmag = findViewById(R.id.ed_time_inmag);
        spTypeInmag = findViewById(R.id.sp_type_inmag);
        edPayerInmag = findViewById(R.id.ed_payer_inmag);
        edRemarkInmag = findViewById(R.id.ed_remark_inmag);

        btModifyInmag = findViewById(R.id.bt_modify_inmag);
        btDeleteInmag = findViewById(R.id.bt_delete_inmag);
        mhelper = new MyDBHelper(InManageActivity.this);
        db = mhelper.getWritableDatabase();

        getDataDisplay();
        bntModify();
        bntdelete();
    }

    private void getDataDisplay() {
         incomeBean = (IncomeBean) getIntent().getSerializableExtra("seri");
         edMoneyInmag.setText(incomeBean.getMoney()+"");
         edTimeInmag.setText(incomeBean.getTime());
        if (incomeBean.getType().equals("学习-奖金")) {
            spTypeInmag.setSelection(1);
        } else if (incomeBean.getType().equals("补助-奖金")) {
            spTypeInmag.setSelection(2);
        } else if (incomeBean.getType().equals("比赛-奖励")) {
            spTypeInmag.setSelection(3);
        } else if (incomeBean.getType().equals("业余-兼职")) {
            spTypeInmag.setSelection(4);
        } else if (incomeBean.getType().equals("基本-工资")) {
            spTypeInmag.setSelection(5);
        } else if (incomeBean.getType().equals("福利-分红")) {
            spTypeInmag.setSelection(6);
        } else if (incomeBean.getType().equals("加班-津贴")) {
            spTypeInmag.setSelection(7);
        } else if (incomeBean.getType().equals("其他")) {
            spTypeInmag.setSelection(8);
        }else
        {
            spTypeInmag.setSelection(0);
        }
        edPayerInmag.setText(incomeBean.getPayer());
        edRemarkInmag.setText(incomeBean.getRemark());
    }

    private void bntModify() {
        btModifyInmag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("inmoney",edMoneyInmag.getText().toString());
                values.put("intime",edTimeInmag.getText().toString());
                values.put("intype",spTypeInmag.getSelectedItem().toString());
                values.put("inplayer",edPayerInmag.getText().toString());
                values.put("inremark",edRemarkInmag.getText().toString());
                db.update("in_come",values,"id=?",new String[]{incomeBean.getId()+""});
                Toast.makeText(InManageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InManageActivity.this, IncomeDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void bntdelete() {
        btDeleteInmag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("in_come","id=?",new String[]{incomeBean.getId()+""});
                Toast.makeText(InManageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InManageActivity.this, IncomeDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}