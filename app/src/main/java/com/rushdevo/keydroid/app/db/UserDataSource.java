package com.rushdevo.keydroid.app.db;

import static android.provider.BaseColumns._ID;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;

import com.rushdevo.keydroid.app.R;
import com.rushdevo.keydroid.app.db.model.User;

public class UserDataSource {
    public static final String USERS_TABLE_NAME = "users";

    public static final String USERS_KEYBASE_ID = "keybase_id";
    public static final String[] USERS_COLUMNS = {_ID, USERS_KEYBASE_ID};

    public static final int ID_INDEX = 0;

    private KeydroidDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserDataSource(Context context) {
        dbHelper = new KeydroidDatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        db.close();
    }

    /**
     * Gets the singleton user record
     *
     * @return The User object from the first record in the users table, or null if none exists
     */
    public User getUser() {
        Cursor cursor = db.query(USERS_TABLE_NAME,USERS_COLUMNS,null,null,null,null,null);
        cursor.moveToFirst();
        User user = null;
        if (!cursor.isAfterLast()) user = cursorToUser(cursor);
        cursor.close();
        return user;
    }


    public void createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(USERS_KEYBASE_ID, user.getKeybaseID());
        int insertId = (int)db.insert(USERS_TABLE_NAME, null, values);
        user.setId(insertId);
    }




    private User cursorToUser(Cursor cursor) {
        Integer id = cursor.getInt(ID_INDEX);
        return new User(id, null);
    }
}
