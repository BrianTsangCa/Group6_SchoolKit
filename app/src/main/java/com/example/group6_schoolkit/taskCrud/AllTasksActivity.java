package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.group6_schoolkit.R;

import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity {
    private RecyclerView tasksRecycle;
    private TasksRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        adapter=new TasksRecyclerViewAdapter(this);
        tasksRecycle=findViewById(R.id.tasksRecycleView);
        tasksRecycle.setAdapter(adapter);
        tasksRecycle.setLayoutManager(new LinearLayoutManager(this));

//        ArrayList<TaskModel> taskList = new ArrayList<>();


        adapter.setBooks(TaskUtil.getInstance().getAllTasks());
    }
}