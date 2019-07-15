package com.example.curiositydaily.model;

public class UserAttention {
    public static final String TABLE_NAME = "user_attention";   // 数据表名
    public static final String ID = "id";                // 用户id
    public static final String ATTENTION_ID = "attention_id";        // 关注的人的id

    public int id;
    public int attention_id;

    public UserAttention(){ }
    public UserAttention(int id,int attention_id ){
        super();
        this.attention_id = attention_id;
    }

    // 创建表
    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "("
            + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ATTENTION_ID + "INTEGER, "
            + ")";

    // 设置
    public void setId(int id){ this.id = id;}
    public void setAttentionId(int attention_id){ this.attention_id = attention_id;}

    // 获取
    public int getId(){ return id; }
    public int getAttentionId(){ return attention_id; }

    @Override
    public String toString(){
        return "UserLogin [id=" + id
                + ",attention_id="+attention_id+"]";
    }
}
