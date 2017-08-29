package com.ton.ecorp.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int alert_id;
    private double price;
    private String type;
    private String coin_type;
    private String calcel_flag;

    public  Alert() {
    }

    public  Alert( double price , String type , String coin)
    {
        this.price = price;
        this.type = type;
        this.coin_type = coin;
        this.calcel_flag = "F";
    }

    public  Alert(double price , String type , String coin , Set<Users>  user)
    {
        this.price = price;
        this.type = type;
        this.coin_type = coin;
        this.calcel_flag = "F";
        users = user;
    }


    public void setCalcel_flag(String calcel_flag) {
        this.calcel_flag = calcel_flag;
    }

    public String getCalcel_flag() {
        return calcel_flag;
    }

    public int getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(int alert_id) {
        this.alert_id = alert_id;
    }

    public double getPrice() {
        return price;
    }

    public String getCoin_type() {
        return coin_type;
    }

    public String getType() {
        return type;
    }

    public void setCoin_type(String coin_type) {
        this.coin_type = coin_type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToMany(mappedBy = "Alerts")
    private Set<Users> users;

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> user) {
        this.users = user;
    }
}
