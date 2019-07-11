package com.example.curiositydaily.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.curiositydaily.helper.DatabaseHelper;

public class UserLoginService {
    private DatabaseHelper db;
    public UserLoginService(Context context){
        db = new DatabaseHelper(context);
    }

    public boolean login(String number,String password){
        SQLiteDatabase sdb = db.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from user where number =? and password=? and phonenumber =?",
                new String[]{number,password});
        if(cursor.moveToFirst() == true){
            cursor.close();
            return true;
        }
        return false;
    }

}
