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

import com.example.finacialmanage.other.OutManageActivity;
import com.example.finacialmanage.R;
import com.example.finacialmanage.bean.OutpayBean;

import java.util.List;

public class OutpayAdapter extends RecyclerView.Adapter<OutpayAdapter.ViewHolder> {
    private Context moutcontent;
    private List<OutpayBean> arr2;

    public OutpayAdapter(Context content, List<OutpayBean> arr2) {
        this.moutcontent = content;
        this.arr2 = arr2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_payee, item_type, item_time, item_remark, item_money;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_payee = itemView.findViewById(R.id.item_payer_out);
            item_type = itemView.findViewById(R.id.item_type_out);
            item_time = itemView.findViewById(R.id.item_time_out);
            item_remark = itemView.findViewById(R.id.item_remark_out);
            item_money = itemView.findViewById(R.id.item_money_out);
        }
    }
    @NonNull
    @Override
    public OutpayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(moutcontent).inflate(R.layout.recy_item_out,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OutpayAdapter.ViewHolder holder, int position) {
        final OutpayBean outpayBean = arr2.get(position);
        holder.item_payee.setText("付款-给"+outpayBean.getPayer());
        holder.item_type.setText(outpayBean.getType());
        holder.item_time.setText(outpayBean.getTime());
        holder.item_money.setText("-"+outpayBean.getMoney());
        holder.item_remark.setText(outpayBean.getRemark());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(moutcontent,OutManageActivity.class);
                intent.putExtra("sero",outpayBean);
                moutcontent.startActivity(intent);
                ((Activity)moutcontent).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr2.size();
    }
}
