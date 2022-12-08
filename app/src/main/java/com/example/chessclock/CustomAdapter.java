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
        holder.setViewHolder(String.valueOf(presets.get(position).getTime1()), String.valueOf(presets.get(position).getTime2()),String.valueOf(presets.get(position).getIncrement1()), String.valueOf(presets.get(position).getIncrement2()));
    }

    @Override
    public int getItemCount() {
        return presets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView time1;
        TextView time2;
        TextView increment1;
        TextView increment2;
        public ViewHolder(@NonNull View itemView, AppCompatActivity favorites) {
            super(itemView);
            time1=itemView.findViewById(R.id.time1);
            time2=itemView.findViewById(R.id.time2);
            increment1=itemView.findViewById(R.id.increment1);
            increment2=itemView.findViewById(R.id.increment2);
            /*itemView.setOnClickListener( view -> {
                Intent intent = new Intent();
                //favorites.setResult(Favorites.RESULT_OK, intent);
                //favorites.finish();
            });*/
        }
        public void setViewHolder(String time1, String time2, String increment1, String increment2){
            this.time1.setText(time1);
            this.time2.setText(time1);
            this.increment1.setText(increment1);
            this.increment2.setText(increment2);
        }

    }
}
