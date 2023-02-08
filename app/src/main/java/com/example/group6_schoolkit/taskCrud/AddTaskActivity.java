package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.group6_schoolkit.R;

public class AddTaskActivity extends AppCompatActivity {
    EditText title,desc, due, importance, category, course,owner,comment;
    Button btnSaveTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        title=findViewById(R.id.EditTextTitle);
        desc = findViewById(R.id.EditTextDesc);
        due = findViewById(R.id.EditTextDue);
        importance=findViewById(R.id.EditTextImp);
        category=findViewById(R.id.EditTextCategory);
        course=findViewById(R.id.EditTextCourse);
        owner=findViewById(R.id.EditTextOwner);
        comment=findViewById(R.id.EditTextComment);
        btnSaveTask=findViewById(R.id.btnSaveTask);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskModel taskObject = new TaskModel(
                        title.getText().toString(),
                        desc.getText().toString(),
                        due.getText().toString(),
                        importance.getText().toString(),
                        category.getText().toString(),
                        course.getText().toString(),
                        owner.getText().toString(),
                        comment.getText().toString(),
                        1,1
                );

                TaskUtil.getInstance().addTask(taskObject);
            }
        });

    }
}