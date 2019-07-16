package com.example.curiositydaily.model;

public class UserDesign {
    public static final String TABLE_NAME = "user_design";      // 数据表名
    public static final String ID = "id";                       // 专题id
    public static final String NAME = "name";                   // 专题名
    public static final String IMAGE = "image";                 // 专题封面
    public static final String TYPE = "type";                   // 专题类型
    public static final String INTRODUCTION = "introduction";   // 专题介绍
    public static final String COMMENDATION = "commendation";   // 专题点赞数

    private int id;
    private String name;
    private String image;
    private int type;
    private String introduction;
    private int commendation;

    // 建立数据表
    public static final String CREATE_TABLE =
            "CREATE TABLE" + TABLE_NAME + "("
                    + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NAME + "TEXT,"
                    + IMAGE + "TEXT,"
                    + TYPE + "INTEGER,"
                    + INTRODUCTION + "TEXT"
                    + COMMENDATION + "INTEGER,"
                    + ")";

    public UserDesign(){}
    public UserDesign(int id,String name,String image,int type,String introduction,int commendation){
        super();
        this.name = name;
        this.image = image;
        this.type = type;
        this.introduction = introduction;
        this.commendation = commendation;
    }

    // 设置
    public void setId(int id){ this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setImage(String image){ this.image = image;}
    public void setType(int type){ this.type  =type;}
    public void setIntroduction(String introduction){ this.introduction = introduction;}
    public void setCommendation(int commendation){ this.commendation = commendation;}

    // 获取
    public int getId(){ return id;}
    public String getName(){ return name;}
    public String getImage(){ return image;}
    public int getType(){ return type;}
    public String getIntroduction(){ return introduction;}
    public int getCommendation(){ return commendation;}

    // toString
    public String toString(){
        return "UserDesign [id=" + id
                + ",name="+name
                + ",image="+image
                + ",type="+type
                + ",introduction="+introduction
                + ",commendation="+commendation+"]";
    }
}
