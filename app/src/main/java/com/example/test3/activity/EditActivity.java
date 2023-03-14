package com.example.test3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;

public class EditActivity extends AppCompatActivity {
    private GameDAO gameDAO;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        gameDAO = new GameDAO(this);
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("game");
        EditText gameNameInput = findViewById(R.id.editName);
        EditText gameDescriptionInput = findViewById(R.id.editDescription);
        EditText gameCategoryInput = findViewById(R.id.editCategory);
        EditText gameAmountInput = findViewById(R.id.editAmount);
        EditText gamePriceInput = findViewById(R.id.editPrice);
        EditText gameImageInput = findViewById(R.id.editImage);

        Integer gamePrice = game.getPrice();
        Integer gameAmount = game.getAmount();

        gameNameInput.setText(game.getName());
        gameDescriptionInput.setText(game.getDescription());
        gameCategoryInput.setText(game.getCategory());
        gameAmountInput.setText(gameAmount.toString());
        gamePriceInput.setText(gamePrice.toString());
        gameImageInput.setText(game.getImageUrl());

    }

    public void onBack(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void editGame(View view) {
        EditText gameNameInput = findViewById(R.id.editName);
        EditText gameDescriptionInput = findViewById(R.id.editDescription);
        EditText gameCategoryInput = findViewById(R.id.editCategory);
        EditText gameAmountInput = findViewById(R.id.editAmount);
        EditText gamePriceInput = findViewById(R.id.editPrice);
        EditText gameImageInput = findViewById(R.id.editImage);

        Game gameData = new Game(game.getId(), gameNameInput.getText().toString(),
                gameDescriptionInput.getText().toString(),
                gameCategoryInput.getText().toString(),
                gameImageInput.getText().toString(),
                Integer.parseInt(gamePriceInput.getText().toString().trim()),
                Integer.parseInt(gameAmountInput.getText().toString().trim()));
        gameDAO.editGame(gameData);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("game", gameData);
        startActivity(intent);
    }
}
