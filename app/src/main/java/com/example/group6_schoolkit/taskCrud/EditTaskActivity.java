package com.example.group6_schoolkit.taskCrud;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class EditTaskActivity extends AppCompatActivity {
    private EditText title,desc, due, category, course,owner,comment;
    private Button btn_SaveChanges,btn_Delete;
    private Spinner spinner_EditPage_importance;
    private DataBaseHelper myDB;
    private Calendar selectedDate = Calendar.getInstance();
    private String email;
    private String ownerFromList;
    HashMap<String, String> userEmailList = new HashMap<>();
    ArrayList<String> users = new ArrayList<>();
    Spinner spinnerUserList;
    private DatabaseReference mDatabase;
    ArrayList<String> usersList = new ArrayList<>();

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);




        title=findViewById(R.id.EditTxt_EditPage_title);
        desc = findViewById(R.id.EditTxt_EditPage_description);
        due = findViewById(R.id.EditTxt_EditPage_duedate);
        spinnerUserList=(findViewById(R.id.spnrOwnerEditTask));
        due.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(selectedDate.getTime()));



        due = findViewById(R.id.EditTxt_EditPage_duedate);
        due.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = selectedDate.get(Calendar.YEAR);
                int month = selectedDate.get(Calendar.MONTH);
                int day = selectedDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditTaskActivity.this,
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
        spinner_EditPage_importance=findViewById(R.id.spinner_EditPage_importance);
        category=findViewById(R.id.EditTxt_EditPage_category);
        course=findViewById(R.id.EditTxt_EditPage_course);
        owner=findViewById(R.id.EditTxt_EditPage_Owner);
        comment=findViewById(R.id.EditTxt_EditPage_comment);
        btn_SaveChanges=findViewById(R.id.btn_SaveChanges);
        btn_Delete=findViewById(R.id.btn_Delete);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        owner.setText(user.getDisplayName());
        owner.setKeyListener(null);

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
                myDB.updateTask(intent.getExtras().getInt("ID"),new TaskModel(title.getText().toString(),desc.getText().toString(), due.getText().toString(), importance, category.getText().toString(), course.getText().toString(),ownerFromList, comment.getText().toString(),1, userEmailList.get(ownerFromList)));
                startActivity(new Intent(EditTaskActivity.this,HomeTaskCrud.class));
            }
        });

        btn_Delete.setOnClickListener((View v)-> {

            myDB = new DataBaseHelper(EditTaskActivity.this);
            myDB.deleteTask(intent.getExtras().getInt("ID"));
            startActivity(new Intent(EditTaskActivity.this,HomeTaskCrud.class));
        });

        //this is to get all regstered users and key-val pair of users-email to be used in spinner

        mDatabase= FirebaseDatabase.getInstance().getReference("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> userList = snapshot.getChildren();
                for (DataSnapshot x:userList
                ) {
                    System.out.println(x.child("name").getValue().toString());
                    usersList.add(x.child("name").getValue().toString());
                    userEmailList.put(x.child("name").getValue().toString(), x.child("email").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //this is to set the spinner with the user array values
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, usersList);
        spinnerUserList.setAdapter(dataAdapter);

        //this is to get the owner selected from spinner
        spinnerUserList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ownerFromList=spinnerUserList.getSelectedItem().toString();
                Toast.makeText(EditTaskActivity.this, "Selected "+ ownerFromList, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}