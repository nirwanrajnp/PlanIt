package com.example.todoreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HomeActivities extends AppCompatActivity {

    DBHelper reminderDB;

    ArrayAdapter<ListModel> adapter;
    ListView listView;
    List<ListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        reminderDB = new DBHelper(this);

        listView = (ListView) findViewById(R.id.homeItems);

        list = reminderDB.getAllReminders();
        adapter = new ArrayAdapter<ListModel>(this, R.layout.list_items, R.id.text1, list);
        listView.setAdapter(adapter);
        // clicking an item in a list will go to specific item with the help of list id
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListModel reminderList = list.get(position);
                Intent intent = new Intent(HomeActivities.this, DetailActivity.class);
                intent.putExtra("Title", reminderList);
                startActivity(intent);
            }
        });
    }
    //launches add activity
    public void launchAdd (View view1){
        Intent intent = new Intent (this, AddActivity.class);
        startActivity(intent);
    }
    //launches google calendar
    public void openCalendar(View view) {
        Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.calendar");
        startActivity(i);
    }
}

