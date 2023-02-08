package com.example.group6_schoolkit.taskCrud;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group6_schoolkit.R;

import java.util.ArrayList;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private ArrayList<TaskModel> tasks = new ArrayList<>();

    private Context mContext;

    public TasksRecyclerViewAdapter(Context mContext){
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(tasks.get(position).getTitle());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "task is clickd", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, TaskActivity.class);
                intent.putExtra("TITLE", tasks.get(position).getTitle());
                intent.putExtra("DESC", tasks.get(position).getDescription());
                intent.putExtra("OWNER", tasks.get(position).getOwner());
                mContext.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(tasks.get(position).getOwner());
        holder.txtDescription.setText(tasks.get(position).getDescription());

        if(tasks.get(position).getExpanded()){
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        }else{
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setBooks(ArrayList<TaskModel> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelativeLayout;
        private TextView txtAuthor, txtDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent=itemView.findViewById(R.id.parent);
            imgBook=itemView.findViewById(R.id.imageId);
            txtName=itemView.findViewById(R.id.txtBookName);

            downArrow=itemView.findViewById(R.id.btnDownArrow);
            upArrow=itemView.findViewById(R.id.btnUpArrow);
            expandedRelativeLayout=itemView.findViewById(R.id.expandedRelLayout);
            txtAuthor=itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TaskModel task = tasks.get(getAdapterPosition());
                    task.setExpanded(!task.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TaskModel task = tasks.get(getAdapterPosition());
                    task.setExpanded(!task.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
