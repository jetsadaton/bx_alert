package com.ton.ecorp.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {
    @Id
    private String username;
    private String password;
    private String line_token;
    private String pattern;

    public Users(){}

    public Users(String user, String pass, String token, HashSet<Alert> alerts)
    {
        username = user;
        password = pass;
        line_token = token;
        pattern = "1";
        Alerts = alerts;
    }
    public Users(String user , String pass , String token )
    {
        username = user;
        password = pass;
        line_token = token;
        pattern = "1";
    }

    public String getLine_token() {
        return line_token;
    }

    public String getPassword() {
        return password;
    }

    public String getPattern() {
        return pattern;
    }

    public String getUsername() {
        return username;
    }

    public void setLine_token(String line_token) {
        this.line_token = line_token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "alert_paring", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"), inverseJoinColumns = @JoinColumn(name = "alert_id", referencedColumnName = "alert_id"))
    private Set<Alert> Alerts;

    public Set<Alert> getAlerts() {
        return Alerts;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.Alerts = alerts;
    }


//    @Override
//    public String toString() {
//        String result = String.format(
//                "Book [username=%s, line_token=%s]%n",
//                username, line_token);
//        if (Alerts != null) {
//            for(Alert a : Alerts) {
//                result += String.format(
//                        "Alert[alert_id=%d, coin_type=%s, price=%d, type=%s]%n",
//                        a.getAlert_id(), a.getCoin_type(),a.getPrice(),a.getType());
//            }
//        }
//        return result;
//    }
}
