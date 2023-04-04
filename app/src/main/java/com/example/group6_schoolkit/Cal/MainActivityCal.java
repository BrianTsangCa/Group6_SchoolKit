package com.example.group6_schoolkit.Cal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.DataBaseHelper;
import com.example.group6_schoolkit.taskCrud.TaskModel;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivityCal extends AppCompatActivity {
    RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private TextView monthYearText;
    private DataBaseHelper myDB;
    List<TaskModel> allTask=new ArrayList<>();
    TaskModel taskToGet = new TaskModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_layout);

        myDB = new DataBaseHelper(MainActivityCal.this);

        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        allTask =myDB.getAllTasks();

    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

//        CalendarAdapterRecycler adapterRecycler=new CalendarAdapterRecycler(daysInMonth, monthYearFromDate(selectedDate), myDB.getAllTasks());

        CalendarAdapterRecycler adapterRecycler=new CalendarAdapterRecycler(daysInMonth, monthYearFromDate(selectedDate), myDB.getAllTasks(), new CalendarAdapterRecycler.SetonClick_() {
            @Override
            public void onClick_(int i) {
                Toast.makeText(MainActivityCal.this, daysInMonth.get(i).toString(), Toast.LENGTH_SHORT).show();
//                CalendarAdapterRecycler adapterRecycler2 = new CalendarAdapterRecycler();
//                String title=
//                adapterRecycler2.taskAndPosition.get(i).getTitle().toString();
//                Toast.makeText(MainActivityCal.this, title, Toast.LENGTH_SHORT).show();

            }


        });
        GridLayoutManager gd = new GridLayoutManager(this, 7);
        calendarRecyclerView.setLayoutManager(gd);
        calendarRecyclerView.setAdapter(adapterRecycler);

    }

    private String monthYearFromDate(LocalDate selectedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return selectedDate.format(formatter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate selectedDate) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(selectedDate);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private void initWidgets() {
        calendarRecyclerView=findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }


}