package com.example.todoreminder;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ListModel implements Serializable {

    boolean selected;
    // getters, setters and constructor for the list
    public ListModel(boolean selected, String reminder, String date, String time, int id) {
        this.selected = selected;
        this.reminder = reminder;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    String reminder;
    String date;
    String time;
    int id;

    public ListModel(int id, String reminder, String date, String time) {
        this.reminder = reminder;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public ListModel(String reminder, String date, String time) {
        this.selected = selected;
        this.reminder = reminder;
        this.date = date;
        this.time = time;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @NonNull
    @Override
    public String toString() {
        //info = reminder + date + time;
        return getReminder();
    }
}

