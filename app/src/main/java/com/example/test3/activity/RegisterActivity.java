package com.example.test3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.R;
import com.example.test3.dao.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    private UserDAO userDAO = new UserDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void clickHandler (View view) {
        EditText emailInput = findViewById(R.id.emailInput2);
        EditText passwordInput = findViewById(R.id.passwordInput2);
        EditText confirmPasswordInput = findViewById(R.id.passwordInput3);
        Boolean result = true;
        if (passwordInput.getText().toString().equals(confirmPasswordInput.getText().toString())) {
            result = userDAO.register(emailInput.getText().toString(), passwordInput.getText().toString());
        }

        if (result) {
            Intent navigateIntent = new Intent(this, Home_Activity.class);
            startActivity(navigateIntent);
        }
    }

    public void goToLogin (View view) {
        Intent navigateIntent = new Intent(this, MainActivity.class);
        startActivity(navigateIntent);
    }
}
