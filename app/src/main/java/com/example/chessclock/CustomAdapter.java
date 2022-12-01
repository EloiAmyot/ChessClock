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
    AppCompatActivity secondActivity;
    public CustomAdapter(ArrayList<Preset> units, AppCompatActivity secondActivity){
        this.presets = units;
        this.secondActivity = secondActivity;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bananes, parent, false);
        return new ViewHolder(view, secondActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setUnitName(presets.get(position).getUnitName());
        holder.setImage(presets.get(position).getImage());
        holder.setConversionFactor(presets.get(position).getConversionFactor());
        holder.setUnit(presets.get(position));
    }

    @Override
    public int getItemCount() {
        return presets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView unitName;
        private final ImageView image;
        private final TextView conversionFactor;
        private Preset unit = new Unit();

        public ViewHolder(@NonNull View itemView, AppCompatActivity secondActivity) {
            super(itemView);
            itemView.setOnClickListener( view -> {
                Intent intent = new Intent();
                intent.putExtra("Unit", presets);
                secondActivity.setResult(secondActivity.RESULT_OK, intent);
                secondActivity.finish();
            });
            unitName = itemView.findViewById(R.id.unitName);
            image = itemView.findViewById(R.id.image);
            conversionFactor = itemView.findViewById(R.id.conversionFactor);
        }

        public void setUnitName(String nomUnit){
            this.unitName.setText(nomUnit);
        }

        public void setImage(int image){
            this.image.setImageResource(image);
        }

        public void setConversionFactor(double conversionFactor){
            this.conversionFactor.setText(String.valueOf(conversionFactor));
        }

        public void setUnit(Preset unit){
            this.unit.setClass(presets);
        }
    }
}
