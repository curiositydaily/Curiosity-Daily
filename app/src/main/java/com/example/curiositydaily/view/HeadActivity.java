package com.example.curiositydaily.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curiositydaily.R;

public class HeadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_info);

        Button sub = (Button)findViewById(R.id.btn_info_sub);
        Button back = (Button)findViewById(R.id.btn_back);
        Button exit = (Button)findViewById(R.id.btn_exit);

        final EditText name = (EditText)findViewById(R.id.et_mine_info_name);
        final EditText sign = (EditText)findViewById(R.id.et_mine_info_sign);

        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String s1 = name.getText().toString();
                String s2 = sign.getText().toString();

                String input = getResources().getString(R.string.user_name);
                //String output = String.format(input,s1);

                //String.format(input, String.format(input, s1));

                /*
                //String number = tv_mine_info_name.getText().toString();
                //String password = passwordUserRegister.getText().toString();
                UserLogin userLogin = new UserLogin();
                userLogin.setNumber(name);
                userLogin.setPassword(sign);

                int resUserRegister = SQLiteDB.getInstance(getApplicationContext()).saveUserLogin(userLogin);
                Intent intent = new Intent(HeadActivity.this,MainActivity.class);
                startActivity(intent);
                * */

                Toast.makeText(HeadActivity.this,"已申请修改！",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 2000);
            }
        });

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

        //退出登录（屏蔽返回键）
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HeadActivity.this,"退出登录",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(HeadActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /*
        @Override
        public boolean onKeyDown(int keyCode,KeyEvent event){
            if(keyCode==KeyEvent.KEYCODE_BACK)
                return true;
            return super.onKeyDown(keyCode, event);
        }//屏蔽返回键
        */
    }

}
