package com.example.curiositydaily.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.curiositydaily.R;
import com.example.curiositydaily.model.SQLiteDB;
import com.sackcentury.shinebuttonlib.ShineButton;

public class RecommodContentActivity extends AppCompatActivity {
    private SQLiteDB db;
    private int article_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommod_content);

        TextView tv = (TextView)findViewById(R.id.tv_recommod);
        ShineButton shineButton2 = (ShineButton) findViewById(R.id.btn_zan);
        ShineButton shineButton = (ShineButton) findViewById(R.id.btn_guanzhu);
        shineButton.init(RecommodContentActivity.this);
        shineButton2.init(RecommodContentActivity.this);
        shineButton.setOnClickListener(new MyListener());
        shineButton2.setOnClickListener(new MyListener2());

        Intent intent = getIntent();
        String id = (String) intent.getSerializableExtra("article_id");
        article_id = Integer.valueOf(id) + 1;

        tv.setText("标题："+SQLiteDB.getInstance(getApplicationContext()).queryArticleInfo(article_id).getTitle()+"\n"
        +"作者："+SQLiteDB.getInstance(getApplicationContext()).queryUserInfo(SQLiteDB.getInstance(getApplicationContext()).queryArticleInfo(article_id).getUserId()).getName()+"\n"
        +"内容："+SQLiteDB.getInstance(getApplicationContext()).queryArticleInfo(article_id).getContent()+"\n"
        +"点赞数："+SQLiteDB.getInstance(getApplicationContext()).queryArticleInfo(article_id).getCommendation());
    }
    private class MyListener implements View.OnClickListener {
        private int cnt=0;
        @Override
        public void onClick(View view) {
            cnt++;
            if(cnt%2!=0){
                System.out.println("点赞");
                Toast.makeText(RecommodContentActivity.this,"您已点赞",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(RecommodContentActivity.this,"您取消点赞",Toast.LENGTH_LONG).show();
            }

        }
    }
    private class MyListener2 implements View.OnClickListener {
        private int cnt=0;
        @Override
        public void onClick(View view) {
            cnt++;
            if(cnt%2!=0){
                System.out.println("点赞");
                Toast.makeText(RecommodContentActivity.this,"您关注了作者",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(RecommodContentActivity.this,"您取消取消关注了作者",Toast.LENGTH_LONG).show();
            }

        }
    }
}
