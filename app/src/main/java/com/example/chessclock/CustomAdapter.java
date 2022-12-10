package com.example.chessclock;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends ListAdapter<Preset, CustomAdapter.ViewHolder> {
    Favorites favorites;
    public CustomAdapter(Favorites favorites){
        super(DIFF_CALLBACK);
        this.favorites = favorites;
    }

    private static final DiffUtil.ItemCallback<Preset> DIFF_CALLBACK = new DiffUtil.ItemCallback<Preset>() {
        @Override
        public boolean areItemsTheSame(@NonNull Preset oldItem, @NonNull Preset newItem) {
            return oldItem.id == newItem.id; // On retourne si deux items sont les mêmes
        }

        @Override
        public boolean areContentsTheSame(@NonNull Preset oldItem, @NonNull Preset newItem) {
            return oldItem.equals(newItem); // On retourne si deux items sont équivalents (affichent la même chose)
        }
    };

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setViewHolder(String.valueOf(getItem(position).getTime1()), String.valueOf(getItem(position).getTime2()),String.valueOf(getItem(position).getIncrement1()), String.valueOf(getItem(position).getIncrement2()), getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Preset preset;
        TextView time1;
        TextView time2;
        TextView increment1;
        TextView increment2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time1=itemView.findViewById(R.id.time1);
            time2=itemView.findViewById(R.id.time2);
            increment1=itemView.findViewById(R.id.increment1);
            increment2=itemView.findViewById(R.id.increment2);
            itemView.findViewById(R.id.favori).setOnClickListener(view -> {
                favorites.removePreset(preset);
            });
           /* itemView.setOnClickListener(view -> {

            });*/
        }
        public void setViewHolder(String time1, String time2, String increment1, String increment2, Preset preset){
            this.time1.setText(time1);
            this.time2.setText(time2);
            this.increment1.setText(increment1);
            this.increment2.setText(increment2);
            this.preset = preset;
            CheckBox favori = itemView.findViewById(R.id.favori);
            if (preset.getFavorite()){favori.setChecked(true);}

        }

    }
}
