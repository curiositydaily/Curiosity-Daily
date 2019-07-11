package com.example.curiositydaily.model;

import java.io.Serializable;

public class UserLogin implements Serializable {
    private int id;
    private String number;
    private String password;
    private String phonenumber;
    public UserLogin(){
        super();
    }
    public UserLogin(String number,String password,String phonenumber){
        super();
        this.number = number;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    // 获取登录信息
    public int getId(){ return id; }
    public String getNumber(){ return number; }
    public String getPassword(){ return password; }
    public String getPhonenumber(){ return phonenumber; }

    // 设置登录信息
    public void setId(int id){ this.id = id;}
    public void setNumber(String number){ this.number = number;}
    public void setPassword(String password){ this.password = password; }
    public void setPhonenumber(String phonenumber){ this.phonenumber = phonenumber;}

    @Override
    public String toString(){
        return "UserLogin [id=" + id
                + ",number="+number
                + ",password="+password
                + ",password="+password
                + ",phonenumber="+phonenumber+"]";
    }
}
