package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.group6_schoolkit.R;

public class AddTaskActivity extends AppCompatActivity {
    EditText title,desc, due, importance, category, course,owner,comment;
    Button btn_createTask,btn_Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title=findViewById(R.id.EditTxt_EditPage_title);
        desc = findViewById(R.id.EditTxt_EditPage_description);
        due = findViewById(R.id.EditTxt_EditPage_duedate);
        importance=findViewById(R.id.EditTxt_EditPage_importance);
        category=findViewById(R.id.EditTxt_EditPage_category);
        course=findViewById(R.id.EditTxt_EditPage_course);
        owner=findViewById(R.id.EditTxt_EditPage_Owner);
        comment=findViewById(R.id.EditTxt_EditPage_comment);
        btn_createTask=findViewById(R.id.btn_SaveChanges);

        btn_createTask.setOnClickListener((View view)-> {
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
            startActivity(new Intent(AddTaskActivity.this,HomeTaskCrud.class));
        });
        btn_Cancel=findViewById(R.id.btn_Delete);
        btn_Cancel.setOnClickListener((View v)-> {
            startActivity(new Intent(AddTaskActivity.this,HomeTaskCrud.class));
        });

    }
}