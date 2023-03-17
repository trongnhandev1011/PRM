package com.example.test3.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.UserDAO;
import com.example.test3.model.User;


public class MainActivity extends AppCompatActivity {
    private UserDAO userDAO = new UserDAO(this);
    public static final String SESSION = "session" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHandler (View view) {
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        User user = userDAO.isExist(emailInput.getText().toString(), passwordInput.getText().toString());
        if (user != null ) {
            Intent navigateIntent = new Intent(this, Home_Activity.class);
            SharedPreferences sharedpreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Role", user.getRole());
            editor.putString("Email", user.getEmail());
            editor.commit();
            startActivity(navigateIntent);
        }

    }

    public void goToRegister (View view) {
        Intent navigateIntent = new Intent(this, RegisterActivity.class);
        startActivity(navigateIntent);
    }
}