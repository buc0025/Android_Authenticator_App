package com.example.authenticatorapp;

public class ToDoModel {
    private int id, status;
    private String user, task;

    public ToDoModel(int id, String user, String task, int status) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.task = task;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
