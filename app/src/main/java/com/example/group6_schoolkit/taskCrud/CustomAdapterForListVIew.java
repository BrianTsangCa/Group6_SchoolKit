package com.example.group6_schoolkit.taskCrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        TextView txtview=view.findViewById(R.id.textviewforlistview);
        txtview.setText(nameLists.get(i).getDescription().toString() +"\n"+ nameLists.get(i).getTitle().toString());
        //drawable?




        return view;
    }

    public void setBooks(ArrayList<TaskModel> tasks) {
        this.nameLists = tasks;
        notifyDataSetChanged();
    }
}
