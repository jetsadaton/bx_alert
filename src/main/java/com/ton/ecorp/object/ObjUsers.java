package com.ton.ecorp.object;


public class ObjUsers {
    public String is_user_name;
    public String is_password;
    public String is_pattern;
    public float if_alert_id;

    public ObjUsers(String username , String pattern )
    {
        is_user_name = username;
        is_pattern = pattern;
    }

    public String getIs_user_name() {
        return is_user_name;
    }

    public void setIs_user_name(String is_user_name) {
        this.is_user_name = is_user_name;
    }

    public String getIs_password() {
        return is_password;
    }

    public void setIs_password(String is_password) {
        this.is_password = is_password;
    }

    public float getIf_alert_id() {
        return if_alert_id;
    }

    public void setIf_alert_id(float if_alert_id) {
        this.if_alert_id = if_alert_id;
    }

    public String getIs_pattern() {
        return is_pattern;
    }

    public void setIs_pattern(String is_pattern) {
        this.is_pattern = is_pattern;
    }
}
