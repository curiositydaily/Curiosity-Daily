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

    // 数据库版本
    private static final int DATABASE_VERSION = 1;

    // 数据库名
    private static final String DATABSE_NAME = "curiosity_db";

    public DatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    // 创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户信息表user_info
//        db.execSQL("create table if not exists user_info(id integer primary key autoincrement ,"
//                + "image varchar(255) ,"
//                + "name varchar(255), "
//                + "introduction varchar(255))");

        // 创建用户登录表user_login
        db.execSQL(UserLogin.CREATE_TABLE);
//        db.execSQL("create table if not exists user_login(id integer primary key autoincrement,"
//                + "number varchar(30),"
//                + "password varchar(30),"
//                + "phonenumber varchar(12))");
    }

    // 创建用户登录信息
    public long createUser(String number,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserLogin.COLUMN_NUMBER,number);
        values.put(UserLogin.COLUMN_PASSWORD,password);

        // 插入数据
        long id = db.insert(UserLogin.TABLE_NAME,null,values);
        db.close();
        System.out.println("此时插入数据的对应id为"+id);
        return id;
    }

    // 查询用户登录信息
    public boolean getUserLogin(String number,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_login where number=? and password=?",
                new String[]{number,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    // 更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



//    // 更新用户信息
//    public int updateUser(UserInfo user){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(UserInfo.IMAGE,user.getImage());
//        values.put(UserInfo.NAME,user.getName());
//        values.put(UserInfo.INTRODUCTION,user.getIntroduction());
//        // 更新
//        return db.update(UserInfo.TABLE_NAME,values,UserInfo.ID + "=?",
//                new String[]{String.valueOf(user.getId())});
//    }
//
//    // 删除用户信息
//    public void deleteUser(UserInfo user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(UserInfo.TABLE_NAME, UserInfo.ID + "=?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
}
