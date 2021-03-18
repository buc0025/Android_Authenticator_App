package com.example.authenticatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ToDoList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TODO_TABLE = "todo_table";
    private static final String USER_TABLE = "user_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER = "user";
    private static final String COLUMN_TASK = "task";
    private static final String COLUMN_STATUS = "status";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void addToDo(ToDoModel toDoModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USER, toDoModel.getUser());
        contentValues.put(COLUMN_TASK, toDoModel.getTask());
        contentValues.put(COLUMN_STATUS, toDoModel.getStatus());

        long result = database.insert(TODO_TABLE, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean tableExists(String tableName) {
        SQLiteDatabase database = this.getWritableDatabase();

        String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'";
        try (Cursor cursor = database.rawQuery(query, null)) {
            if(cursor!=null) {
                if(cursor.getCount()>0) {
                    return true;
                }
            }
            return false;
        }
    }

    Cursor readAllTasks(String user) {
//    Cursor readAllTasks() {
//        String query = "SELECT * FROM " + TODO_TABLE + " WHERE user LIKE " + user;
        String query = "SELECT * FROM " + TODO_TABLE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public List<ToDoModel> getAllTasks(String user) {
//    public List<ToDoModel> getAllTasks() {
        Cursor cursor = readAllTasks(user);
//        Cursor cursor = readAllTasks();

        List<ToDoModel> toDoModelList = new ArrayList<>(cursor.getCount());

        while (cursor.moveToNext()) {
            ToDoModel toDo = new ToDoModel(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3));

            toDoModelList.add(toDo);
        }
        return toDoModelList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TODO_TABLE + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USER + " VARCHAR, " +
                        COLUMN_TASK + " VARCHAR, " +
                        COLUMN_STATUS + " INTEGER);";
        db.execSQL(query);

//        String queryUser =
//                "CREATE TABLE " + USER_TABLE + " (" +
//                        COLUMN_USER + " VARCHAR, " +
//                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        COLUMN_TASK + " VARCHAR, " +
//                        COLUMN_STATUS + " INTEGER);";
//        db.execSQL(queryUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
