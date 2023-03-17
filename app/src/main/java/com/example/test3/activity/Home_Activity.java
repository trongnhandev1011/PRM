package com.example.test3.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.example.test3.adapter.GameAdapter;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;
import com.example.test3.utils.RecyclerItemClickListener;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {
    ArrayList<Game> games;
    GameDAO gameDAO;
    GameAdapter gameAdapter;
    RecyclerView listView;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        listView = findViewById(R.id.listView);
        listView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, listView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        itemClickHandler(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        gameDAO = new GameDAO(Home_Activity.this);
        games = gameDAO.getAll();
        gameAdapter = new GameAdapter(Home_Activity.this, games);
        System.out.println("size: " +gameAdapter.games.size());
        listView.setAdapter(gameAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        String role = sharedpreferences.getString("Role", "User");
        if (role.equals("Admin")) {
            menu.findItem(R.id.menu_cart).setVisible(false);
        } else {
            menu.findItem(R.id.menu_create).setVisible(false);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                onLogout();
                return true;
            case R.id.menu_create:
                onCreate();
                return true;
            case R.id.menu_cart:
                onCart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onLogout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onCreate() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void onCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void itemClickHandler(int position) {
        Game game = games.get(position);
        String role = sharedpreferences.getString("Role", "User");
        System.out.println(role);
        Intent intent;
        if (role.equals("Admin")) {
            intent = new Intent(this, DetailActivity.class);
        } else {
            intent = new Intent(this, DetailUserActivity.class);
        }
        intent.putExtra("game", game);
        startActivity(intent);
    }
}