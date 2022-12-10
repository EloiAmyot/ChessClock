package com.example.chessclock;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

public class NouvellePartie extends AppCompatActivity {

    Preset preset;
    MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_holder);
        preset = new Preset();
        model = new ViewModelProvider(this).get(MyViewModel.class);

        findViewById(R.id.favori).setOnClickListener(view -> {
            preset.setFavorite(true);
        });
        findViewById(R.id.globalLayout).setOnClickListener(view -> {
            if (preset.getFavorite()){
                model.addData(preset);
            }
            Intent intent = new Intent();
            intent.putExtra("Preset", preset);
            this.setResult(this.RESULT_OK, intent);
            this.finish();
        });
    }
}