package com.example.test3.activity;

import static com.example.test3.model.Cart.cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;
import com.example.test3.utils.ImageUtils;

public class DetailUserActivity extends AppCompatActivity {
    private Game game;
    private GameDAO gameDAO = new GameDAO(this);
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("game");

        TextView name = findViewById(R.id.userGameDetailName);
        TextView description = findViewById(R.id.userGameDetailDescription);
        TextView category = findViewById(R.id.userGameDetailCategory);
        TextView price = findViewById(R.id.userGameDetailPrice);
        TextView amount = findViewById(R.id.userGameDetailAmount);
        ImageView gameImg = findViewById(R.id.userDetailGameImg);

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

    public void removeFromCart(View view) {
        cart.remove(game.getId());
    }

    public void addToCart(View view) {
        if (cart.get(game.getId()) != null) {
            cart.put(game.getId(), cart.get(game.getId()) + 1);
        } else {
            cart.put(game.getId(), 1);
        }
    }

    public void removeAItem(View view) {
        if (cart.get(game.getId()) != null) {
            if (cart.get(game.getId()) > 1) {
                cart.put(game.getId(), cart.get(game.getId()) - 1);
            } else {
                cart.remove(game.getId());
            }
        }
    }

    public void goToCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
