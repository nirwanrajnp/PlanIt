package com.example.todoreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    DBHelper reminderDB;

    Button datebt, timebt, locationbt, savebt;
    TextView datetv, timetv, locationtv, latAndLon;
    EditText rm;
    Boolean check;
    String rst, dst, tst, locationst, latandlonst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        reminderDB = new DBHelper(this);

        datebt = findViewById(R.id.button1);
        timebt = findViewById(R.id.button2);
        locationbt = findViewById(R.id.button3);
        savebt = findViewById(R.id.savebt);
        datetv = findViewById(R.id.datetv);
        timetv = findViewById(R.id.timetv);
        locationtv = findViewById(R.id.locationtv);
        rm = findViewById(R.id.addtext);
        latAndLon = findViewById(R.id.latandlon);

        /*locationst = getIntent().getExtras().getString("locationst");
        latandlonst = getIntent().getExtras().getString("latAndLonst");

        locationtv.setText(locationst);
        latAndLon.setText(latandlonst);*/

        datebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        timebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });
    }
    //For DatePicker
    private void handleDateButton(){

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
    //For saving the reminder to database
    public void saveProcess(View view) {
        switch (view.getId()){
            case R.id.savebt:

                rst = rm.getText().toString();
                dst = datetv.getText().toString();
                tst = timetv.getText().toString();

                ListModel list = new ListModel(rst,dst,tst);

                long result = reminderDB.addData(list);

                if (result != -1){
                    Toast.makeText(AddActivity.this, "Reminder Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, HomeActivities.class));
                }
                else{
                    Toast.makeText(AddActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //For time picker
    private void handleTimeButton(){
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

    //Intent to go back to home page
    public void backToHome(View view) {
        Intent intent = new Intent(this, HomeActivities.class);
        startActivity(intent);
    }
    //Intent to open location activity
    public void launchMap(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }
}
