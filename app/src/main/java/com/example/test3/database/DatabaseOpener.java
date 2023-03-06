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
        String createUserTableQuery = "CREATE TABLE user(email Text primary key not null, password Text)";
        db.execSQL(createUserTableQuery);
        db.execSQL("INSERT INTO user VALUES('admin@gmail.com', '123456')");

        String createGameTableQuery = "CREATE TABLE game(id Text primary key not null, name Text, description Text, category Text, price Integer, amount Integer, imageUrl String)";
        db.execSQL(createGameTableQuery);
        db.execSQL("INSERT INTO game VALUES('G1', 'Pokemon Scarlet', 'This is my dream game', 'Pokemon', 99, 10, 'https://vn-test-11.slatic.net/p/e95dc95dd19b2a76218bc198d76a1d1c.jpg')");
        db.execSQL("INSERT INTO game VALUES('G2', 'Pokemon Red', 'OMG This game is the best', 'Pokemon', 100, 10, 'https://m.media-amazon.com/images/I/71aow5iRsfL.jpg')");
        db.execSQL("INSERT INTO game VALUES('G3', 'Pokemon XYZ', 'No this game is better', 'Pokemon', 50, 20, 'https://upload.wikimedia.org/wikipedia/vi/d/d1/Pok%C3%A9mon_movie_2016.jpg')");
        db.execSQL("INSERT INTO game VALUES('G4', 'Pokemon Sword', 'Shut up, this game is 99$', 'Pokemon', 30, 20, 'http://product.hstatic.net/1000231532/product/pokemon_sword_nintendo_switch_fb43376de31149ffaf605ceeedf248c4_grande.jpg')");
        db.execSQL("INSERT INTO game VALUES('G5', 'Pokemon Shield', 'I choose this game and buy it!', 'Pokemon', 99, 15, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.walmart.com%2Fip%2FPokemon-Shield-Nintendo-Switch-Physical-Edition-110457%2F919694071&psig=AOvVaw2aRNgSC3eSizSWTjywoPrg&ust=1678175425541000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCNC5guPoxv0CFQAAAAAdAAAAABAD')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS ADMIN");
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS game");
        onCreate(db);
    }
}

