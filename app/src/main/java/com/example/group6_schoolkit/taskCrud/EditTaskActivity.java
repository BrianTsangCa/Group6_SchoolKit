package com.example.group6_schoolkit.taskCrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group6_schoolkit.R;

public class EditTaskActivity extends AppCompatActivity {
    EditText title,desc, due, importance, category, course,owner,comment;
    Button btn_SaveChanges,btn_Delete;
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
        btn_SaveChanges=findViewById(R.id.btn_SaveChanges);
        btn_Delete=findViewById(R.id.btn_Delete);
        btn_SaveChanges.setOnClickListener((View view)-> {
            if(title.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter title",Toast.LENGTH_SHORT).show();
            }else if(desc.getText().toString().length()==00){
                Toast.makeText(EditTaskActivity.this,"Please enter description",Toast.LENGTH_SHORT).show();
            }else if(due.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter due date",Toast.LENGTH_SHORT).show();
            }else if(importance.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter importance",Toast.LENGTH_SHORT).show();
            }else if(category.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter category",Toast.LENGTH_SHORT).show();
            }else if(course.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter course",Toast.LENGTH_SHORT).show();
            }else if(owner.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter Owner",Toast.LENGTH_SHORT).show();
            }else if( comment.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter comment",Toast.LENGTH_SHORT).show();
            }else{
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
                startActivity(new Intent(EditTaskActivity.this,HomeTaskCrud.class));
            }
        });

    }
}