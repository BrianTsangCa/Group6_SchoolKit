package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class HomeTaskCrud extends AppCompatActivity {
    Button button, button2;
//    ArrayList<String> sampleList = new ArrayList<>(Arrays.asList("task1", "task2", "Task3"));
    ListView listMy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task_crud);

        button = findViewById(R.id.btnSeeAllTasks);
        button2 = findViewById(R.id.btnAddTask);


        CustomAdapterForListVIew adapter = new CustomAdapterForListVIew(TaskUtil.getInstance().getAllTasks());
        listMy = findViewById(R.id.listViewHomeTaskCrud);
        listMy.setAdapter(adapter);

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