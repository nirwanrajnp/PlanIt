package com.example.todoreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
//Stores data into the app
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reminder_table.db";

    public static final String TABLE_NAME = "reminder_data";
    private static final int DATABASE_VERSION = 1;
    private static final String COL1 = "ID";
    //public static final String COL5 = "false";
    private static final String COL2 = "Title";
    private static final String COL3 = "Date";
    private static final String COL4 = "Time";

    private static final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_NAME + "("
                                                        + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                        //+ COL5 + " BOOL NOT NULL,"
                                                        + COL2 + " TEXT NOT NULL,"
                                                        + COL3 + " TEXT NOT NULL,"
                                                        + COL4 + " TEXT NOT NULL)";

    public DBHelper(Context context){super(context, DATABASE_NAME,null,DATABASE_VERSION);}
    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REMINDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //add data into the table
    public long addData(ListModel list) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COL5,check);
        values.put(COL2, list.getReminder());
        values.put(COL3, list.getDate());
        values.put(COL4, list.getTime());
        return db.insert(TABLE_NAME, null, values);

    }
    // get all the reminders into the list
    public List<ListModel> getAllReminders() {
        List<ListModel> reminderList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);

                ListModel list = new ListModel(id, title, date, time);
                reminderList.add(list);
            }
            while (cursor.moveToNext());
        }

        return  reminderList;
    }
    //for edit activity in the app
   public int updateData(ListModel reminderlist) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2, reminderlist.getReminder());
        values.put(COL3, reminderlist.getDate());
        values.put(COL4, reminderlist.getTime());
        return db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(reminderlist.getId())});
    }
    // for deleting a reminder from database
    public int delete(int rid) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(rid)});
    }
}
