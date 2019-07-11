package com.example.curiositydaily.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.curiositydaily.R;
import com.example.curiositydaily.helper.DatabaseHelper;
import com.example.curiositydaily.model.UserInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.user_login);

        Button login = (Button)findViewById(R.id.loginUserLogin);
        Button register = (Button)findViewById(R.id.registerUserLogin);

        // 登录按钮
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isEmpty()==true){System.out.println("11111");}
                else{System.out.println("1");
                    // 数据库查询
                    // 登录跳转到主页面
                }
            }
        });

        // 注册按钮
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 跳转注册界面
                Intent intent = new Intent(MainActivity.this,UserRegisterActivity.class);
                startActivity(intent);
            }
        });

        // 显示按钮菜单
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // 判断登录账号与登录密码是否为空
    public boolean isEmpty(){
        EditText number = findViewById(R.id.numberUserLogin);
        EditText password = findViewById(R.id.passwordUserLogin);
        if(TextUtils.isEmpty(number.getText()) && TextUtils.isEmpty(password.getText())){
            Toast.makeText(MainActivity.this,"登录账号为空，登录密码为空",Toast.LENGTH_LONG).show();;
            return true;
        }
        if(TextUtils.isEmpty(number.getText())){
            Toast.makeText(MainActivity.this,"登录账号为空",Toast.LENGTH_LONG).show();
            return true;
        }
        if(TextUtils.isEmpty(password.getText())){
            Toast.makeText(MainActivity.this,"登录密码为空",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_design);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_person);
                    return true;
            }
            return false;
        }
    };
}
