package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {
    private EditText title,desc, due, category, course,owner,comment;
    private Button btn_createTask,btn_Cancel;
    private Spinner spinner_CreatePage_importance;
    private DataBaseHelper myDB;
    private Calendar selectedDate = Calendar.getInstance();

    Spinner spinnerUsers;

    Intent intent;
    ArrayList<String> users = new ArrayList<>();
    String ownerFromList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title=findViewById(R.id.EditTxt_CreatePage_title);
        desc = findViewById(R.id.EditTxt_CreatePage_description);
        due = findViewById(R.id.EditTxt_CreatePage_duedate);
        due.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.getTime()));
        due.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = selectedDate.get(Calendar.YEAR);
                int month = selectedDate.get(Calendar.MONTH);
                int day = selectedDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // update selectedDate with the new date
                                selectedDate.set(year, monthOfYear, dayOfMonth);

                                // format the selected date using SimpleDateFormat
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String formattedDate = sdf.format(selectedDate.getTime());

                                // update the EditText or TextView with the formatted date
                                due.setText(formattedDate);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        spinner_CreatePage_importance=findViewById(R.id.spinner_CreatePage_importance);
        category=findViewById(R.id.EditTxt_CreatePage_category);
        course=findViewById(R.id.EditTxt_CreatePage_course);
        owner=findViewById(R.id.EditTxt_CreatePage_Owner);
        comment=findViewById(R.id.EditTxt_CreatePage_comment);
        btn_createTask=findViewById(R.id.btn_CreateTask);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        owner.setText(user.getDisplayName());
        owner.setKeyListener(null);

        spinnerUsers=findViewById(R.id.spinnerUsers);

        //this is to get the user array from HomeTaskCrud
        intent=getIntent();
        users=intent.getExtras().getStringArrayList("USERS");
        //this is to set the spinner with the user array values
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, users);
        spinnerUsers.setAdapter(dataAdapter);
        spinnerUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ownerFromList=spinnerUsers.getSelectedItem().toString();
                owner.setText(ownerFromList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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
                myDB.insertTask(new TaskModel(title.getText().toString(),desc.getText().toString(), due.getText().toString(), importance, category.getText().toString(), course.getText().toString(),ownerFromList, comment.getText().toString(),1));
                startActivity(new Intent(AddTaskActivity.this,HomeTaskCrud.class));
            }
        });
        btn_Cancel=findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener((View v)-> {
            startActivity(new Intent(AddTaskActivity.this,HomeTaskCrud.class));
        });



    }
}