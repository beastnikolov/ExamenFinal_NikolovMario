package com.beastnikolov.examenbasepractice;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataBaseManager extends SQLiteOpenHelper {
    private static final int DB_Version = 11;
    private static final String DB_Name = "itemsDB";
    private static final String TABLE_TODO = "todo_table";
    private static final String KEY_ID = "id";
    private static final String KEY_TASK = "task";
    private static final String KEY_CREATION = "creation_date";
    private static final String KEY_COMPLETION = "completion_date";
    private static final String KEY_COMPLETED = "is_completed";

    public DataBaseManager(Context context) {
            super(context,DB_Name,null,DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TASK + " TEXT,"
                + KEY_CREATION + " DATE,"
                + KEY_COMPLETION + " DATE,"
                + KEY_COMPLETED + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public Cursor getAllTasks(boolean showCompleted) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data;
        if (showCompleted) {
            data = db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE is_completed= 1 ORDER BY creation_date DESC" , null);
        } else {
            data = db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE is_completed= 0 ORDER BY creation_date DESC" , null);
        }
        return data;
    }

    public void insertTask(String taskName) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String date = sdf.format(Calendar.getInstance().getTime());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TASK, taskName);
        contentValues.put(KEY_CREATION, date);
        contentValues.put(KEY_COMPLETED, 0);
        db.insert(TABLE_TODO, null, contentValues);
        db.close();
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE ID = '" + id + "'");
        Log.d("TESTING","Deleted ID " + id);
    }

    public void deleteAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM  " + TABLE_TODO);
        Log.d("TESTING","Deleted all contacts!");
    }

    public void createDummies(Resources resources) {
        insertTask("Task Test");
        insertTask("Task Test 2");
        insertTask("Task Test 3");
        insertTask("Task Test 4");
    }

    public void updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("TESTING", "Updated: " + task.getTaskID() + " " + task.getCompleted() + " " + task.getTaskID());

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TASK, task.getTaskName());
            contentValues.put(KEY_COMPLETED, task.getCompleted());
            if (task.getCompletionDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
                String stringdate = sdf.format(task.getCompletionDate());
                contentValues.put(KEY_COMPLETION, stringdate);
            }
            db.update(TABLE_TODO, contentValues,"id='"+task.getTaskID()+"'",null);
            db.close();
        } catch (Exception e) {
            Log.d("TESTING", "UPDATE FAILED");
        }
    }




}
