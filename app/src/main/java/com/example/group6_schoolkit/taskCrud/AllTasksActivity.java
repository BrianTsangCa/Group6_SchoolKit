package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;

import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity {
    private RecyclerView tasksRecycle;
    private TasksRecyclerViewAdapter adapter;
    private DataBaseHelper myDB;

    TextView deleteAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        myDB = new DataBaseHelper(AllTasksActivity.this);
        //for testing only. creating 1 instance of task
//        myDB.insertTask(new TaskModel("title1111","desc1", "dueDate1", "Medium", "category", "course", "owner", "comment", 1));
        //this is to check the id of the last item
//        Toast.makeText(this, "last ID is " + myDB.getAllTasks().get(myDB.getAllTasks().size()-1).getId(), Toast.LENGTH_SHORT).show();


        deleteAll=findViewById(R.id.deleteAll);
        adapter=new TasksRecyclerViewAdapter(this);
        tasksRecycle=findViewById(R.id.tasksRecycleView);
        tasksRecycle.setAdapter(adapter);
        tasksRecycle.setLayoutManager(new LinearLayoutManager(this));

//        ArrayList<TaskModel> taskList = new ArrayList<>();

//        adapter.setBooks(TaskUtil.getAllTasks());
        Intent intent =getIntent();

        String email=intent.getExtras().getString("EMAIL");
        String role=intent.getExtras().getString("ROLE");
        if(role.equals("User")){
            adapter.setBooks(myDB.getTasksForOneUser(email));
        }else if (role.equals("Admin")){
            adapter.setBooks(myDB.getAllTasks());
        }



        //this is for the delete all task option
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteAllTasks();
                adapter.setBooks(myDB.getAllTasks());
            }
        });



        //testing update function
//        myDB.updateTask(21, new TaskModel("title1111_UPDATED","desc1", "dueDate1", "imp", "category", "course", "owner", "comment", 1));
//        adapter.setBooks(myDB.getAllTasks());
    }

}