package com.example.test3.adapter;

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
import com.example.test3.model.Game;
import com.example.test3.utils.ImageUtils;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private final Context ctx;
    public final ArrayList<Game> games;

    @SuppressLint("NotifyDataSetChanged")
    public GameAdapter(Context ctx, ArrayList<Game> games) {
        this.ctx = ctx;
        this.games = games;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        System.out.println((games.get(position)));
        viewHolder.tvGameName.setText(games.get(position).getName());
        viewHolder.tvGameDescription.setText(String.valueOf(games.get(position).getDescription()));
        new ImageUtils(viewHolder.gameImg)
                .execute(games.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGameName;
        TextView tvGameDescription;
        ImageView gameImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGameName = itemView.findViewById(R.id.game_title);
            tvGameDescription = itemView.findViewById(R.id.game_description);
            gameImg = itemView.findViewById(R.id.imageView);
        }
    }
}


