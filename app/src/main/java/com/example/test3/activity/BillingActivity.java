package com.example.test3.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.example.test3.adapter.CartAdapter;
import com.example.test3.utils.RecyclerItemClickListener;

public class BillingActivity extends AppCompatActivity {
    RecyclerView cartList;
    CartAdapter cartAdapter;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);

        cartList = findViewById(R.id.cartList);
        cartList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, cartList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
//                        itemClickHandler(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        cartAdapter = new CartAdapter(BillingActivity.this);
        TextView cartTotal = findViewById(R.id.billTotal);
        TextView billDate = findViewById(R.id.billDate);
        TextView billCustomer = findViewById(R.id.billCustomer);
        String email = sharedpreferences.getString("Email", "@gmail.com");

        cartTotal.setText(cartAdapter.getCartTotal().toString());
        billDate.setText(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
        billCustomer.setText(email);
        cartList.setAdapter(cartAdapter);
        cartList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void cartBackHandler (View view) {
        Intent intent = new Intent(this, Home_Activity.class );
        startActivity(intent);
    }
}
