package com.example.group6_schoolkit.Cal;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.taskCrud.TaskModel;

import java.util.ArrayList;
import java.util.List;

//2
public class CalendarAdapterRecycler extends RecyclerView.Adapter<CalendarAdapterRecycler.ViewHolder_>{
//3 add data
    ArrayList<String> daysInMonth;
    String month;
    List<TaskModel> tasks;

    public CalendarAdapterRecycler(ArrayList<String> daysInMonth, String month, List<TaskModel> tasks) {
        this.daysInMonth = daysInMonth;
        this.month=month;
        this.tasks=tasks;
    }
//6
    //4 constructor
    @NonNull
    @Override
    public ViewHolder_ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cal_layout_cell, parent, false);
        //setting up the size of the cells in calendar view
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        ViewHolder_ holder = new ViewHolder_(view);

        return holder;
    }
//7
    @Override
    public void onBindViewHolder(@NonNull ViewHolder_ holder, int position) {
        holder.textView.setText(daysInMonth.get(position));
//        System.out.println(tasks.size());
        for(int i=0;i< tasks.size();i++){
            String dueDate = tasks.get(i).getDueDate();
            String[] parts = dueDate.split("-");
            String[] partsForMonth = month.split(" ");

            String year = parts[0];   // "2023"
            String month2 = parts[1];  // "04"
            String day = parts[2];    // "23"
            String monthFromDB = partsForMonth[0];

//            System.out.println(month2+monthFromDB);
            if(month2.equals("01") && monthFromDB.equals("January")){
//                System.out.println("APRIL TEST OK " + daysInMonth.size());
                colorGrey(day,  position,  holder);
            } else if (month2.equals("02") && monthFromDB.equals("February")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("03") && monthFromDB.equals("March")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("04") && monthFromDB.equals("April")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("05") && monthFromDB.equals("May")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("06") && monthFromDB.equals("June")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("07") && monthFromDB.equals("July")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("08") && monthFromDB.equals("August")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("09") && monthFromDB.equals("September")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("10") && monthFromDB.equals("October")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("11") && monthFromDB.equals("November")) {
                colorGrey(day,  position,  holder);
            }else if (month2.equals("12") && monthFromDB.equals("December")) {
                colorGrey(day,  position,  holder);
            }
            else{
                System.out.println("APRIL TEST NOT OK");
            }




        }
    }
//5
    @Override
    public int getItemCount() {
        return daysInMonth.size();
    }

    //1 write innerclass holder
    public class ViewHolder_ extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder_(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.cellDayText);
        }
    }

    public void colorGrey(String day, int position, ViewHolder_ holder){
        for(int j=0;j<daysInMonth.size();j++){
//
            try{
                if(Integer.parseInt(daysInMonth.get(position))==Integer.parseInt(day)){
                    holder.textView.setBackgroundColor(Color.LTGRAY);
                    break;
                }
            }catch (Exception e){

            }

        }
    }
}
