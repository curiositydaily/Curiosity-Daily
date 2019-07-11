package com.example.curiositydaily.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.curiositydaily.model.UserInfo;

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
        db.execSQL("create table if not exists user_info(id integer primary key autoincrement ,"
                + "image text not null ,"
                + "name text not null, "
                + "introduction text not null)");

        // 创建用户登录表user_login
        db.execSQL("create table if not exists user_login(id integer primary key autoincrement,"
                + "number text not null,"
                + "password text not null,"
                + "phonenumber text not null)");
    }

    // 更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 如果存在旧表则删除
        db.execSQL("DROP TABLE IF EXISTS " + UserInfo.TABLE_NAME);

        // 创建表
        onCreate(db);
    }

    // 获取用户信息
    public UserInfo getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UserInfo.TABLE_NAME,
                new String[]{UserInfo.ID, UserInfo.IMAGE, UserInfo.TABLE_NAME, UserInfo.INTRODUCTION},
                UserInfo.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null){ cursor.moveToFirst(); }

        // 用户对象
        UserInfo user = new UserInfo(
                cursor.getInt(cursor.getColumnIndex(UserInfo.ID)),
                cursor.getString(cursor.getColumnIndex(UserInfo.IMAGE)),
                cursor.getString(cursor.getColumnIndex(UserInfo.NAME)),
                cursor.getString(cursor.getColumnIndex(UserInfo.INTRODUCTION))
        );
        cursor.close();
        return user;
    }

    // 创建用户信息
    public int createUser(UserInfo user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // 插入数据
        return 0;
    }

    // 更新用户信息
    public int updateUser(UserInfo user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserInfo.IMAGE,user.getImage());
        values.put(UserInfo.NAME,user.getName());
        values.put(UserInfo.INTRODUCTION,user.getIntroduction());
        // 更新
        return db.update(UserInfo.TABLE_NAME,values,UserInfo.ID + "=?",
                new String[]{String.valueOf(user.getId())});
    }

    // 删除用户信息
    public void deleteUser(UserInfo user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UserInfo.TABLE_NAME, UserInfo.ID + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
