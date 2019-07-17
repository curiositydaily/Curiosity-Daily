package com.example.curiositydaily.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import com.example.curiositydaily.model.UserInfo;
import com.example.curiositydaily.model.UserLogin;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "curiosity_db";
    // 建立用户登录表语句
    public static final String CREATE_USER_LOGIN = "create table user_login(id integer primary key autoincrement,number text,password text)";
    // 建立用户信息表语句
    public static final String CREATE_USER_INFO = "create table user_info(id integer primary key autoincrement,image text,name text,introduction text)";
    // 建立设计专题表
    public static final String CREATE_USER_DESIGN = "create table user_design(id integer primary key autoincrement,name text,type integer,image text,introduction text,commendation integer)";
    // 建立专题内容表
    public static final String CREATE_DESIGN_CONTENT = "create table design_content(id integer primary key autoincrement,design_id integer,image_url text)";
    // 建立发布文章表
    public static final String CREATE_USER_ARTICLE = "create table user_article(id integer primary key autoincrement,user_id integer,title text,content text,commendation integer)";
    // 建立关注表
    public static final String CREATE_USER_ATTENTION = "create table user_attention(id integer primary key autoincrement,user_id integer)";
    //创建搜索历史表
    public static final String CREATE_SEARCH_HISTORY = "create table records(id integer primary key autoincrement,name text)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, null, version);
    }

    // 创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户登录表user_login
        db.execSQL(CREATE_USER_LOGIN);
        // 创建用户信息表user_info
        db.execSQL(CREATE_USER_INFO);
        // 创建设计专题表
        db.execSQL(CREATE_USER_DESIGN);
        // 创建专题内容表design_content
        db.execSQL(CREATE_DESIGN_CONTENT);
        // 创建发布文章表user_article
        db.execSQL(CREATE_USER_ARTICLE);
        // 创建关注表user_attention
        db.execSQL(CREATE_USER_ATTENTION);
        db.execSQL(CREATE_SEARCH_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // 删除数据库
    public boolean deleteDatabase(Context context){
        return context.deleteDatabase(DB_NAME);
    }
}
