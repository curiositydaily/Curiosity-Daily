package com.example.curiositydaily.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curiositydaily.R;
import com.example.curiositydaily.helper.DatabaseHelper;
import com.example.curiositydaily.model.UserLogin;
import com.example.curiositydaily.service.UserService;

public class UserRegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        Button registerUserRegister = (Button)findViewById(R.id.registerUserRegister);
        final EditText numberUserRegister = (EditText)findViewById(R.id.numberUserRegister);
        final EditText passwordUserRegister = (EditText)findViewById(R.id.passwordUserRegister);

        // 注册
        registerUserRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isEmpty()==true){System.out.println("没有完整填写信息");}
                else{System.out.println("完整填写信息");
                    String number = numberUserRegister.getText().toString();
                    String password = passwordUserRegister.getText().toString();
                    Log.i("TAG",number+"_"+password);

                    long ID = createUserLogin(number,password);
                    if(ID >= 0 ){
                        // 注册成功跳转到登录界面
                        Toast.makeText(UserRegisterActivity.this,"注册成功请登录",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UserRegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        // 注册失败
                        Toast.makeText(UserRegisterActivity.this,"注册失败请重试",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private long createUserLogin(String number,String password){
        long id = db.createUser(number,password);
        return id;
    }

    public boolean isEmpty(){
        EditText number = findViewById(R.id.numberUserRegister);
        EditText password = findViewById(R.id.passwordUserRegister);
        if(TextUtils.isEmpty(number.getText()) || TextUtils.isEmpty(password.getText())){
            Toast.makeText(UserRegisterActivity.this,"请完整填写",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
