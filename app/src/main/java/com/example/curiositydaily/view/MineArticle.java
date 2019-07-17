package com.example.curiositydaily.view;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curiositydaily.R;

import java.util.List;

public class MineArticle extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_article_user);

        Button exit = (Button)findViewById(R.id.btn_exit);
        Button exit_app = (Button)findViewById(R.id.btn_exit_app);
        Button back = (Button)findViewById(R.id.btn_back);

        //退出登录（屏蔽返回键）
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MineArticle.this,"退出登录",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MineArticle.this, MainActivity.class);
                startActivity(intent);
            }
        });

        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MineArticle.this, UserRegisterActivity.class);
                startActivity(intent);
                //@TargetApi(Build.VERSION_CODES.LOLLIPOP)

                // 1. 通过Context获取ActivityManager
                 //       ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

                // 2. 通过ActivityManager获取任务栈
                //List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();

                // 3. 逐个关闭Activity
                //for (ActivityManager.AppTask appTask : appTaskList) {
                //    appTask.finishAndRemoveTask();
                //}
                // 4. 结束进程
                // System.exit(0);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(FeedbackActivity.this,"",Toast.LENGTH_LONG).show();
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
