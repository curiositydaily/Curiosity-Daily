package com.example.curiositydaily.model;

import java.io.Serializable;

public class UserLogin implements Serializable {
    public static final String TABLE_NAME = "curiosity";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_PASSWORD= "password";

    public int id;
    public String number;
    public String password;

    public UserLogin(){ }
    public UserLogin(int id,String number,String password ){
        super();
        this.number = number;
        this.password = password;
    }

    // 创建表
    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "("
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NUMBER + "TEXT, "
            + COLUMN_PASSWORD + "TEXT, "
            + ")";

    // 获取登录信息
    public long getId(){ return id; }
    public String getNumber(){ return number; }
    public String getPassword(){ return password; }

    // 设置登录信息
    public void setId(int id){ this.id = id;}
    public void setNumber(String number){ this.number = number;}
    public void setPassword(String password){ this.password = password; }

    @Override
    public String toString(){
        return "UserLogin [id=" + id
                + ",number="+number
                + ",password="+password
                + ",password="+password+"]";
    }
}
