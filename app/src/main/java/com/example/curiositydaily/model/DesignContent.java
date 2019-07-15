package com.example.curiositydaily.model;

public class DesignContent {
        public static final String TABLE_NAME = "design_content";      // 数据表名
        public static final String ID = "id";                          // 图片id
        public static final String DESIGN_ID = "design_id";            // 对应专题id
        public static final String IMAGE_URL = "image_url";                      // 图片路径

        private int id;
        private int design_id;
        private String image_url;

        // 建立数据表
        public static final String CREATE_TABLE =
                "CREATE TABLE" + TABLE_NAME + "("
                        + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + DESIGN_ID + "INTEGER,"
                        + IMAGE_URL + "TEXT,"
                        + ")";

        public DesignContent(){}
        public DesignContent(int id,int design_id,String image_url){
            super();
            this.design_id = design_id;
            this.image_url = image_url;
        }

        // 设置
        public void setId(int id){ this.id = id;}
        public void setDesignId(int design_id){ this.design_id = design_id;}
        public void setImageUrl(String image_url){ this.image_url = image_url;}

        // 获取
        public int getId(){ return id;}
        public int getDesignId(){ return design_id;}
        public String getImageurl(){ return image_url;}

        // toString
        public String toString(){
            return "DesignContent [id=" + id
                    + ",design_id="+design_id
                    + ",image_url="+image_url+"]";
        }
}
