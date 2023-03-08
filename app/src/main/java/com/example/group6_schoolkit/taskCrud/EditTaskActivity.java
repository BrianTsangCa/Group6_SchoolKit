package com.example.group6_schoolkit.taskCrud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;

public class EditTaskActivity extends AppCompatActivity {
    private EditText title,desc, due, category, course,owner,comment;
    private Button btn_SaveChanges,btn_Delete;
    private Spinner spinner_EditPage_importance;
    private DataBaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        title=findViewById(R.id.EditTxt_EditPage_title);
        desc = findViewById(R.id.EditTxt_EditPage_description);
        due = findViewById(R.id.EditTxt_EditPage_duedate);
        spinner_EditPage_importance=findViewById(R.id.spinner_EditPage_importance);
        category=findViewById(R.id.EditTxt_EditPage_category);
        course=findViewById(R.id.EditTxt_EditPage_course);
        owner=findViewById(R.id.EditTxt_EditPage_Owner);
        comment=findViewById(R.id.EditTxt_EditPage_comment);
        btn_SaveChanges=findViewById(R.id.btn_SaveChanges);
        btn_Delete=findViewById(R.id.btn_Delete);

        //getExtra from all tasks
        Intent intent =getIntent();
        title.setText(intent.getExtras().getString("TITLE"));
        Log.d("getEx:",intent.getExtras().getString("TITLE"));
        owner.setText(intent.getExtras().getString("OWNER"));
        due.setText(intent.getExtras().getString("DATE"));

        if(intent.getExtras().getString("IMPORTANCE").equals("Low")){
            spinner_EditPage_importance.setSelection(0);
        }else if(intent.getExtras().getString("IMPORTANCE").equals("Medium")){
            spinner_EditPage_importance.setSelection(1);
        }else if(intent.getExtras().getString("IMPORTANCE").equals("High")){
            spinner_EditPage_importance.setSelection(2);
        }
        category.setText(intent.getExtras().getString("CATEGORY"));
        course.setText(intent.getExtras().getString("COURSE"));
        comment.setText(intent.getExtras().getString("COMMENT"));
        desc.setText(intent.getExtras().getString("DESCRIPTION"));

        btn_SaveChanges.setOnClickListener((View view)-> {
            if(title.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter title",Toast.LENGTH_SHORT).show();
            }else if(desc.getText().toString().length()==00){
                Toast.makeText(EditTaskActivity.this,"Please enter description",Toast.LENGTH_SHORT).show();
            }else if(due.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter due date",Toast.LENGTH_SHORT).show();
            }else if(category.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter category",Toast.LENGTH_SHORT).show();
            }else if(course.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter course",Toast.LENGTH_SHORT).show();
            }else if(owner.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter Owner",Toast.LENGTH_SHORT).show();
            }else if(comment.getText().toString().length()==0){
                Toast.makeText(EditTaskActivity.this,"Please enter comment",Toast.LENGTH_SHORT).show();
            }else{
                myDB = new DataBaseHelper(EditTaskActivity.this);
                String importance="";
                if(spinner_EditPage_importance.getSelectedItemPosition()==0){
                    importance="Low";
                }else if (spinner_EditPage_importance.getSelectedItemPosition()==1){
                    importance="Medium";
                }else{
                    importance="High";
                }
                myDB.insertTask(new TaskModel(title.getText().toString(),desc.getText().toString(), due.getText().toString(), importance, category.getText().toString(), course.getText().toString(),owner.getText().toString(), comment.getText().toString(),1));
                startActivity(new Intent(EditTaskActivity.this,HomeTaskCrud.class));
            }
        });

        btn_Delete.setOnClickListener((View v)-> {

            myDB = new DataBaseHelper(EditTaskActivity.this);
            myDB.deleteTask(intent.getExtras().getInt("ID"));
            startActivity(new Intent(EditTaskActivity.this,HomeTaskCrud.class));
        });



    }
}