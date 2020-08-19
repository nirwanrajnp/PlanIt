package com.example.todoreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static TextView detailtv, datetv, timetv;
    String detail, date, time;
    int id;
    DBHelper reminderDB;
    Button calendarbt;
    ListModel reminderList;
    List<ListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        reminderDB = new DBHelper(this);

        calendarbt = findViewById(R.id.calendar);

        detailtv = findViewById(R.id.reminderdtv);
        datetv = findViewById(R.id.datetv);
        timetv = findViewById(R.id.timetv);

        //gets data from list with help of list id
        reminderList = (ListModel) getIntent().getExtras().getSerializable("Title");
        id = reminderList.getId();
        detailtv.setText(reminderList.getReminder());
        datetv.setText(reminderList.getDate());
        timetv.setText(reminderList.getTime());
    }
    // launches edit activity
    public void launchEdit(View view) {

        Intent intent = new Intent(this, EditActivity.class);
        detail = detailtv.getText().toString();
        date = datetv.getText().toString();
        time = timetv.getText().toString();
        intent.putExtra("id", id);
        intent.putExtra("reminder", detail);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        startActivity(intent);
        finish();
    }
    // call delete function in database to delete reminders from the list
    public void deleteReminder(View view) {
        int rid = reminderList.getId();

        int result = reminderDB.delete(rid);


        if (result > 0){
            Toast.makeText(DetailActivity.this, "Reminder Deleted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivities.class));
        }
        else{
            Toast.makeText(DetailActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    //calls calendar activity
    public void saveToCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        //Toast.makeText(DetailActivity.this, "Saved To Calendar", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    // calls home activities
    public void backToHome(View view) {
        Intent intent = new Intent(this, HomeActivities.class);
        //Toast.makeText(DetailActivity.this, "Saved To Calendar", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
