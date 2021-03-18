package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView passedIntent;
    private TextView noTasks;
    private TextView tableExists;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseManager databaseManager;
    private List<ToDoModel> toDoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noTasks = findViewById(R.id.noTasksTextView);
        tableExists = findViewById(R.id.tableExists);
        databaseManager = new DatabaseManager(this);
        Intent intent = getIntent();
//        passedIntent = findViewById(R.id.passedIntent);
        String user = intent.getExtras().getString("user");
//        passedIntent.setText(user);

//        if (databaseManager.tableExists("todo_table")) {
//            tableExists.setVisibility(View.VISIBLE);
//        } else {
//            noTasks.setVisibility(View.VISIBLE);
//        }

        databaseManager.tableExists("todo_table");
        if (databaseManager.getAllTasks(user).size() == 0) {
            noTasks.setVisibility(View.VISIBLE);
        }



//        recyclerView = findViewById(R.id.tasksRecyclerView);
//        layoutManager = new LinearLayoutManager(this);
//        adapter = new ToDoAdapter(databaseManager, user);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
    }

    // onClick created for logout button in main xml
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); // log out
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logOut) {
            FirebaseAuth.getInstance().signOut(); // log out
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}