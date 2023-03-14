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

import java.util.ArrayList;

//import kotlinx.coroutines.scheduling.Task;

public class CustomAdapterForListVIew extends BaseAdapter {
    ArrayList<TaskModel> nameLists = new ArrayList<>();

    public CustomAdapterForListVIew(ArrayList<TaskModel> nameLists) {
        this.nameLists = nameLists;
    }

    @Override
    public int getCount() {
        return nameLists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
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

        txtviewHomeTitle.setText(nameLists.get(i).getTitle().toString());
        txtViewHomeDate.setText(nameLists.get(i).getDueDate().toString());
        txtViewHomeDes.setText(nameLists.get(i).getDescription().toString());

        String importance = nameLists.get(i).getImportance();
        if(importance.equals("High")){
            btnHomeImportance.setBackgroundColor(Color.RED);
        }else if(importance.equals("Medium")){
            btnHomeImportance.setBackgroundColor(Color.BLUE);
        }else if(importance.equals("Low")){
            btnHomeImportance.setBackgroundColor(Color.CYAN);
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
