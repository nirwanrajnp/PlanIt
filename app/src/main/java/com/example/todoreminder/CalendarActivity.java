package com.example.todoreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import static com.example.todoreminder.DetailActivity.datetv;
import static com.example.todoreminder.DetailActivity.detailtv;
import static com.example.todoreminder.DetailActivity.timetv;

public class CalendarActivity extends Activity {

    private Spinner calendarIdSpinner;
    private Hashtable<String,String> calendarIdTable;
    private Button newEventButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //Spinner gets the calendars configured by user in google calendar's app
        calendarIdSpinner = (Spinner) findViewById(R.id.calendarid_spinner);
        newEventButton = (Button) findViewById(R.id.newevent_button);

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CalendarHelper.haveCalendarReadWritePermissions(CalendarActivity.this))
                {
                    addNewEvent();
                }
                else
                {
                    CalendarHelper.requestCalendarReadWritePermission(CalendarActivity.this);
                }
            }
        });


        calendarIdSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        if (CalendarHelper.haveCalendarReadWritePermissions(this))
        {
            //Load calendars
            calendarIdTable = CalendarHelper.listCalendarId(this);

            updateCalendarIdSpinner();

        }


    }

    private void updateCalendarIdSpinner()
    {
        if (calendarIdTable==null)
        {
            return;
        }

        List<String> list = new ArrayList<String>();

        Enumeration e = calendarIdTable.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            list.add(key);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calendarIdSpinner.setAdapter(dataAdapter);
    }
    //Asks permission for giving access to calendar
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode==CalendarHelper.CALENDARHELPER_PERMISSION_REQUEST_CODE)
        {
            if (CalendarHelper.haveCalendarReadWritePermissions(this))
            {
                Toast.makeText(this, (String)"Have Calendar Read/Write Permission.",
                        Toast.LENGTH_LONG).show();

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    // Add event to calendars to app from detail activity
    private void addNewEvent() {
        if (calendarIdTable == null) {
            Toast.makeText(this, (String) "No calendars found. Please ensure at least one google account has been added.",
                    Toast.LENGTH_LONG).show();
            //Load calendars
            calendarIdTable = CalendarHelper.listCalendarId(this);

            updateCalendarIdSpinner();

            return;
        }

        Date date;
        final long oneHour = 1000 * 60 * 60;

        String dateAndTime = datetv.getText().toString() + " " + timetv.getText().toString();
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            date = dateformat2.parse(dateAndTime);
            String calendarString = calendarIdSpinner.getSelectedItem().toString();

            int calendar_id = Integer.parseInt(calendarIdTable.get(calendarString));

            long newdate = date.getTime();

            CalendarHelper.MakeNewCalendarEntry(this, detailtv.getText().toString(), "Add event", "Somewhere", newdate, newdate + oneHour, false, true, calendar_id, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    // Back to home activity
    public void backToDetails(View view) {
        Intent intent = new Intent(this, HomeActivities.class);
        startActivity(intent);
    }
}
