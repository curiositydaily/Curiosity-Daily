package com.example.curiositydaily.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curiositydaily.R;

import java.util.UUID;

public class DesignContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_content);

        Intent intent = getIntent();
        String demo = (String) intent.getSerializableExtra("data");
        TextView test = (TextView)findViewById(R.id.test);
        test.setText(demo);

    }

}
