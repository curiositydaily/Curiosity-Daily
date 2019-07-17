package com.example.curiositydaily.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.curiositydaily.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.curiositydaily.model.UserArticle.TITLE;
import static com.example.curiositydaily.model.UserInfo.ID;

public class SQLiteDB {
    public static final String DB_NAME = "curiosity_db";
    public static final int DB_VERSION = 1;
    private static SQLiteDB sqliteDB;
    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    public SQLiteDB(Context context){
        this.context = context;
        init();

    }
    private void init() {
        dbHelper = new DatabaseHelper(context,DB_NAME,null, DB_VERSION);
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
            System.out.println("-----------------2-------"+userInfo.toString());
            try {
                System.out.println(userInfo.getImage().toString());
                System.out.println(userInfo.getName().toString());
                System.out.println(userInfo.getIntroduction().toString());
                db.execSQL("insert into user_info(image,name,introduction) values(?,?,?)",
                        new String[]{userInfo.getImage().toString(),userInfo.getName().toString(),userInfo.getIntroduction().toString()});
                System.out.println("---------3---------------"+userInfo.toString());
                return true;
            }catch (Exception e){
                Log.d("保存用户个人信息错误",e.getMessage().toString());
            }
        }
        return false;
    }

    // 查询用户个人信息，通过user_login的id来对应查找，返回登录对象
    //    一个id对应一个登陆账号&用户信息
    public UserInfo queryUserInfo(int id){
        Cursor cursor = db.rawQuery("select * from user_info where id=?",
                new String[]{String.valueOf(id)});
//        Cursor cursor = db.query("user_info", null, ID+"=?",new String[]{"id"}, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()) {
            UserInfo userInfo = new UserInfo();
            do {
                userInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userInfo.setName(cursor.getString(cursor.getColumnIndex("name")));
                userInfo.setImage(cursor.getString(cursor.getColumnIndex("image")));
                userInfo.setIntroduction(cursor.getString(cursor.getColumnIndex("introduction")));
            } while (cursor.moveToNext());
            return userInfo;
        }
        return null;
    }


    /* user_design 设计专题表 */
    // 读取设计专题表信息
    public List<UserDesign> loadUserDesign(int design_id) {
        List<UserDesign> list = new ArrayList<UserDesign>();
        Cursor cursor = db.rawQuery("select * from user_design where id =?",new String[]{String.valueOf(design_id)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                UserDesign userDesign = new UserDesign();
                userDesign.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userDesign.setName(cursor.getString(cursor.getColumnIndex("name")));
                userDesign.setType(cursor.getInt(cursor.getColumnIndex("type")));
                userDesign.setImage(cursor.getString(cursor.getColumnIndex("image")));
                userDesign.setIntroduction(cursor.getString(cursor.getColumnIndex("introduction")));
                userDesign.setCommendation(cursor.getInt(cursor.getColumnIndex("commendation")));
                list.add(userDesign);
            } while (cursor.moveToNext());
        }
        return list;
    }

    // 插入设计专题表信息
    public boolean saveUserDesign(UserDesign userDesign){
        if(userDesign != null) {
            try {
                db.execSQL("insert into user_design(name,image,type,introduction,commendation) values(?,?,?,?,?)",
                        new String[]{userDesign.getName().toString(), userDesign.getImage(),String.valueOf(userDesign.getType()), userDesign.getIntroduction(), String.valueOf(userDesign.getCommendation())});
                return true;
            } catch (Exception e) {
                Log.d("插入专题表信息错误", e.getMessage().toString());
            }
            return false;
        }
        return false;
    }

    /* design_content 专题内容表 */
    // 读取专题内容表信息
    public List<DesignContent> loadDesignContent(){
        List<DesignContent> list = new ArrayList<DesignContent>();
        Cursor cursor = db.query("design_content",null,null,null,null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                DesignContent designContent =  new DesignContent();
                designContent.setId(cursor.getInt(cursor.getColumnIndex("id")));
                designContent.setDesignId(cursor.getInt(cursor.getColumnIndex("design_id")));
                designContent.setImageUrl(cursor.getString(cursor.getColumnIndex("image_url")));
                list.add(designContent);
            }while(cursor.moveToNext());
        }
        return list;
    }

    // 插入专题内容信息
    public boolean saveDesignContent(DesignContent designContent){
        if(designContent != null){
            Cursor cursor = db.rawQuery("select * from user_design where id=?",
                    new String[]{String.valueOf(designContent.getId())});
            if(cursor.getCount() > 0){
                return false;
            }else{
                try {
                    db.execSQL("insert into design_content(design_id,image_url) values(?,?)",
                            new String[]{String.valueOf(designContent.getDesignId()),designContent.getImageurl().toString()});
                }catch (Exception e){
                    Log.d("插入专题内容信息错误",e.getMessage().toString());
                }
                return true;
            }
        }
        return false;
    }

    /* user_article 发布文章表 */
    // 读取发布文章表信息
    public List<UserArticle> loadUserArticle(){
        List<UserArticle> list = new ArrayList<UserArticle>();
        Cursor cursor = db.query("user_article",null,null,null,null,null,null);
//        Cursor cursor = db.rawQuery("select * from user_article",null);
        if(cursor!=null && cursor.moveToFirst()){
            do{
                UserArticle userArticle =  new UserArticle();
                userArticle.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userArticle.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
                userArticle.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                userArticle.setContent(cursor.getString(cursor.getColumnIndex("content")));
                userArticle.setCommendation(cursor.getInt(cursor.getColumnIndex("commendation")));
                list.add(userArticle);
            }while(cursor.moveToNext());
        }
        return list;
    }
    // 插入文章信息
    public boolean saveUserArticle(UserArticle userArticle){
        try {
            db.execSQL("insert into user_article(id,user_id,title,content,commendation) values(?,?,?,?,?)",
                    new String[]{String.valueOf(userArticle.getId()),String.valueOf(userArticle.getUserId()),userArticle.getTitle().toString(),
                            userArticle.getContent(),String.valueOf(userArticle.getCommendation())});
            return true;
        }catch (Exception e){
            Log.d("插入文章信息错误",e.getMessage().toString());
        }
        return false;
    }

    // 依据标题查找文章内容，返回该对象
    public UserArticle getContentByTitle(String title){
        UserArticle userArticle = new UserArticle();
        Cursor cursor = db.query("user_article", null, TITLE+"=?",new String[]{"title"}, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()) {
            UserInfo userInfo = new UserInfo();
            do {
                userArticle.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userArticle.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
                userArticle.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                userArticle.setContent(cursor.getString(cursor.getColumnIndex("content")));
                userArticle.setCommendation(cursor.getInt(cursor.getColumnIndex("commendation")));
            } while (cursor.moveToNext());
            return userArticle;
        }
        return null;
    }
    public UserArticle queryArticleInfo(int id){
        Cursor cursor = db.rawQuery("select * from user_article where id=?",
                new String[]{String.valueOf(id)});
//        Cursor cursor = db.query("user_info", null, ID+"=?",new String[]{"id"}, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()) {
            UserArticle userArticle = new UserArticle();
            do {
                userArticle.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userArticle.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                userArticle.setContent(cursor.getString(cursor.getColumnIndex("content")));
                userArticle.setCommendation(cursor.getInt(cursor.getColumnIndex("commendation")));
                userArticle.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            } while (cursor.moveToNext());
            return userArticle;
        }
        return null;
    }
    /* user_attention 关注表 */
    // 读取关注表信息
    public List<UserAttention> loadUserAttention(){
        List<UserAttention> list = new ArrayList<UserAttention>();
        Cursor cursor = db.query("user_attention",null,null,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            do{
                UserAttention userAttention = new UserAttention();
                userAttention.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userAttention.setAttentionId(cursor.getInt(cursor.getColumnIndex("user_id")));
                list.add(userAttention);
            }while (cursor.moveToNext());
        }
        return list;
    }
    /*搜索历史*/
    public List<String> queryData(String tempName) {
        List<String> data = new ArrayList<>();
        //模糊搜索
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);

        while (cursor.moveToNext()) {
            //注意这里的name跟建表的name统一
            String name = cursor.getString(cursor.getColumnIndex("name"));
            data.add(name);
        }
        cursor.close();
        return data;

    }

    /**
     * 检查数据库中是否已经有该条记录
     *
     * @param tempName
     * @return
     */
    public boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 插入数据
     *
     * @param tempName
     */
    public void insertData(String tempName) {
        db = dbHelper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 插入数据
     *
     * @param name
     * @return
     */

    public int delete(String name) {
        // 获取数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 执行SQL
        int delete = db.delete("records", " name=?", new String[]{name});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    /**
     * 清空数据
     */
    public void deleteData() {
        db = dbHelper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

}
