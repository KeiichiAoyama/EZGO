package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class internalDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "internal.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    private String[] allColumns =
            {"userID", "uName", "uAddress", "uPhone", "uEmail", "uBirthdate", "uProfilePicture"};

    public internalDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS user (\n" +
                "    userID TEXT PRIMARY KEY,\n" +
                "    uName TEXT NOT NULL,\n" +
                "    uAddress TEXT,\n" +
                "    uPhone TEXT,\n" +
                "    uEmail TEXT,\n" +
                "    uBirthdate TEXT,\n" +
                "    uProfilePicture TEXT\n" +
                ");";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    private User cursorToUser(Cursor cursor){
        User user = new User();
        user.id = cursor.getString(0);
        user.name = cursor.getString(1);
        user.address = cursor.getString(2);
        user.phone = cursor.getString(3);
        user.email = cursor.getString(4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            user.bday = LocalDate.parse(cursor.getString(5));
        }
        user.pfp = cursor.getString(6);
        return user;
    }

    public void createUser(User user){
        ContentValues values = new ContentValues();
        values.put("userID", user.id);
        values.put("uName", user.name);
        values.put("uAddress", user.address);
        values.put("uPhone", user.phone);
        values.put("uEmail", user.email);
        values.put("uBirthdate", user.bday.toString());
        values.put("uProfilePicture", user.pfp);
        db.insert("user", null, values);
    }

    public User getUser(){
        Cursor cursor = db.query("user", allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    public void updateUser(User user){
        String filter = "userID="+user.id;
        ContentValues values = new ContentValues();
        values.put("userID", user.id);
        values.put("uName", user.name);
        values.put("uAddress", user.address);
        values.put("uPhone", user.phone);
        values.put("uEmail", user.email);
        values.put("uBirthdate", user.bday.toString());
        values.put("uProfilePicture", user.pfp);
        db.update("user", values, filter, null);
    }

    public void deleteUser(User user){
        String filter = "userID="+user.id;
        db.delete("user", filter, null);
    }

    public boolean checkUserExist(){
        String query = "SELECT COUNT(*) FROM user";
        Cursor cursor = db.rawQuery(query, null);
        boolean ret = true;
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            ret = count == 0;
            cursor.close();
        }
        return ret;
    }
}
