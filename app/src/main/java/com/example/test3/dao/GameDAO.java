package com.example.test3.dao;

import android.content.ContentValues;
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
            Integer id = cs.getInt(0);
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

    public void createGame(Game game) {
        SQLiteDatabase db = databaseOpener.getWritableDatabase();
        String name = game.getName();
        String description = game.getDescription();
        String category = game.getCategory();
        Integer price = game.getPrice();
        Integer amount = game.getAmount();
        String imageUrl = game.getImageUrl();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("description", description);
        values.put("category", category);
        values.put("price", price);
        values.put("amount", amount);
        values.put("imageUrl", imageUrl);
        db.insert("game", null, values);
        db.close();
    }

    public void deleteGame(Integer id) {
        SQLiteDatabase db = databaseOpener.getWritableDatabase();
        String convertId = id.toString();
        db.delete("game", "id=?", new String[]{convertId});
        db.close();
    }

    public void editGame(Game game) {
        SQLiteDatabase db = databaseOpener.getWritableDatabase();
        Integer id = game.getId();
        String name = game.getName();
        String description = game.getDescription();
        String category = game.getCategory();
        Integer price = game.getPrice();
        Integer amount = game.getAmount();
        String imageUrl = game.getImageUrl();


        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("description", description);
        values.put("category", category);
        values.put("price", price);
        values.put("amount", amount);
        values.put("imageUrl", imageUrl);
        db.update("game", values, "id=?", new String[]{id.toString()});
        db.close();
    }

    public Game getGameById(Integer id) {
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Game game = new Game();
        Cursor cursor = db.rawQuery("SELECT * FROM game WHERE id=?",
                new String[]{id.toString()});
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int priceIndex = cursor.getColumnIndex("price");
            int imageIndex = cursor.getColumnIndex("imageUrl");
            if (nameIndex >= 0 && priceIndex >= 0 && imageIndex >= 0) {
                String name = cursor.getString(nameIndex);
                Integer price = cursor.getInt(priceIndex);
                String imageUrl = cursor.getString(imageIndex);
                game.setName(name);
                game.setPrice(price);
                game.setImageUrl(imageUrl);
            }
        }
        return game;
    }
}
