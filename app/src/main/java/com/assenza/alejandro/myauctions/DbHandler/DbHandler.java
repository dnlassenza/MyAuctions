package com.assenza.alejandro.myauctions.DbHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.assenza.alejandro.myauctions.models.Login.User;

/**
 * Created by alejandro on 12/07/17.
 */

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyAuctionsDB";
    private static final String USERS_TABLE = "users";
    private static final String AUCTIONS_TABLE = "auctions";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "password";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + USERS_TABLE + " ("
        + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
        + KEY_PASS+ " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        onCreate(db);
    }

    public void AddUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, user.GetUsername()); // Shop Name
        values.put(KEY_PASS, user.GetPassword()); // Shop Phone Number
        db.insert(USERS_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public boolean UserExist(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + KEY_NAME + "=? AND "
                + KEY_PASS + "=?";

        Cursor cursor = db.rawQuery(query, new String[] { user.GetUsername(), user.GetPassword()});

        if (cursor.getCount() > 0)
            return true;

        return false;
    }
}
