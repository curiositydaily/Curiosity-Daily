package com.example.curiositydaily.model;

public class UserInfo {
    public static final String TABLE_NAME = "user_info";
    public static final String ID = "id";
    public static final String IMAGE = "image";
    public static final String NAME = "name";
    public static final String INTRODUCTION = "introduction";

    private int id;
    private String image;
    private String name;
    private String introduction;

    // 建立数据表
    public static final String CREATE_TABLE =
            "CREATE TABLE" + TABLE_NAME + "("
                + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + IMAGE + "TEXT,"
                + NAME + "TEXT,"
                + INTRODUCTION + "TEXT)";

    public UserInfo(){}
    public UserInfo(int id,String image,String name,String introduction){
        super();
        this.image = image;
        this.name = name;
        this.introduction = introduction;
    }

    // 设置
    public void setId(int id){ this.id = id;}
    public void setImage(String image){ this.image = image;}
    public void setName(String Name){ this.name = name;}
    public void setIntroduction(String introduction){ this.introduction = introduction;}

    // 获取
    public int getId(){ return id;}
    public String getImage(){ return image;}
    public String getName(){ return name;}
    public String getIntroduction(){ return introduction;}

    // toString
    public String toString(){
        return "UserLogin [id=" + id
                + ",image="+image
                + ",name="+name
                + ",introduction="+introduction+"]";
    }

}
