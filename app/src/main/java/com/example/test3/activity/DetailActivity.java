package com.example.test3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;
import com.example.test3.utils.ImageUtils;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements Serializable {
    private Game game;
    private GameDAO gameDAO = new GameDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("game");

        TextView name = findViewById(R.id.gameName);
        TextView description = findViewById(R.id.gameDescription);
        TextView category = findViewById(R.id.gameCategory);
        TextView price = findViewById(R.id.gamePrice);
        TextView amount = findViewById(R.id.gameAmount);
        ImageView gameImg = findViewById(R.id.gameImage);

        Integer gamePrice = game.getPrice();
        Integer gameAmount = game.getAmount();

        name.setText(game.getName());
        description.setText(game.getDescription());
        category.setText(game.getCategory());
        price.setText(gamePrice.toString());
        amount.setText(gameAmount.toString());
        new ImageUtils(gameImg)
                .execute(game.getImageUrl());
    }

    public void onBack(View view) {
        Intent intent = new Intent(this, Home_Activity.class);
        startActivity(intent);
    }

    public void onDelete(View view) {
        gameDAO.deleteGame(game.getId());
        Intent intent = new Intent(this, Home_Activity.class);
        startActivity(intent);
    }

    public void onEdit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
