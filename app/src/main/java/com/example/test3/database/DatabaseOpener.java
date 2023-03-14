package com.example.test3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseOpener extends SQLiteOpenHelper {

    public DatabaseOpener(@Nullable Context context) {
        super(context, "Game Store", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE user(email Text primary key not null, password Text, role Text)";
        db.execSQL(createUserTableQuery);
        db.execSQL("INSERT INTO user VALUES('admin@gmail.com', '123456', 'Admin')");
        db.execSQL("INSERT INTO user VALUES('user@gmail.com', '123456', 'User')");

        String createGameTableQuery = "CREATE TABLE game(id integer primary key autoincrement not null , name Text, description Text, category Text, price Integer, amount Integer, imageUrl String)";
        db.execSQL(createGameTableQuery);
        db.execSQL("INSERT INTO game(name, description, category, price, amount, imageUrl) VALUES('Pokemon Scarlet', 'This is my dream game', 'Pokemon', 99, 10, 'https://vn-test-11.slatic.net/p/e95dc95dd19b2a76218bc198d76a1d1c.jpg')");
        db.execSQL("INSERT INTO game(name, description, category, price, amount, imageUrl) VALUES('Pokemon Red', 'OMG This game is the best', 'Pokemon', 100, 10, 'https://m.media-amazon.com/images/I/71aow5iRsfL.jpg')");
        db.execSQL("INSERT INTO game(name, description, category, price, amount, imageUrl) VALUES('Pokemon XYZ', 'No this game is better', 'Pokemon', 50, 20, 'https://upload.wikimedia.org/wikipedia/vi/d/d1/Pok%C3%A9mon_movie_2016.jpg')");
        db.execSQL("INSERT INTO game(name, description, category, price, amount, imageUrl) VALUES('Pokemon Sword', 'Shut up, this game is 99$', 'Pokemon', 30, 20, 'https://vn-test-11.slatic.net/p/ce0aed550cacddceb419ff486eccb66a.jpg')");
        db.execSQL("INSERT INTO game(name, description, category, price, amount, imageUrl) VALUES('Pokemon Shield', 'I choose this game and buy it!', 'Pokemon', 99, 15, 'https://i5.walmartimages.com/asr/b6436620-88f7-4de1-b83f-ab63a04d31c7_1.84fa4280b3c00768f867997304957768.jpeg')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS ADMIN");
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS game");
        onCreate(db);
    }
}

