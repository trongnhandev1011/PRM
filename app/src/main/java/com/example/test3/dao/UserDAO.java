package com.example.test3.dao;

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

    public boolean isExist(User user) {
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM user WHERE email=? AND password=?",
                new String[]{user.getEmail(), user.getPassword()});
        return cursor.getCount() == 1;
    }
}
