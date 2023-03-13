package com.example.group6_schoolkit.Course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.CourseModel;
import com.example.group6_schoolkit.Utils.DataBaseHelper;
import com.example.group6_schoolkit.taskCrud.AllTasksActivity;
import com.example.group6_schoolkit.taskCrud.EditTaskActivity;

public class CourseActivity extends AppCompatActivity {
    TextView textView;
    EditText textView2,textView8, textView9,textView10;
    int courseId;
    int courseNo;
    String courseName;
    String courseDesc;
    String prof;
    int taskId;
    Button buttonCreateCourse;
    private DataBaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        myDB = new DataBaseHelper(CourseActivity.this);
        textView = findViewById(R.id.txtViewCourse);
        textView2=findViewById(R.id.textView2);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
        textView10=findViewById(R.id.textView10);
        buttonCreateCourse=findViewById(R.id.buttonCreateCourse);
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



        buttonCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int courseNo = Integer.parseInt(textView2.getText().toString());
                myDB.updateCourse(courseId, new CourseModel(courseNo,textView8.getText().toString(), textView9.getText().toString(), textView10.getText().toString(),taskId ));
                myDB.updateTask2(taskId,textView8.getText().toString() );
            }
        });

    }
}