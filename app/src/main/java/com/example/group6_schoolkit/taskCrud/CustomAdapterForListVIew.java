package com.example.group6_schoolkit.taskCrud;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.group6_schoolkit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import kotlinx.coroutines.scheduling.Task;

public class CustomAdapterForListVIew extends BaseAdapter {
    ArrayList<TaskModel> nameLists = new ArrayList<>();
    private int dueDayInYear;

    public CustomAdapterForListVIew(ArrayList<TaskModel> nameLists) {
        this.nameLists = nameLists;
    }

    @Override
    public int getCount() {
        return nameLists.size();
    }

    @Override
    public Object getItem(int i) {
        return nameLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //inflate
        if(view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.taskitemviewforlistview, viewGroup, false);
        }
        //set text view
        TextView txtviewHomeTitle = view.findViewById(R.id.textViewHomeTitle);
        TextView txtViewHomeDate = view.findViewById(R.id.textViewHomeDate);
        TextView txtViewHomeDes = view.findViewById(R.id.textViewHomeDes);
        Button btnHomeImportance = view.findViewById(R.id.buttonHomeImportance);


        //TimerTask------
        Calendar todayCalendar = Calendar.getInstance();
        int today = todayCalendar.get(Calendar.DAY_OF_YEAR);

        //parse the string 3-06-2023 to DAY_OF_YEAR
        String dueDay = nameLists.get(i).getDueDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dueDate = format.parse(dueDay);
            Calendar dueCalendar = Calendar.getInstance();
            dueCalendar.setTime(dueDate);
            dueDayInYear = dueCalendar.get(Calendar.DAY_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btnHomeImportance.setText(String.valueOf(dueDayInYear-today)+" Days");

        //End of timer task


        txtviewHomeTitle.setText(nameLists.get(i).getTitle().toString());
        txtViewHomeDate.setText(nameLists.get(i).getDueDate().toString());
        txtViewHomeDes.setText(nameLists.get(i).getDescription().toString());

        String importance = nameLists.get(i).getImportance();
        if(importance.equals("High")){
            btnHomeImportance.setBackgroundColor(Color.RED);
        }else if(importance.equals("Medium")){
            btnHomeImportance.setBackgroundColor(Color.BLUE);
        }else if(importance.equals("Low")){
            btnHomeImportance.setBackgroundColor(Color.DKGRAY);
        }
        //drawable?
        //put into inttent



        return view;
    }

    public void setBooks(ArrayList<TaskModel> tasks) {
        this.nameLists = tasks;
        notifyDataSetChanged();
    }
}
