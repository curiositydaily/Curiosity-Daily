package com.example.curiositydaily.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curiositydaily.R;

public class FeedbackActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_feedback);

        Button button1 = (Button)findViewById(R.id.btn_feedback_sub);
        Button button2 = (Button)findViewById(R.id.btn_back);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FeedbackActivity.this,"感谢您的宝贵意见！",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 2000);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(FeedbackActivity.this,"感谢您的宝贵意见！",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 500);
            }
        });

    }
}
