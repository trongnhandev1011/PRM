package com.example.test3.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.test3.database.DatabaseOpener;
import com.example.test3.model.Game;
import java.util.ArrayList;

public class GameDAO {
    DatabaseOpener databaseOpener;

    public GameDAO(Context context) {
        databaseOpener = new DatabaseOpener(context);
    }

    public ArrayList<Game> getAll() {
        ArrayList<Game> games = new ArrayList<Game>();
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM game", null);
        cs.moveToFirst();

        while (!cs.isAfterLast()) {
            String id = cs.getString(0);
            String name = cs.getString(1);
            String description = cs.getString(2);
            String category = cs.getString(3);
            Integer price = cs.getInt(4);
            Integer amount = cs.getInt(5);
            String imageUrl = cs.getString(6);
            Game game = new Game(id, name, description, category, imageUrl, price, amount);
            games.add(game);
            cs.moveToNext();
        }
        return games;
    }
}
