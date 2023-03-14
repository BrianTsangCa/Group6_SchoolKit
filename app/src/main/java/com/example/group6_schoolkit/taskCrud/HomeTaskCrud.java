package com.example.group6_schoolkit.taskCrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HomeTaskCrud extends AppCompatActivity {
    Button button, button2;
    TextView taskHomeTitle;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
//    ArrayList<String> sampleList = new ArrayList<>(Arrays.asList("task1", "task2", "Task3"));
    ListView listMy;
    private CalendarView calendarView;
    private Button btnSwitchView;
    private DataBaseHelper myDB;

    //Weather API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task_crud);
        //API
        TextView testWeather = findViewById(R.id.textViewTestWeather);
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver&appid=e0d951f88f25e04392121560f7ccc632";
        //Weather Api
        JsonObjectRequest jsonObjectRequestWeather = new JsonObjectRequest(weatherUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mainObject = response.getJSONObject("main");
                    String temp = mainObject.getString("temp");
                    Log.d("temp",temp);


                    // String temp = mainObject.getString("temp");

                    //String weather = temp.toString();

                    testWeather.setText(temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                testWeather.setText("Error");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequestWeather);
        //requestQueue.add(jsonObjectRequest2);
        //end of API


        button = findViewById(R.id.btnSeeAllTasks);
        button2 = findViewById(R.id.btnAddTask);
        taskHomeTitle=findViewById(R.id.taskHomeTitle);
        firebaseAuth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        myDB = new DataBaseHelper(HomeTaskCrud.this);

        CustomAdapterForListVIew adapter = new CustomAdapterForListVIew(TaskUtil.getInstance().getAllTasks());
        listMy = findViewById(R.id.listViewHomeTaskCrud);
        listMy.setAdapter(adapter);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);
        btnSwitchView = findViewById(R.id.btnSwitchView);

        btnSwitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listMy.getVisibility() == View.VISIBLE) {
                    listMy.setVisibility(View.GONE);
                    calendarView.setVisibility(View.VISIBLE);
                } else {
                    listMy.setVisibility(View.VISIBLE);
                    calendarView.setVisibility(View.GONE);
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ArrayList<TaskModel> tasks = myDB.getTasksForDate(year, month, dayOfMonth);
                CustomAdapterForListVIew adapter = new CustomAdapterForListVIew(tasks);
                listMy.setAdapter(adapter);
                listMy.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.GONE);
            }
        });

        //dire to edit task
        listMy.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Task is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeTaskCrud.this, EditTaskActivity.class);
            intent.putExtra("TITLE",adapter.nameLists.get(position).getTitle());
            intent.putExtra("DESC", adapter.nameLists.get(position).getDescription());
            intent.putExtra("OWNER", adapter.nameLists.get(position).getOwner());
            intent.putExtra("DATE",adapter.nameLists.get(position).getDueDate());
            intent.putExtra("IMPORTANCE",adapter.nameLists.get(position).getImportance());
            intent.putExtra("CATEGORY",adapter.nameLists.get(position).getCategory());
            intent.putExtra("COURSE",adapter.nameLists.get(position).getCourse());
            //intent.putExtra("OWNER",tasks.get(position).getOwner());
            intent.putExtra("COMMENT",adapter.nameLists.get(position).getCommentBox());
            intent.putExtra("DESCRIPTION",adapter.nameLists.get(position).getDescription());
            intent.putExtra("ID", adapter.nameLists.get(position).getId());
           // intent.startActivity(intent);
            startActivity(intent);
            adapter.notifyDataSetChanged();


        });

        taskHomeTitle.setText("WELCOME "+"\n"+user.getDisplayName());



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeTaskCrud.this, AllTasksActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeTaskCrud.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}