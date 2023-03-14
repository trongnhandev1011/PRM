package com.example.test3.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;

public class CreateActivity extends AppCompatActivity {
    private GameDAO gameDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        gameDAO = new GameDAO(this);
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

    public void createMusic(View view) {
        EditText gameNameInput = findViewById(R.id.editGameNameInput);
        EditText gameDescriptionInput = findViewById(R.id.editGameDescriptionInput);
        EditText gameCategoryInput = findViewById(R.id.editGameCategoryInput);
        EditText gameAmountInput = findViewById(R.id.editGameAmountInput);
        EditText gamePriceInput = findViewById(R.id.editGamePriceInput);
        EditText gameImageInput = findViewById(R.id.editGameImageInput);

        Game game = new Game(1, gameNameInput.getText().toString(),
                gameDescriptionInput.getText().toString(),
                gameCategoryInput.getText().toString(),
                gameImageInput.getText().toString(),
                Integer.parseInt(gamePriceInput.getText().toString().trim()),
                Integer.parseInt(gameAmountInput.getText().toString().trim()));
        gameDAO.createGame(game);
        Intent intent = new Intent(this, Home_Activity.class);
        startActivity(intent);
    }
}
