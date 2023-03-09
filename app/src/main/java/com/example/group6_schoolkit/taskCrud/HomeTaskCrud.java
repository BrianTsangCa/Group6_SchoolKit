package com.example.group6_schoolkit.taskCrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HomeTaskCrud extends AppCompatActivity {
    Button button, button2;
    TextView taskHomeTitle;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
//    ArrayList<String> sampleList = new ArrayList<>(Arrays.asList("task1", "task2", "Task3"));
    ListView listMy;
    private CalendarView calendarView;
    private Button btnSwitchView;
    private DataBaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task_crud);

        button = findViewById(R.id.btnSeeAllTasks);
        button2 = findViewById(R.id.btnAddTask);
        taskHomeTitle=findViewById(R.id.taskHomeTitle);
        firebaseAuth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        myDB = new DataBaseHelper(HomeTaskCrud.this);

        CustomAdapterForListVIew adapter = new CustomAdapterForListVIew(TaskUtil.getInstance().getAllTasks());
        listMy = findViewById(R.id.listViewHomeTaskCrud);
        listMy.setAdapter(adapter);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);
        btnSwitchView = findViewById(R.id.btnSwitchView);

        btnSwitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listMy.getVisibility() == View.VISIBLE) {
                    listMy.setVisibility(View.GONE);
                    calendarView.setVisibility(View.VISIBLE);
                } else {
                    listMy.setVisibility(View.VISIBLE);
                    calendarView.setVisibility(View.GONE);
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ArrayList<TaskModel> tasks = myDB.getTasksForDate(year, month, dayOfMonth);
                CustomAdapterForListVIew adapter = new CustomAdapterForListVIew(tasks);
                listMy.setAdapter(adapter);
                listMy.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.GONE);
            }
        });
        listMy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(HomeTaskCrud.this, AllTasksActivity.class);
                startActivity(myIntent);
            }
        });

        taskHomeTitle.setText("WELCOME "+"\n"+user.getDisplayName());



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