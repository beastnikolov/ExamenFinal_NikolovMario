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

public class DataBaseManager extends SQLiteOpenHelper {
    private static final int DB_Version = 1;
    private static final String DB_Name = "contactsDB";
    private static final String TABLE_CONTACTS = "contacts_table";
    private static final String KEY_ID = "id";
    private static final String KEY_PICTURE = "picture";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public DataBaseManager(Context context) {
            super(context,DB_Name,null,DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PICTURE + " BLOB,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS , null);
        return data;
    }

    public void insertContact(byte[] image, String contactName, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PICTURE, image);
        contentValues.put(KEY_NAME, contactName);
        contentValues.put(KEY_PHONE, phoneNumber);
        db.insert(TABLE_CONTACTS, null, contentValues);
        db.close();
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS + " WHERE ID = '" + id + "'");
        Log.d("TESTING","Deleted ID " + id);
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM  " + TABLE_CONTACTS);
        Log.d("TESTING","Deleted all contacts!");
    }

    public void createDummies(Resources resources) {
        ImageUtilities imageUtilities = new ImageUtilities();
        Bitmap icon = BitmapFactory.decodeResource(resources, R.drawable.contact);
        byte[] imagetest = imageUtilities.getBitMapByteArray(icon);
        insertContact(imagetest,"Mario","692622437");
        insertContact(imagetest,"Pere","698543437");
        insertContact(imagetest,"Joan","405964046");
        insertContact(imagetest,"Cabba","569549544");
    }




}
