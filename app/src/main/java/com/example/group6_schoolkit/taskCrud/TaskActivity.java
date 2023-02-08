package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.group6_schoolkit.R;

public class TaskActivity extends AppCompatActivity {
    TextView txtView3, txtView4, txtView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        txtView3=findViewById(R.id.textView3);
        txtView4=findViewById(R.id.textView4);
        txtView5=findViewById(R.id.textView5);

        Intent intent=getIntent();
        txtView3.setText(intent.getStringExtra("TITLE"));
        txtView5.setText(intent.getStringExtra("DESC"));
        txtView4.setText(intent.getStringExtra("OWNER"));
    }
}