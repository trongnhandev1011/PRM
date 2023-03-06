package com.example.test3.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.example.test3.adapter.GameAdapter;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {

    ArrayList<Game> games;
    GameDAO gameDAO;
    GameAdapter gameAdapter;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.listView);
        gameDAO = new GameDAO(Home_Activity.this);
        games = gameDAO.getAll();
        gameAdapter = new GameAdapter(Home_Activity.this, games);
        System.out.println("size: " +gameAdapter.games.size());
        listView.setAdapter(gameAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}