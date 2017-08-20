package com.ton.ecorp.object;


import java.text.NumberFormat;
import java.util.Locale;

public class BxCion {
    public String coin_pri;
    public String coin_sec;
    public double least_price;
    public double least_buy;
    public double least_sell;
    public float change;

    public  BxCion(String s_coin_pri , String s_coin_sec , String s_least_price , String s_least_buy , String s_least_sell , String s_change)
    {
        this.coin_pri = s_coin_pri;
        this.coin_sec = s_coin_sec;
        this.least_price = Double.parseDouble(s_least_price);
        this.least_buy = Double.parseDouble(s_least_buy);
        this.least_sell = Double.parseDouble(s_least_sell);
        this.change = Float.parseFloat(s_change);
    }
    public double getLeast_buy() {
        return least_buy;
    }

    public double getLeast_price() {
        return least_price;
    }

    public double getLeast_sell() {
        return least_sell;
    }

    public float getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = Float.parseFloat(change);
    }

    public void setLeast_buy(String least_buy) {
        this.least_buy = Double.parseDouble(least_buy);
    }

    public void setLeast_price(String least_price) {
        this.least_price = Double.parseDouble(least_price);
    }

    public void setLeast_sell(String least_sell) {
        this.least_sell = Double.parseDouble(least_sell);
    }

    public String getCoin_pri() {
        return coin_pri;
    }

    public String getCoin_sec() {
        return coin_sec;
    }

    public void setCoin_pri(String coin_pri) {
        this.coin_pri = coin_pri;
    }

    public void setCoin_sec(String coin_sec) {
        this.coin_sec = coin_sec;
    }

    @Override
    public String toString() {
        String value = coin_pri.equals("THB")?"บาท":coin_pri;
        String msg = "";
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
        msg +=  coin_pri+"/"+coin_sec + " ";
        msg += "Change : " + change + "%\n";
        msg += "ราคาล่าสุด : " + formatter.format(least_buy) + " "+value+"\n";
        msg += "ราคาชื้อ : " +  formatter.format(least_buy) + " "+value+"\n";
        msg += "ราคาขาย : " +  formatter.format(least_sell) + " "+value+"\n";
        return msg;
    }
}
