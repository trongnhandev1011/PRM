package com.example.test3.adapter;

import static com.example.test3.model.Cart.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.example.test3.dao.GameDAO;
import com.example.test3.model.Game;
import com.example.test3.utils.ImageUtils;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private final Context ctx;
    private final GameDAO gameDAO;

    @SuppressLint("NotifyDataSetChanged")
    public CartAdapter(Context ctx) {
        this.ctx = ctx;
        gameDAO = new GameDAO(ctx);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder viewHolder, int position) {
        Integer gameKey = (Integer) cart.keySet().toArray()[position];
        System.out.println(position);
        Game game = gameDAO.getGameById(gameKey);
        viewHolder.tvGameName.setText(game.getName());
        viewHolder.tvGamePrice.setText(String.valueOf(game.getPrice()));
        viewHolder.tvGameQuantity.setText(String.valueOf(cart.get(gameKey)));
        viewHolder.tvGameTotal.setText(String.valueOf(game.getPrice() * cart.get(gameKey)));
        new ImageUtils(viewHolder.tvGameImage)
                .execute(game.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return cart.keySet().toArray().length;
    }

    public Integer getCartTotal() {
        Integer cartTotal = 0;
        for (int i = 0; i < cart.keySet().toArray().length; i ++) {
            Integer gameId = (Integer) cart.keySet().toArray()[i];
            cartTotal += cart.get(gameId) * gameDAO.getGameById(gameId).getPrice();
        }
        return cartTotal;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGameName;
        TextView tvGameQuantity;
        TextView tvGamePrice;
        ImageView tvGameImage;
        TextView tvGameTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGameName = itemView.findViewById(R.id.cartGameName);
            tvGameQuantity = itemView.findViewById(R.id.cartGameQuantity);
            tvGamePrice = itemView.findViewById(R.id.cartGamePrice);
            tvGameImage = itemView.findViewById(R.id.cartGameImage);
            tvGameTotal = itemView.findViewById(R.id.cartItemTotal);
        }
    }
}
