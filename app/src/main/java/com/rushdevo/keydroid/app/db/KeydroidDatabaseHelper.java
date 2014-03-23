package com.rushdevo.keydroid.app.db;


import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

public class KeydroidDatabaseHelper extends SQLiteOpenHelper {
    // Tables
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CONTACTS = "contacts";

    // User Fields
    private static final String USERS_KEYBASE_ID = "keybase_id";

    // Contact Fields
    private static final String CONTACTS_KEYBASE_ID = "keybase_id"; // keybase_id is the Keybase.io username
    private static final String CONTACTS_KEYDROID_ID = "keydroid_id"; // keydroid_id is the keydroid app identifier

    private static final String DATABASE_NAME = "keydroid.db";
    private static final int DATABASE_VERSION = 1;

    public KeydroidDatabaseHelper(android.content.Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        // Users Table
        StringBuilder user_sql = new StringBuilder();
        user_sql.append("CREATE TABLE " + TABLE_USERS + " (");
        user_sql.append(_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        user_sql.append(USERS_KEYBASE_ID + " TEXT");
        user_sql.append(");");
        db.execSQL(user_sql.toString());

        // Contacts Table
        StringBuilder contact_sql = new StringBuilder();
        contact_sql.append("CREATE TABLE " + TABLE_CONTACTS + " (");
        contact_sql.append(_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        contact_sql.append(CONTACTS_KEYBASE_ID + " TEXT, ");
        contact_sql.append(CONTACTS_KEYDROID_ID + " TEXT");
        contact_sql.append(");");
        db.execSQL(contact_sql.toString());
    }
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
