package com.example.finacialmanage.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finacialmanage.other.InManageActivity;
import com.example.finacialmanage.R;
import com.example.finacialmanage.bean.IncomeBean;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {
    private Context mcontext;
    private List<IncomeBean> arr2;

    public IncomeAdapter(List<IncomeBean> arr2, Context context) {
        this.arr2 = arr2;
        this.mcontext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_payer, item_type, item_time, item_remark, item_money;
        View myinview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myinview = itemView;
            item_payer = itemView.findViewById(R.id.item_payer_in);
            item_type = itemView.findViewById(R.id.item_type_in);
            item_time = itemView.findViewById(R.id.item_time_in);
            item_remark = itemView.findViewById(R.id.item_remark_in);
            item_money = itemView.findViewById(R.id.item_money_in);
        }
    }

    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.ViewHolder holder, int position) {
        final IncomeBean incomeBean = arr2.get(position);
        holder.item_money.setText("+"+incomeBean.getMoney());
        holder.item_payer.setText("收款-来自"+incomeBean.getPayer());
        holder.item_time.setText(incomeBean.getTime());
        holder.item_remark.setText(incomeBean.getRemark());
        holder.item_type.setText(incomeBean.getType());
        holder.myinview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,InManageActivity.class);
                intent.putExtra("seri",incomeBean);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr2.size();
    }
}