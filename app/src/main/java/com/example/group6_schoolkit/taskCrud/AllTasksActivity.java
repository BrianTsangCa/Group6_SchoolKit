package com.example.group6_schoolkit.taskCrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;

import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity {
    private RecyclerView tasksRecycle;
    private TasksRecyclerViewAdapter adapter;
    private DataBaseHelper myDB;

    TextView deleteAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        getSupportActionBar().hide();


        myDB = new DataBaseHelper(AllTasksActivity.this);
        //for testing only. creating 1 instance of task
//        myDB.insertTask(new TaskModel("title1111","desc1", "dueDate1", "Medium", "category", "course", "owner", "comment", 1));
        //this is to check the id of the last item
//        Toast.makeText(this, "last ID is " + myDB.getAllTasks().get(myDB.getAllTasks().size()-1).getId(), Toast.LENGTH_SHORT).show();


        deleteAll=findViewById(R.id.deleteAll);
        adapter=new TasksRecyclerViewAdapter(this);
        tasksRecycle=findViewById(R.id.tasksRecycleView);
        tasksRecycle.setAdapter(adapter);
        tasksRecycle.setLayoutManager(new LinearLayoutManager(this));

        //Search
        TextView txtSearch = findViewById(R.id.txtSearch);
        Button buttonSearch = findViewById(R.id.buttonSearch);



//        ArrayList<TaskModel> taskList = new ArrayList<>();

//        adapter.setBooks(TaskUtil.getAllTasks());
        Intent intent =getIntent();

        String email=intent.getExtras().getString("EMAIL");
        String role=intent.getExtras().getString("ROLE");
        if(role.equals("User")){
            adapter.setBooks(myDB.getTasksForOneUser(email), role);

            buttonSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(txtSearch.getText().toString().isEmpty()){



                        adapter.setBooks(myDB.getTasksForOneUser(email),role);
                    }else{
                        String inputText = txtSearch.getText().toString().trim();
                        //myDB.getTasksForSearchInput(inputText);
                        adapter.setBooks(myDB.getTasksForSearchInput(inputText));
                    }

                }
            });

        }else if (role.equals("Admin")){
            adapter.setBooks(myDB.getAllTasks(), role);

           buttonSearch.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if(txtSearch.getText().toString().isEmpty()){
                       adapter.setBooks(myDB.getAllTasks(),role);
                   }else{
                       String inputText = txtSearch.getText().toString().trim();
                       //myDB.getTasksForSearchInput(inputText);
                       adapter.setBooks(myDB.getTasksForSearchInput(inputText));
                   }
               }
           });

        }



        //this is for the delete all task option
        deleteAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AllTasksActivity.this);
                builder.setMessage("Are you sure you want to delete all tasks?")
                                .setTitle("Confirm")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                myDB.deleteAllTasks();
                                                adapter.setBooks(myDB.getAllTasks(), role);
                                            }
                                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();


            }



        });



        //testing update function
//        myDB.updateTask(21, new TaskModel("title1111_UPDATED","desc1", "dueDate1", "imp", "category", "course", "owner", "comment", 1));
//        adapter.setBooks(myDB.getAllTasks());
    }

}