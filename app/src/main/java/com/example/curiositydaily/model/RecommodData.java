package com.example.curiositydaily.model;

import android.media.Image;

public class RecommodData {
    private String title;
    private String writer;
    private int img;
    private String content;
    public RecommodData(String title,String writer,String content,int img)
    {
        this.title = title;
        this.writer = writer;
        this.img = img;
        this.content = content;
//        this.img = img;
    }

    public String getTitle(){
        return title;
    }
    public String getWriter(){
        return writer;
    }
    public int getImg(){
        return img;
    }
    public String getContent(){
        return content;
    }
    public void setImg(int img){
        this.img = img;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setWriter(String writer){
        this.writer = writer;
    }

    public void setContent(String content){
        this.content = content;
    }
}
