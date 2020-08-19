package com.example.todoreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    TextView datetv, timetv;
    EditText detailtv;
    String detail, date,time;
    DBHelper reminderDB;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        reminderDB = new DBHelper(this);

        detailtv = findViewById(R.id.addtext);
        datetv = findViewById(R.id.datetv);
        timetv = findViewById(R.id.timetv);
        // gets details of reminders from details activity
        detail = getIntent().getExtras().getString("reminder");
        date = getIntent().getExtras().getString("date");
        time = getIntent().getExtras().getString("time");
        id = getIntent().getExtras().getInt("id");

        detailtv.setText(detail);
        datetv.setText(date);
        timetv.setText(time);
    }
    // launches time picker
    public void launchTime(View view) {
        Calendar calendar = Calendar.getInstance();

        int hr = calendar.get(Calendar.HOUR);
        int mn = calendar.get(Calendar.MINUTE);

        boolean tf = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hr, int mn) {
                String timeSt = hr+":"+mn;
                timetv.setText(timeSt);
            }
        }, hr, mn, tf);

        timePickerDialog.show();
    }
    // launches date picker
    public void launchDate(View view) {
        Calendar calendar = Calendar.getInstance();

        int yr = calendar.get(Calendar.YEAR);
        int mt = calendar.get(Calendar.MONTH);
        int dt = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yr, int mt, int dt) {
                int mt1 = mt+1;
                String dateSt = dt + "/" + mt1 + "/" + yr;
                datetv.setText(dateSt);

            }
        },yr,mt,dt);

        datePickerDialog.show();
    }
    // goes back to home activity
    public void launchDetail(View view) {
        Intent intent = new Intent(this, HomeActivities.class);
        startActivity(intent);
    }
    // calls the database to update a reminder
    public void updateReminder(View view) {
                //id = reminderlist.getId();
                String reminderst = detailtv.getText().toString();
                String datest = datetv.getText().toString();
                String timest = timetv.getText().toString();

                ListModel reminderlist = new ListModel(id,reminderst,datest,timest);

                int result = reminderDB.updateData(reminderlist);

                if (result > 0){
                    Toast.makeText(EditActivity.this, "Reminder Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, HomeActivities.class));
                }
                else{
                    Toast.makeText(EditActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
    }
}
