package com.example.curiositydaily.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.curiositydaily.helper.DatabaseHelper;
import com.example.curiositydaily.model.UserLogin;

public class UserService {
    private static DatabaseHelper dbHelper;
//    public UserService(Context context){
//        dbHelper = new DatabaseHelper(context);
//    }
//
//    // 登录
//    public static boolean login(String number,String password){
//        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
//        Cursor cursor = sdb.rawQuery("select * from user_login where number=? and password=?",
//                new String[]{number,password});
//        if(cursor.moveToFirst()==true){
//            cursor.close();
//            return true;
//        }
//        return false;
//    }
//
//    // 注册
//    public static boolean register(String number, String password, String phonenumber){
//        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
////        Object obj[]={userLogin.getNumber(),userLogin.getPassword(),userLogin.getPhonenumber()};
//        sdb.execSQL("insert into user_login(number,password,phonenumber) values(number,password,phonenumber)");
//        return true;
//    }
}
