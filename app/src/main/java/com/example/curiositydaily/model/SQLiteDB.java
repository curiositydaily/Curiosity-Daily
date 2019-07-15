package com.example.curiositydaily.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.example.curiositydaily.R;
import com.example.curiositydaily.helper.DatabaseHelper;
import com.example.curiositydaily.view.DesignFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.curiositydaily.model.UserInfo.ID;

public class SQLiteDB {
    public static final String DB_NAME = "curiosity_db";
    public static final int DB_VERSION = 1;
    private static SQLiteDB sqliteDB;
    private SQLiteDatabase db;
    private SQLiteDB(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context,DB_NAME,null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    // 获取SQLiteDB
    public synchronized static SQLiteDB getInstance(Context context){
        if(sqliteDB == null){
            sqliteDB = new SQLiteDB(context);
        }
        return sqliteDB;
    }

    /* user_login */
    // 保存用户登录信息
    public int saveUserLogin(UserLogin userLogin){
        if(userLogin != null){
            Cursor cursor = db.rawQuery("select * from user_login where number=?",
                    new String[]{userLogin.getNumber().toString()});
            if(cursor.getCount() > 0){
                return -1;
            }else{
                try {
                    db.execSQL("insert into user_login(number,password) values(?,?)",
                            new String[]{userLogin.getNumber().toString(),userLogin.getPassword().toString()});
                }catch (Exception e){
                    Log.d("注册错误",e.getMessage().toString());
                }
                return 1;
            }
        }
        return 0;
    }

    // 读取用户登录信息
    public List<UserLogin> loadUserLogin() {
        List<UserLogin> list = new ArrayList<UserLogin>();
        Cursor cursor = db.query("user_login", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                UserLogin userLogin = new UserLogin();
                userLogin.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userLogin.setNumber(cursor.getString(cursor.getColumnIndex("number")));
                userLogin.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                list.add(userLogin);
            } while (cursor.moveToFirst());
        }
        return list;
    }

    // 查找用户登录信息
    public int queryUserLogin(String number,String password){
        HashMap<String,String> hashmap = new HashMap<String,String>();
        Cursor cursor = db.rawQuery("select * from user_login where number=?",new String[]{number});
        if(cursor.getCount() > 0){
            Cursor passwordcursor = db.rawQuery("select * from user_login where number=? and password=?",
                    new String[]{number,password});
            if(passwordcursor.getCount() > 0 ){
                return 1;
            }else{
                return -1;
            }
        }
        return 0;
    }

    /* user_info */
    // 保存用户个人信息
    public boolean saveUserInfo(UserInfo userInfo){
        if(userInfo != null){
            try {
                db.execSQL("insert into user_info(iamge,name,introduction) values(?,?,?)",
                        new String[]{userInfo.getImage().toString(),userInfo.getName().toString(),userInfo.getIntroduction()});
            }catch (Exception e){
                Log.d("保存用户个人信息错误",e.getMessage().toString());
            }
        }
        return false;
    }

    // 查询用户个人信息，通过user_login的id来对应查找，返回登录对象
    //    一个id对应一个登陆账号&用户信息
    public UserInfo queryUserInfo(int id){
        Cursor cursor = db.query("user_info", null, ID+"=?",new String[]{"id"}, null, null, null, null);
        if (cursor.moveToFirst()) {
            UserInfo userInfo = new UserInfo();
            do {
                userInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userInfo.setName(cursor.getString(cursor.getColumnIndex("name")));
                userInfo.setImage(cursor.getString(cursor.getColumnIndex("image")));
                userInfo.setIntroduction(cursor.getString(cursor.getColumnIndex("introduction")));
            } while (cursor.moveToFirst());
            return userInfo;
        }
        return null;
    }


    /* user_design 设计专题表 */
    // 读取设计专题表信息
    public List<UserDesign> loadUserDesign() {
        List<UserDesign> list = new ArrayList<UserDesign>();
        Cursor cursor = db.query("user_design", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                UserDesign userDesign = new UserDesign();
                userDesign.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userDesign.setName(cursor.getString(cursor.getColumnIndex("name")));
                userDesign.setType(cursor.getInt(cursor.getColumnIndex("type")));
                userDesign.setIntroduction(cursor.getString(cursor.getColumnIndex("introduction")));
                userDesign.setCommendation(cursor.getInt(cursor.getColumnIndex("commendation")));
                list.add(userDesign);
            } while (cursor.moveToFirst());
        }
        return list;
    }

    /* design_content 专题内容表 */
    // 读取专题内容表信息
    public List<DesignContent> loadDesignContent(){
        List<DesignContent> list = new ArrayList<DesignContent>();
        Cursor cursor = db.query("design_content",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                DesignContent designContent =  new DesignContent();
                designContent.setId(cursor.getInt(cursor.getColumnIndex("id")));
                designContent.setDesignId(cursor.getInt(cursor.getColumnIndex("design_id")));
                designContent.setImageUrl(cursor.getString(cursor.getColumnIndex("image_url")));
                list.add(designContent);
            }while(cursor.moveToFirst());
        }
        return list;
    }
}
