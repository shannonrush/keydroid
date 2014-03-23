package com.rushdevo.keydroid.app.db;


import android.database.sqlite.SQLiteOpenHelper;

public class KeydroidDatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_USERS = "users";
    public static final String TABLE_CONTACTS = "contacts";

    private static final String DATABASE_NAME = "keydroid.db";
    private static final int DATABASE_VERSION = 1;

    public KeydroidDatabaseHelper(android.content.Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
