package com.example.curiositydaily.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.curiositydaily.R;

public class AttentionContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_content);
        TextView tv = (TextView)findViewById(R.id.tv_attention);
//        Intent intent = getIntent();
//        RecommodData data = (RecommodData) intent.getSerializableExtra("obj");
//        Bundle bundle  =intent.getBundleExtra("bundle");
//        String arg  =intent.getStringExtra("arg1");
//        String title = intent.getStringExtra("title");
//        StringBuilder sbld  =new StringBuilder();
//        sbld.append(arg+"\n");
//        sbld.append(title);
//        sbld.append(data.getTitle()+"\n");
//        sbld.append(data.getWriter()+"\n");
//        sbld.append(data.getContent()+"\n");
        tv.setText("选中某一关注的人的具体页面");
    }
}
