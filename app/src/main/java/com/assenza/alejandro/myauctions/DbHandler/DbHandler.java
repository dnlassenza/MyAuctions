package com.assenza.alejandro.myauctions.DbHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assenza.alejandro.myauctions.models.Auction.Auction;
import com.assenza.alejandro.myauctions.models.Login.User;

/**
 * Created by alejandro on 12/07/17.
 */

public class DbHandler extends SQLiteOpenHelper {

    public static final long USER_DOESNT_EXIST = -1;
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MyAuctionsDB";
    private static final String USERS_TABLE = "users";
    private static final String AUCTIONS_TABLE = "auctions";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "password";
    private static final String KEY_LASTBID = "lb";
    private static final String KEY_CURRENTBID = "cb";
    private static final String KEY_DESC = "description";
    private static final String KEY_MINVALUE = "min_value";
    private static final String KEY_AUCTION_NAME = "auction_name";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + USERS_TABLE + " ("
        + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
        + KEY_PASS+ " TEXT)";

        db.execSQL(query);

        query = "CREATE TABLE " + AUCTIONS_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_AUCTION_NAME + " TEXT, " + KEY_NAME + " TEXT, " + KEY_DESC + " TEXT, " +
                KEY_MINVALUE + " INTEGER, " + KEY_LASTBID + " INTEGER, " + KEY_CURRENTBID + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AUCTIONS_TABLE);
        onCreate(db);
    }

    public long AddUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, user.GetUsername());
        values.put(KEY_PASS, user.GetPassword());

        return db.insert(USERS_TABLE, null, values);
    }

    public long AddAuction(Auction auction) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_AUCTION_NAME, auction.getAuctionName());
        values.put(KEY_NAME, auction.getBidObject().getName());
        values.put(KEY_DESC, auction.getBidObject().getDescription());
        values.put(KEY_MINVALUE, auction.getBidObject().getMinValue());
        values.put(KEY_CURRENTBID, auction.getBid().GetCurrentBid());
        values.put(KEY_LASTBID, auction.getBid().GetLastBid());

        return db.insert(USERS_TABLE, null, values);
    }

    private Cursor GetCursorFromUsers(SQLiteDatabase db, User user) {

        String query = "SELECT " + KEY_ID + " FROM " + USERS_TABLE + " WHERE " + KEY_NAME + "=? AND "
                + KEY_PASS + "=?";

        Cursor cursor = db.rawQuery(query, new String[] { user.GetUsername(), user.GetPassword()});

        return cursor;
    }

    public long GetUserId(User user) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = this.GetCursorFromUsers(db, user);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(KEY_ID));
            cursor.close();
            db.close();
            Log.d(">>", String.valueOf(id));
            return id;
        }

        cursor.close();
        db.close();

        return USER_DOESNT_EXIST;
    }
}
