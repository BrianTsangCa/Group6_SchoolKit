package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;

public class AddTaskActivity extends AppCompatActivity {
    private EditText title,desc, due, category, course,owner,comment;
    private Button btn_createTask,btn_Cancel;
    private Spinner spinner_CreatePage_importance;
    private DataBaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title=findViewById(R.id.EditTxt_CreatePage_title);
        desc = findViewById(R.id.EditTxt_CreatePage_description);
        due = findViewById(R.id.EditTxt_CreatePage_duedate);
        spinner_CreatePage_importance=findViewById(R.id.spinner_CreatePage_importance);
        category=findViewById(R.id.EditTxt_CreatePage_category);
        course=findViewById(R.id.EditTxt_CreatePage_course);
        owner=findViewById(R.id.EditTxt_CreatePage_Owner);
        comment=findViewById(R.id.EditTxt_CreatePage_comment);
        btn_createTask=findViewById(R.id.btn_CreateTask);

        btn_createTask.setOnClickListener((View view)-> {
            if(title.getText().toString().length()==0){
                Toast.makeText(AddTaskActivity.this,"Please enter title",Toast.LENGTH_SHORT).show();
            }else if(desc.getText().toString().length()==00){
                Toast.makeText(AddTaskActivity.this,"Please enter description",Toast.LENGTH_SHORT).show();
            }else if(due.getText().toString().length()==0){
                Toast.makeText(AddTaskActivity.this,"Please enter due date",Toast.LENGTH_SHORT).show();
            }else if(category.getText().toString().length()==0){
                Toast.makeText(AddTaskActivity.this,"Please enter category",Toast.LENGTH_SHORT).show();
            }else if(course.getText().toString().length()==0){
                Toast.makeText(AddTaskActivity.this,"Please enter course",Toast.LENGTH_SHORT).show();
            }else if(owner.getText().toString().length()==0){
                Toast.makeText(AddTaskActivity.this,"Please enter Owner",Toast.LENGTH_SHORT).show();
            }else if( comment.getText().toString().length()==0){
                Toast.makeText(AddTaskActivity.this,"Please enter comment",Toast.LENGTH_SHORT).show();
            }else{
                myDB = new DataBaseHelper(AddTaskActivity.this);
                String importance="";
                if(spinner_CreatePage_importance.getSelectedItemPosition()==0){
                    importance="Low";
                }else if (spinner_CreatePage_importance.getSelectedItemPosition()==1){
                    importance="Medium";
                }else{
                    importance="High";
                }
                myDB.insertTask(new TaskModel(title.getText().toString(),desc.getText().toString(), due.getText().toString(), importance, category.getText().toString(), course.getText().toString(),owner.getText().toString(), comment.getText().toString(),1));
                startActivity(new Intent(AddTaskActivity.this,HomeTaskCrud.class));
            }
        });
        btn_Cancel=findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener((View v)-> {
            startActivity(new Intent(AddTaskActivity.this,HomeTaskCrud.class));
        });

    }
}