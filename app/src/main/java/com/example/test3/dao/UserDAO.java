package com.example.test3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test3.database.DatabaseOpener;
import com.example.test3.model.User;

public class UserDAO {
    private final DatabaseOpener databaseOpener;

    public UserDAO(Context context) {
        this.databaseOpener = new DatabaseOpener(context);
    }

    public User isExist(String email, String password) {
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?",
                new String[]{email, password});
        if (cursor.moveToFirst()) {
            int roleIndex = cursor.getColumnIndex("role");
            if (roleIndex >= 0) {
                String role = cursor.getString(roleIndex);
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);
                return user;
            }
        }
        return null;
    }

    public boolean register(String email, String password) {
        SQLiteDatabase db = databaseOpener.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("role", "User");
        long result = db.insert("user", null, values);
        return result != -1;
    }
}
