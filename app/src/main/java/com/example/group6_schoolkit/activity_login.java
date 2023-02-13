package com.example.group6_schoolkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.group6_schoolkit.MainActivity;
import com.example.group6_schoolkit.taskCrud.HomeTaskCrud;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_LoginPage=findViewById(R.id.btn_LoginPage_Login);
        btn_LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login.this, HomeTaskCrud.class);
                startActivity(intent);
            }
        });
    }
}