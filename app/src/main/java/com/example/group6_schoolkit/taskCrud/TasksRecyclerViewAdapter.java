package com.example.group6_schoolkit.taskCrud;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group6_schoolkit.Course.CourseActivity;
import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.Utils.CourseModel;

import java.util.ArrayList;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private ArrayList<TaskModel> tasks = new ArrayList<>();
    private ArrayList<CourseModel> courses = new ArrayList<>();

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
        holder.txtTitle.setText(tasks.get(position).getTitle());

        holder.txtAuthor.setText(tasks.get(position).getOwner());
//        holder.txtDescription.setText(tasks.get(position).getDescription());
        holder.txtDescription.setText(tasks.get(position).getCourse());
        holder.txtViewDate.setText(tasks.get(position).getDueDate());
        holder.btnImportance.setText(tasks.get(position).getImportance());

        if(holder.btnImportance.getText().toString().equals("High")){
            holder.btnImportance.setBackgroundColor(Color.RED);
        }else if(holder.btnImportance.getText().toString().equals("Medium")){
            holder.btnImportance.setBackgroundColor(Color.BLUE);
        }else if(holder.btnImportance.getText().toString().equals("Low")){
            holder.btnImportance.setBackgroundColor(Color.CYAN);
        }

        //This is for Task Details
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "task is clickd", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, EditTaskActivity.class);
                intent.putExtra("TITLE", tasks.get(position).getTitle());
                intent.putExtra("DESC", tasks.get(position).getDescription());
                intent.putExtra("OWNER", tasks.get(position).getOwner());
                intent.putExtra("DATE",tasks.get(position).getDueDate());
                intent.putExtra("IMPORTANCE",tasks.get(position).getImportance());
                intent.putExtra("CATEGORY",tasks.get(position).getCategory());
                intent.putExtra("COURSE",tasks.get(position).getCourse());
                //intent.putExtra("OWNER",tasks.get(position).getOwner());
                intent.putExtra("COMMENT",tasks.get(position).getCommentBox());
                intent.putExtra("DESCRIPTION",tasks.get(position).getDescription());
                intent.putExtra("ID", tasks.get(position).getId());
                //this will get the course information for the task
                for(int i=0;i<courses.size();i++){
                    if(courses.get(i).getCourseName().equals(tasks.get(position).getCourse())){
                        intent.putExtra("COURSENAME", courses.get(i).getCourseName());
                        intent.putExtra("COURSEID", courses.get(i).getId());
                        intent.putExtra("COURSENO", courses.get(i).getCourseNo());
                        intent.putExtra("COURSEDESC", courses.get(i).getCourseDesc());
                        intent.putExtra("INSTRUCTOR", courses.get(i).getInstructor());
                        intent.putExtra("TASKID", courses.get(i).getTaskId());
                        mContext.startActivity(intent);
                        break;
                    }else{

                    }

                }
                mContext.startActivity(intent);
            }
        });
        //when the course is clicked from all task activity, it will route to course activity with the course data
        holder.txtDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CourseActivity.class);

                for(int i=0;i<courses.size();i++){
                    if(courses.get(i).getCourseName().equals(tasks.get(position).getCourse())){
                        intent.putExtra("COURSENAME", courses.get(i).getCourseName());
                        intent.putExtra("ID", courses.get(i).getId());
                        intent.putExtra("COURSENO", courses.get(i).getCourseNo());
                        intent.putExtra("COURSEDESC", courses.get(i).getCourseDesc());
                        intent.putExtra("INSTRUCTOR", courses.get(i).getInstructor());
                        intent.putExtra("TASKID", courses.get(i).getTaskId());
                        mContext.startActivity(intent);
                        break;
                    }else{

                    }

                }
                Toast.makeText(mContext, courses.size()+" is the size of course array", Toast.LENGTH_SHORT).show();
//                Toast.makeText(mContext, "NOT FOUND "+tasks.get(position).getId(), Toast.LENGTH_SHORT).show();

//

            }
        });


     //   if(tasks.get(position).getExpanded()){
      //      holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
      //      holder.downArrow.setVisibility(View.GONE);
     //   }else{
      //      holder.expandedRelativeLayout.setVisibility(View.GONE);
      //      holder.downArrow.setVisibility(View.VISIBLE);
      //  }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setBooks(ArrayList<TaskModel> tasks, ArrayList<CourseModel> courses) {
        this.tasks = tasks;
        this.courses=courses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtTitle,txtAuthor, txtDescription;
        private TextView txtViewDate;
        private Button btnImportance;


        // private ImageView downArrow, upArrow;
      //  private RelativeLayout expandedRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent=itemView.findViewById(R.id.parent);
            imgBook=itemView.findViewById(R.id.userimage);
            txtViewDate = itemView.findViewById(R.id.textViewDate);
            txtTitle= itemView.findViewById(R.id.textViewTitle);
            txtAuthor=itemView.findViewById(R.id.textViewAuthor);
            txtDescription = itemView.findViewById(R.id.textViewDescription);
            btnImportance = itemView.findViewById(R.id.buttonImportance);





        }
    }
}
