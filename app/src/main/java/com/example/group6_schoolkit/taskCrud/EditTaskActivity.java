package com.example.group6_schoolkit.taskCrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group6_schoolkit.R;

public class EditTaskActivity extends AppCompatActivity {
    EditText title,desc, due, importance, category, course,owner,comment;
    Button btn_createTask,btn_Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        title=findViewById(R.id.EditTxt_EditPage_title);
        desc = findViewById(R.id.EditTxt_EditPage_description);
        due = findViewById(R.id.EditTxt_EditPage_duedate);
        importance=findViewById(R.id.EditTxt_EditPage_importance);
        category=findViewById(R.id.EditTxt_EditPage_category);
        course=findViewById(R.id.EditTxt_EditPage_course);
        owner=findViewById(R.id.EditTxt_EditPage_Owner);
        comment=findViewById(R.id.EditTxt_EditPage_comment);


    }
}