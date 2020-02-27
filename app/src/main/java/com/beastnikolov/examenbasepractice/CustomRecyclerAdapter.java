package com.beastnikolov.examenbasepractice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyHolder> {
    ArrayList<Task> taskArrayList;
    Context context;

    public CustomRecyclerAdapter(ArrayList<Task> taskArrayList, Context context) {
        this.taskArrayList = taskArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerAdapter.MyHolder holder, int position) {
        ImageUtilities imageUtilities = new ImageUtilities();

        final Task tempTask = taskArrayList.get(position);
        holder.taskName.setText(tempTask.getTaskName());
        holder.taskDate.setText(String.valueOf(tempTask.getTaskCreation()));
        if (tempTask.getCompleted() == 0) {
            holder.taskComplete.setChecked(false);
        } else {
            holder.taskComplete.setChecked(true);
        }
       /* holder.contactIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,tempTask.getContactName(),Toast.LENGTH_SHORT).show();
            }
        });
        */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ContactInfoActivity.class);
                intent.putExtra("task", tempTask);
               // intent.putExtra("id",tempTask.getContactID());
                Toast.makeText(context,"SELECTED ID: " + tempTask.getTaskName() + " " + tempTask.getTaskCreation(),Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView taskName;
        TextView taskDate;
        CheckBox taskComplete;
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.textView_taskName);
            taskDate = itemView.findViewById(R.id.textView_taskDate);
            taskComplete = itemView.findViewById(R.id.checkBox_completion);
            this.itemView = itemView;
        }
    }
}


