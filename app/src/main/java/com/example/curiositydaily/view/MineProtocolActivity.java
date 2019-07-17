package com.example.curiositydaily.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curiositydaily.R;

public class MineProtocolActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_protocol);

        Button back = (Button)findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 500);
            }
        });
        /*
        //返回键
        Button btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 跳转mine
                //Intent intent = new Intent(.this,MainActivity.class);
                startActivity(intent);
            }
        });
        * */

    }
}
