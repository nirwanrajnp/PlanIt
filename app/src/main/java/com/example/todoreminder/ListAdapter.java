/*package com.example.todoreminder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<ListModel> {

    private LayoutInflater layoutInflater;
    private ArrayList<ListModel> reminder;
    private int mViewResourseId;

    public ListAdapter(Context context, int textViewResourceId, ArrayList<ListModel> reminder){
        super(context,textViewResourceId,reminder);
        this.reminder = reminder;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mViewResourseId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = layoutInflater.inflate(mViewResourseId, null);

        ListModel reminders = reminder.get(position);

        if (reminders != null) {
            CheckBox box = (CheckBox) convertView.findViewById(R.id.box);
            TextView rm = (TextView) convertView.findViewById(R.id.text1);
            TextView date = (TextView) convertView.findViewById(R.id.text2);
            TextView time = (TextView) convertView.findViewById(R.id.text3);
            if (reminder.get(position).isSelected()) {
                box.setSelected(false);
            }
            if (rm != null) {
                rm.setText(reminders.getReminder());
            }
            if (date != null) {
                date.setText((reminders.getDate()));
            }
            if (time != null) {
                time.setText((reminders.getTime()));
            }
        }

        return convertView;

    }
}*/