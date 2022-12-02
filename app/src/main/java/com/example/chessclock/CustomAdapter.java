package com.example.chessclock;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    ArrayList<Preset> presets;
    AppCompatActivity favorites;
    public CustomAdapter(ArrayList<Preset> presets, AppCompatActivity favorites){
        this.presets = presets;
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        return new ViewHolder(view, favorites);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return presets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, AppCompatActivity favorites) {
            super(itemView);
            /*itemView.setOnClickListener( view -> {
                Intent intent = new Intent();
                //favorites.setResult(Favorites.RESULT_OK, intent);
                //favorites.finish();
            });*/
        }

    }
}
