package com.example.curiositydaily.model;

import android.media.Image;

public class PeopleData {
    private String name;
    private String info;
    private int img;
    public PeopleData(String name,String info,int img)
    {
        this.name = name;
        this.info = info;
        this.img = img;

    }

    public String getName(){
        return name;
    }
    public String getInfo(){
        return info;
    }
    public int getImg(){
        return img;
    }

    public void setImg(int img){
        this.img = img;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setInfo(String info){
        this.info = info;
    }

}
