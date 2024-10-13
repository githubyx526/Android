package com.example.finacialmanage.bean;

import java.io.Serializable;

public class IncomeBean implements Serializable {
    private int id;
    private double money;
    private String time;
    private String type;
    private String payer;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public IncomeBean(int id, double money, String time, String type, String payer, String remark) {
        this.id = id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.payer = payer;
        this.remark = remark;
    }
}
