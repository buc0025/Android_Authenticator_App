package com.example.authenticatorapp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private Context context;
    private DatabaseManager databaseManager;
    private List<ToDoModel> toDoList;
    private MainActivity activity;

    public ToDoAdapter (DatabaseManager databaseManager, MainActivity activity) {
        this.databaseManager = databaseManager;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ToDoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        public MyViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
