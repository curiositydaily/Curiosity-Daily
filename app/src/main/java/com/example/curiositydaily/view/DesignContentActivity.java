package com.example.curiositydaily.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.curiositydaily.R;
import com.example.curiositydaily.app.AppController;
import com.example.curiositydaily.model.SQLiteDB;
import com.example.curiositydaily.model.UserDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DesignContentActivity extends AppCompatActivity {

    private int design_id;
    private List<UserDesign> userDesignsList = new ArrayList<UserDesign>();
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_content);

        TextView name = (TextView)findViewById(R.id.name);
        TextView type = (TextView)findViewById(R.id.type);
        TextView introduction = (TextView)findViewById(R.id.introduction);
        TextView commendation = (TextView)findViewById(R.id.commendation);
        NetworkImageView image = (NetworkImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        String getDesignId = (String) intent.getSerializableExtra("design_id");
        design_id = Integer.valueOf(getDesignId) + 1;

        // 获取设计专题信息
        userDesignsList = SQLiteDB.getInstance(getApplicationContext()).loadUserDesign(design_id);
        for( UserDesign ud : userDesignsList){
            System.out.println(ud.toString());
            name.setText(ud.getName());
            image.setImageUrl(ud.getImage(),imageLoader);
            if(ud.getType()==0){ type.setText("头像");}
            else{type.setText("壁纸"); }
            introduction.setText(ud.getIntroduction());
            commendation.setText(String.valueOf(ud.getCommendation()));

        }
    }

}
