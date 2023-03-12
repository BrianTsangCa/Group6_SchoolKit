package com.example.group6_schoolkit.Course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.group6_schoolkit.R;

public class CourseActivity extends AppCompatActivity {
    TextView textView;
    int courseId;
    int courseNo;
    String courseName;
    String courseDesc;
    String prof;
    int taskId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        textView = findViewById(R.id.txtViewCourse);
        Intent intent = getIntent();

        courseId = intent.getExtras().getInt("ID");
        courseNo = intent.getExtras().getInt("COURSENO");
        courseName = intent.getExtras().getString("COURSENAME");
        courseDesc = intent.getExtras().getString("COURSEDESC");
        prof = intent.getExtras().getString("INSTRUCTOR");
        taskId = intent.getExtras().getInt("TASKID");

        textView.setText(courseId+"\n"+
                courseNo+"\n"+
                courseName+"\n"+
                courseDesc+"\n"+
                prof+"\n"+
                taskId);

    }
}