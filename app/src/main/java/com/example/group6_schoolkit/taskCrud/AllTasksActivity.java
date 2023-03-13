package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.CourseModel;
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

        if(myDB.getAllTasks().isEmpty()){
            //for testing only. creating 1 instance of task and 1 instance of course model
            TaskModel taskTest = new TaskModel("title1111","desc1", "dueDate1", "Medium", "category", "course", "owner", "comment", 1);
            myDB.insertTask(taskTest);
            CourseModel courseTest = new CourseModel(12345, taskTest.getCourse(), "MOBILE1","INSTRUCTOR", myDB.getAllTasks().get(myDB.getAllTasks().size()-1).getId());
            myDB.insertCourse(courseTest);
        }else{

        }



        //createing test course model and getting some attr from the previously created task model

        //this is to check the id of the last item
        Toast.makeText(this, "last ID is " + myDB.getAllTasks().get(myDB.getAllTasks().size()-1).getId(), Toast.LENGTH_SHORT).show();


        deleteAll=findViewById(R.id.deleteAll);
        adapter=new TasksRecyclerViewAdapter(this);
        tasksRecycle=findViewById(R.id.tasksRecycleView);
        tasksRecycle.setAdapter(adapter);
        tasksRecycle.setLayoutManager(new LinearLayoutManager(this));

//        ArrayList<TaskModel> taskList = new ArrayList<>();

//        adapter.setBooks(TaskUtil.getAllTasks());
        adapter.setBooks(myDB.getAllTasks(), myDB.getAllCourse());


        //this is for the delete all task option
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteAllTasks();
                myDB.deleteTask(1);
                myDB.deleteAllCourse();
                adapter.setBooks(myDB.getAllTasks(), myDB.getAllCourse());
            }
        });

        //testing update function
//        myDB.updateTask(21, new TaskModel("title1111_UPDATED","desc1", "dueDate1", "imp", "category", "course", "owner", "comment", 1));
//        adapter.setBooks(myDB.getAllTasks());
    }

}