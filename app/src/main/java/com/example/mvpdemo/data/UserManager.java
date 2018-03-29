package com.example.mvpdemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mvpdemo.data.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user_database";
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";

    private static UserManager INSTANCE = null;

    public static UserManager getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserManager(context);
        }
        return INSTANCE;
    }

    private UserManager(final Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                NAME + " TEXT, " +
                AGE + " TEXT)";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(final User user) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(NAME, user.getName());
        values.put(AGE, user.getAge());
        final long rowInserted = database.insert(TABLE_NAME, null, values);
        database.close();
        return rowInserted;
    }

    public List<User> getAllUsers() {
        final List<User> userList = new ArrayList<>();
        final String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        final SQLiteDatabase database = this.getWritableDatabase();
        final Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) {
            return null;
        }

        if (cursor.moveToFirst()) {
            do {
                final User user = new User();
                user.setName(cursor.getString(1));
                user.setAge(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return userList;
    }
}
