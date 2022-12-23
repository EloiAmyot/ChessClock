package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    MyViewModel viewModel;
    Button addPreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        //viewModel.addData(new Preset(300,300,2,4,true));


        CustomAdapter monAdapter = new CustomAdapter(this);
        viewModel.getData().observe(this, monAdapter::submitList);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(monAdapter);
    }

    public void clickAddPreset(View view){
        Intent intent = new Intent(this, NouvellePartie.class);
        intent.putExtra("origine", "nouveau_preset");
        startActivity(intent);
    }

    public void removePreset(Preset preset){
        viewModel.removeData(preset);
    }
}