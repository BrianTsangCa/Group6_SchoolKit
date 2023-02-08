package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.group6_schoolkit.R;

public class HomeTaskCrud extends AppCompatActivity {
    Button button, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task_crud);

        button = findViewById(R.id.btnSeeAllTasks);
        button2 = findViewById(R.id.btnAddTask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeTaskCrud.this, AllTasksActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeTaskCrud.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}