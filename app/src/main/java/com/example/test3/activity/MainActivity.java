package com.example.test3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.UserDAO;
import com.example.test3.model.User;


public class MainActivity extends AppCompatActivity {
    private UserDAO userDAO = new UserDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHandler (View view) {
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        User loginUser = new User(emailInput.getText().toString(), passwordInput.getText().toString());
        System.out.println(userDAO.isExist(loginUser));
        if (userDAO.isExist(loginUser)) {
            Intent navigateIntent = new Intent(this, Home_Activity.class);
            startActivity(navigateIntent);
        }
    }
}