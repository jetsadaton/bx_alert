package com.ton.ecorp.object;

public class Alert {
    public float id;
    public float price;
    public String type;
    public boolean one_time_flag;

    public Alert(){}
    public Alert(float id ,float price , String type , boolean one_time)
    {
        this.id = id;
        this.price = price;
        this.type = type;
        this.one_time_flag = one_time;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOne_time_flag() {
        return one_time_flag;
    }

    public void setOne_time_flag(boolean one_time_flag) {
        this.one_time_flag = one_time_flag;
    }

    @Override
    public String toString() {
        return  "ID :" + this.id + ", Price:" + this.price +", Type:" + this.type + ", OneTimeFlag:" + one_time_flag;
    }
}
